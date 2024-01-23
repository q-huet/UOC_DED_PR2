package uoc.ds.pr;

import edu.uoc.ds.traversal.*;
import uoc.ds.pr.exceptions.*;
import uoc.ds.pr.model.*;

import java.time.*;

public class CTTCompaniesJobsPR2Impl implements CTTCompaniesJobsPR2 {



    @Override
    public void addWorker(String id, String name, String surname, LocalDate dateOfBirth, Qualification qualification) {

    }

    @Override
    public void addCompany(String id, String name, String description) {

    }

    @Override
    public void addRequest(String id, String jobOfferId, String companyId, String description, Qualification minQualification, int maxWorkers, LocalDate startDate, LocalDate endDate) throws CompanyNotFoundException {

    }

    @Override
    public Request updateRequest(Status status, LocalDate date, String description) throws NoRequestException {
        return null;
    }

    @Override
    public Response signUpJobOffer(String workerId, String jobOfferId) throws JobOfferNotFoundException, WorkerNotFoundException, WorkerAlreadyEnrolledException {
        return null;
    }

    @Override
    public double getPercentageRejectedRequests() {
        return 0;
    }

    @Override
    public Iterator<JobOffer> getJobOffersByCompany(String companyId) throws NOJobOffersException {
        return null;
    }

    @Override
    public Iterator<JobOffer> getAllJobOffers() throws NOJobOffersException {
        return null;
    }

    @Override
    public Iterator<JobOffer> getJobOffersByWorker(String workerId) throws NOJobOffersException {
        return null;
    }

    @Override
    public void addRating(String workerId, String jobOfferId, int value, String message) throws WorkerNotFoundException, JobOfferNotFoundException, WorkerNOEnrolledException {

    }

    @Override
    public Iterator<Rating> getRatingsByJobOffer(String jobOfferId) throws JobOfferNotFoundException, NORatingsException {
        return null;
    }

    @Override
    public Worker getMostActiveWorker() throws NoWorkerException {
        return null;
    }

    @Override
    public JobOffer getBestJobOffer() throws NOJobOffersException {
        return null;
    }

    @Override
    public Worker getWorker(String id) {
        return null;
    }

    @Override
    public Company getCompany(String id) {
        return null;
    }

    @Override
    public JobOffer getJobOffer(String jobOfferId) {
        return null;
    }

    @Override
    public int numWorkers() {
        return 0;
    }

    @Override
    public int numCompanies() {
        return 0;
    }

    @Override
    public int numJobOffers() {
        return 0;
    }

    @Override
    public int numPendingRequests() {
        return 0;
    }

    @Override
    public int numTotalRequests() {
        return 0;
    }

    @Override
    public int numRejectedRequests() {
        return 0;
    }

    @Override
    public void addRole(String roleId, String description) {

    }

    @Override
    public void addEmployee(String employeeId, String name, String surname, LocalDate localDate, String role) {

    }

    @Override
    public void addRoom(String roomId, String name, String description, RoomType roomtype) {

    }

    @Override
    public void assignEmployee(String employeeId, String roomId) throws EmployeeAlreadyAssignedException, EmployeeNotFoundException, RoomNotFoundException {

    }

    @Override
    public Iterator<Employee> getEmployeesByRoom(String roomId) throws RoomNotFoundException, NOEmployeeException {
        return null;
    }

    @Override
    public Iterator<Employee> getEmployeesByRole(String roleId) throws NOEmployeeException {
        return null;
    }

    @Override
    public void addEquipment(String equipmentId, String name, String description) {

    }

    @Override
    public AssignEquipmentResponse assignEquipment(String equipmentId, String roomId) throws EquipmentNotFoundException, RoomNotFoundException, EquipmentAlreadyAssignedException {
        return null;
    }

    @Override
    public Level getLevel(String workerId) throws WorkerNotFoundException {
        return null;
    }

    @Override
    public Iterator<Enrollment> getWorkersByJobOffer(String jobOfferId) throws JobOfferNotFoundException, NoWorkerException {
        return null;
    }

    @Override
    public Iterator<Enrollment> getSubstitutesByJobOffer(String jobOfferId) throws JobOfferNotFoundException, NoWorkerException {
        return null;
    }

    @Override
    public Iterator<Room> getRoomsWithoutEmployees() throws NoRoomsException {
        return null;
    }

    @Override
    public Iterator<Room> best5EquippedRooms() throws NoRoomsException {
        return null;
    }

    @Override
    public void addFollower(String followerId, String followedId) throws FollowerNotFound, FollowedException {

    }

    @Override
    public Iterator<Employee> getFollowers(String followedId) throws EmployeeNotFoundException, NoFollowersException, FollowerNotFound {
        return null;
    }

    @Override
    public Iterator<Employee> getFollowings(String followerId) throws EmployeeNotFoundException, NoFollowedException {
        return null;
    }

    @Override
    public Iterator<Employee> recommendations(String followerId) throws EmployeeNotFoundException, NoFollowedException {
        return null;
    }

    @Override
    public Iterator<Employee> getUnfollowedColleagues(String employeeId) throws EmployeeNotFoundException, NOEmployeeException {
        return null;
    }

    @Override
    public int numRoles() {
        return 0;
    }

    @Override
    public int numEmployees() {
        return 0;
    }

    @Override
    public int numEmployeesByRole(String roleId) {
        return 0;
    }

    @Override
    public int numRooms() {
        return 0;
    }

    @Override
    public int numEquipments() {
        return 0;
    }

    @Override
    public int numEquipmentsByRoom(String roomId) {
        return 0;
    }

    @Override
    public int numFollowers(String employeeId) {
        return 0;
    }

    @Override
    public int numFollowings(String employeeId) {
        return 0;
    }

    @Override
    public int numRoomsByEmployee(String employee) {
        return 0;
    }

    @Override
    public Room whereIs(String equipmentId) {
        return null;
    }

    @Override
    public Role getRole(String role) {
        return null;
    }

    @Override
    public Employee getEmployee(String employeeId) {
        return null;
    }

    @Override
    public Room getRoom(String roomId) {
        return null;
    }

    @Override
    public Equipment getEquipment(String equipmentId) {
        return null;
    }
}
