package uoc.ds.pr;

//import edu.uoc.ds.adt.*;

import edu.uoc.ds.adt.nonlinear.*;
import edu.uoc.ds.adt.nonlinear.graphs.*;
import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.model.*;
import uoc.ds.pr.util.*;

import java.time.*;
import java.util.*;

public class CTTCompaniesJobsPR2Impl extends CTTCompaniesJobsImpl implements CTTCompaniesJobsPR2 {

    private final HashTable<String, Employee> employees;

    private final HashTable<String, Room> rooms;

    private final DictionaryAVLImpl<String, Equipment> equipments;

    private final DSArray<Role> roles;

    private final DirectedGraph<Employee, String> socialNetwork;

    private final OrderedVector<Room> best5rooms;

    public CTTCompaniesJobsPR2Impl() {
        employees = new HashTable<>(HashTable.DEFAULT_CAPACITY);
        rooms = new HashTable<>(HashTable.DEFAULT_CAPACITY);
        equipments = new DictionaryAVLImpl<>();
        roles = new DSArray<>(MAX_NUM_ROLES);
        best5rooms = new OrderedVector<>(MAX_BEST5_EQUIPPEMENT, Room.CMP_R);
        socialNetwork = new DirectedGraphImpl<>();
    }

    @Override
    public void addRole(String roleId, String description) {
        Role existingRole = roles.get(roleId);
        if (existingRole == null) {
            Role newRole = new Role(roleId, description);
            roles.put(roleId, newRole);
        } else {
            existingRole.update(description);
            roles.update(roleId, existingRole);
        }
    }

    @Override
    public void addEmployee(String employeeId, String name, String surname, LocalDate localDate, String role) {
        Employee existingEmployee = employees.get(employeeId);
        if (existingEmployee == null) {
            Employee newEmployee = new Employee(employeeId, name, surname, localDate, role);
            employees.put(employeeId, newEmployee);
            roles.get(role).addEmployee(newEmployee);
        } else {
            roles.get(existingEmployee.getRole()).removeEmployee(existingEmployee); // Caso no pueda desempeñar mas de un rol (por confirmar)
            existingEmployee.update(name, surname, localDate, role);
            employees.put(employeeId, existingEmployee);
            roles.get(role).addEmployee(existingEmployee);
        }
    }

    @Override
    public void addRoom(String roomId, String name, String description, RoomType roomtype) {
        Room existingRoom = rooms.get(roomId);
        if (existingRoom == null) {
            Room newRoom = new Room(roomId, name, description, roomtype);
            rooms.put(roomId, newRoom);
        } else {
            existingRoom.update(name, description, roomtype);
            rooms.put(roomId, existingRoom);
        }
    }

    @Override
    public void assignEmployee(String employeeId, String roomId) throws EmployeeAlreadyAssignedException, EmployeeNotFoundException, RoomNotFoundException {
        Employee employee = employees.get(employeeId);
        Room room = rooms.get(roomId);

        if (employee == null) throw new EmployeeNotFoundException();
        if (room == null) throw new RoomNotFoundException();
        if (employee.isAssignedToRoom(room)) throw new EmployeeAlreadyAssignedException();

        employee.assignRoom(room);
        room.addEmployee(employee);

    }

    @Override
    public Iterator<Employee> getEmployeesByRoom(String roomId) throws RoomNotFoundException, NOEmployeeException {

        Room room = rooms.get(roomId);
        if (room == null) throw new RoomNotFoundException();

        Iterator<Employee> employeesInRoom = room.getEmployees();
        if (!employeesInRoom.hasNext()) throw new NOEmployeeException();

        return employeesInRoom;
    }

    @Override
    public Iterator<Employee> getEmployeesByRole(String roleId) throws NOEmployeeException {
        Iterator<Employee> EEbyRole = roles.get(roleId).getEmployees();
        if (!EEbyRole.hasNext()) throw new NOEmployeeException();
        return EEbyRole;

    }

    @Override
    public void addEquipment(String equipmentId, String name, String description) {
        Equipment existingEquipment = equipments.get(equipmentId);
        if (existingEquipment == null) {
            Equipment newEquipment = new Equipment(equipmentId, name, description);
            equipments.put(equipmentId, newEquipment);
        } else {
            existingEquipment.update(name, description);
            equipments.put(equipmentId, existingEquipment);
        }
    }

    @Override
    public AssignEquipmentResponse assignEquipment(String equipmentId, String roomId) throws EquipmentNotFoundException, RoomNotFoundException, EquipmentAlreadyAssignedException {
        Equipment equipment = equipments.get(equipmentId);
        Room newRoom = rooms.get(roomId);

        if (equipment == null) throw new EquipmentNotFoundException();
        if (newRoom == null) throw new RoomNotFoundException();

        Room currentRoom = equipment.getRoom();
        if (currentRoom != null) {
            if (currentRoom.getRoomId().equals(roomId)) throw new EquipmentAlreadyAssignedException();
            currentRoom.removeEquipment(equipment);
            updateBestRoom(currentRoom);
        }
        equipment.assignedToRoom(newRoom);
        updateBestRoom(newRoom);
        return currentRoom == null ? AssignEquipmentResponse.ASSIGNED : AssignEquipmentResponse.REASSIGNED;
        /*
        NO CONSIGO QUE  FUNCIONE LA ACTUALIZACION DEl VECTOR ORDENADO best5rooms
        se deja el OrderedVector en esta implementacion pero en la funcion best5EquippedRooms() no devolvera dicho vector
        si no que se creara ad-hoc un nuevo vector ordenado consultando todas las salas del CTT
         */

    }

    @Override
    public Level getLevel(String workerId) throws WorkerNotFoundException {
        Worker worker = getWorker(workerId);
        if (worker == null) {
            throw new WorkerNotFoundException();
        }
        return worker.getLevel();
    }

    @Override
    public Iterator<Enrollment> getWorkersByJobOffer(String jobOfferId) throws JobOfferNotFoundException, NoWorkerException {
        JobOffer jobOffer = jobOffers.get(jobOfferId);

        if (jobOffer == null) throw new JobOfferNotFoundException();
        if (jobOffer.getNumWorkers() == 0) throw new NoWorkerException();

        return jobOffer.enrollments();
    }

    @Override
    public Iterator<Enrollment> getSubstitutesByJobOffer(String jobOfferId) throws JobOfferNotFoundException, NoWorkerException {
        JobOffer jobOffer = getJobOffer(jobOfferId);
        if (jobOffer == null) throw new JobOfferNotFoundException();
        if (jobOffer.getNumSubstitutes() == 0) throw new NoWorkerException();

        return jobOffer.substitutes();
    }

    @Override
    public Iterator<Room> getRoomsWithoutEmployees() throws NoRoomsException {

        LinkedList<Room> roomsWithoutEmployees = new LinkedList<>();

        for (Iterator<Room> it = rooms.values(); it.hasNext(); ) {
            Room room = it.next();
            if (!room.getEmployees().hasNext()) roomsWithoutEmployees.insertEnd(room);
        }

        if (roomsWithoutEmployees.isEmpty()) throw new NoRoomsException();

        return roomsWithoutEmployees.values();
    }


    @Override
    public Iterator<Room> best5EquippedRooms() throws NoRoomsException {
        if (rooms.isEmpty()) throw new NoRoomsException();

        OrderedVector<Room> bestEquippedRooms = new OrderedVector<>(MAX_BEST5_EQUIPPEMENT,
                Comparator.comparingInt(Room::numEquipments));

        Iterator<Room> roomIterator = rooms.values();
        while (roomIterator.hasNext()) {
            Room room = roomIterator.next();
            bestEquippedRooms.update(room);
        }

        return bestEquippedRooms.values();

    }


    /*
        @Override
        public Iterator<Room> best5EquippedRooms() throws NoRoomsException {
            if (best5rooms.isEmpty()) throw new NoRoomsException();
            return best5rooms.values();

       // Esta es la solucion que me gustaria utilizar pero no consigo actualizar best5rooms desde assignEquipment()

        }
    */
    private void updateBestRoom(Room room) {
        // best5rooms.delete(room);
        best5rooms.update(room);
    }


    @Override
    public void addFollower(String followerId, String followedId) throws FollowerNotFound, FollowedException {
        Employee follower = getEmployee(followerId);
        Employee followed = getEmployee(followedId);

        if (follower == null) throw new FollowerNotFound();

        if (followed == null) throw new FollowedException();

        Vertex<Employee> followerVertex = socialNetwork.getVertex(follower);
        if (followerVertex == null) followerVertex = socialNetwork.newVertex(follower);

        Vertex<Employee> followedVertex = socialNetwork.getVertex(followed);
        if (followedVertex == null) followedVertex = socialNetwork.newVertex(followed);

        if (socialNetwork.getEdge(followerVertex, followedVertex) != null) throw new FollowedException();

        socialNetwork.newEdge(followerVertex, followedVertex);
    }

    @Override
    public Iterator<Employee> getFollowers(String followedId) throws EmployeeNotFoundException, NoFollowersException, FollowerNotFound {
        Employee followed = getEmployee(followedId);
        if (followed == null) throw new EmployeeNotFoundException();

        Vertex<Employee> followedVertex = socialNetwork.getVertex(followed);
        if (followedVertex == null) throw new NoFollowersException();

        LinkedList<Employee> followers = new LinkedList<>();
        Iterator<Edge<String, Employee>> edges = socialNetwork.edges();
        while (edges.hasNext()) {
            DirectedEdge<String, Employee> edge = (DirectedEdge<String, Employee>) edges.next();
            if (edge.getVertexDst().equals(followedVertex)) followers.insertEnd(edge.getVertexSrc().getValue());
        }

        if (followers.isEmpty()) throw new NoFollowersException();

        return followers.values();
    }

    @Override
    public Iterator<Employee> getFollowings(String followerId) throws EmployeeNotFoundException, NoFollowedException {
        Employee follower = getEmployee(followerId);
        if (follower == null) throw new EmployeeNotFoundException();

        Vertex<Employee> followerVertex = socialNetwork.getVertex(follower);
        if (followerVertex == null) throw new NoFollowedException();

        LinkedList<Employee> followings = new LinkedList<>();
        Iterator<Edge<String, Employee>> edges = socialNetwork.edgesWithSource(followerVertex);
        while (edges.hasNext()) {
            DirectedEdge<String, Employee> edge = (DirectedEdge<String, Employee>) edges.next();
            followings.insertEnd(edge.getVertexDst().getValue());
        }

        if (followings.isEmpty()) throw new NoFollowedException();

        return followings.values();
    }

    @Override
    public Iterator<Employee> recommendations(String followerId) throws EmployeeNotFoundException, NoFollowedException {
        Employee follower = getEmployee(followerId);
        if (follower == null) throw new EmployeeNotFoundException();

        Vertex<Employee> followerVertex = socialNetwork.getVertex(follower);
        if (followerVertex == null) throw new NoFollowedException();

        LinkedList<Employee> recommendations = new LinkedList<>();
        Iterator<Edge<String, Employee>> edges = socialNetwork.edgesWithSource(followerVertex);

        while (edges.hasNext()) {
            DirectedEdge<String, Employee> edge = (DirectedEdge<String, Employee>) edges.next();
            Employee followed = edge.getVertexDst().getValue();

            Iterator<Edge<String, Employee>> followedEdges = socialNetwork.edgesWithSource(socialNetwork.getVertex(followed));
            while (followedEdges.hasNext()) {
                DirectedEdge<String, Employee> followedEdge = (DirectedEdge<String, Employee>) followedEdges.next();
                Employee recommended = followedEdge.getVertexDst().getValue();

                if (!recommended.equals(follower) && socialNetwork.getEdge(followerVertex, socialNetwork.getVertex(recommended)) == null) {
                    recommendations.insertEnd(recommended);
                }
            }
        }

        if (recommendations.isEmpty()) throw new NoFollowedException();

        return recommendations.values();
    }

    @Override
    public Iterator<Employee> getUnfollowedColleagues(String employeeId) throws EmployeeNotFoundException, NOEmployeeException, RoomNotFoundException {
        Employee employee = getEmployee(employeeId);
        if (employee == null) throw new EmployeeNotFoundException();

        LinkedList<Employee> unfollowedColleagues = new LinkedList<>();
        Iterator<Room> assignedRoomsIterator = employee.getRooms();

        while (assignedRoomsIterator.hasNext()) {
            Room room = assignedRoomsIterator.next();
            Iterator<Employee> roomEmployeesIterator = getEmployeesByRoom(room.getRoomId());

            while (roomEmployeesIterator.hasNext()) {
                Employee colleague = roomEmployeesIterator.next();

                if (!colleague.getEmployeeId().equals(employeeId) && !isAlreadyInList(unfollowedColleagues, colleague)) {
                    Vertex<Employee> employeeVertex = socialNetwork.getVertex(employee);
                    Vertex<Employee> colleagueVertex = socialNetwork.getVertex(colleague);
                    if (socialNetwork.getEdge(employeeVertex, colleagueVertex) == null) {
                        unfollowedColleagues.insertEnd(colleague);
                    }
                }
            }
        }

        if (unfollowedColleagues.isEmpty()) throw new NOEmployeeException();

        return unfollowedColleagues.values();
    }

    private boolean isAlreadyInList(LinkedList<Employee> list, Employee employee) {
        Iterator<Employee> it = list.values();
        while (it.hasNext()) if (it.next().getEmployeeId().equals(employee.getEmployeeId())) return true;
        return false;
    }

    @Override
    public int numRoles() {
        return this.roles.size();
    }

    @Override
    public int numEmployees() {
        return this.employees.size();
    }

    @Override
    public int numEmployeesByRole(String roleId) {
        return roles.get(roleId).getNumEmployees();
    }

    @Override
    public int numRooms() {
        return this.rooms.size();
    }

    @Override
    public int numEquipments() {
        return this.equipments.size();
    }

    @Override
    public int numEquipmentsByRoom(String roomId) {
        return this.rooms.get(roomId).numEquipments();
    }

    @Override
    public int numFollowers(String employeeId) {
        Employee employee = getEmployee(employeeId);
        if (employee == null) return 0;

        Vertex<Employee> employeeVertex = socialNetwork.getVertex(employee);
        Iterator<Edge<String, Employee>> edges = socialNetwork.edgedWithDestination(employeeVertex);

        int count = 0;
        while (edges.hasNext()) {
            edges.next();
            count++;
        }

        return count;

    }

    @Override
    public int numFollowings(String employeeId) {
        Employee employee = getEmployee(employeeId);
        if (employee != null) {
            Vertex<Employee> employeeVertex = socialNetwork.getVertex(employee);
            Iterator<Edge<String, Employee>> edges = socialNetwork.edgesWithSource(employeeVertex);

            int count = 0;
            while (edges.hasNext()) {
                edges.next();
                count++;
            }

            return count;

        } else return 0;
    }

    @Override
    public int numRoomsByEmployee(String employee) {
        return this.employees.get(employee).numAssignedRooms();
    }

    @Override
    public Room whereIs(String equipmentId) {
        return equipments.get(equipmentId).getRoom();
    }

    @Override
    public Role getRole(String role) {
        return roles.get(role);
    }

    @Override
    public Employee getEmployee(String employeeId) {
        return employees.get(employeeId);
    }

    @Override
    public Room getRoom(String roomId) {
        return rooms.get(roomId);
    }

    @Override
    public Equipment getEquipment(String equipmentId) {
        return equipments.get(equipmentId);
    }
}
