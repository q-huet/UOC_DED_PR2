package uoc.ds.pr.model;

import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.adt.sequential.List;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.CTTCompaniesJobs;
import uoc.ds.pr.CTTCompaniesJobsPR2;
import uoc.ds.pr.CTTCompaniesJobs.Qualification;
import uoc.ds.pr.CTTCompaniesJobsPR2.Level;
import uoc.ds.pr.util.*;

import java.time.LocalDate;
import java.util.*;

public class Worker implements Comparable<Worker> {
    public static final Comparator<Worker> CMP_V = (w1, w2) -> Integer.compare(w1.getLevel().getValue(), w2.getLevel().getValue());
    private String id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private Qualification qualification;

    private List<JobOffer> jobOffers;
    private int workingDays;

    private Level level;
    private int workedHours;

    public Worker(String id, String name, String surname,
                  LocalDate dateOfBirth, Qualification qualification) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.qualification = qualification;
        this.level = Level.BEFINNER;
        this.jobOffers = new LinkedList<>();
        this.workingDays = 0;
        this.workedHours = 0;
    }

    public void update(String name, String surname,
                       LocalDate dateOfBirth, CTTCompaniesJobs.Qualification qualification) {
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.qualification = qualification;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public Level getLevel() {
        return level;
    }
    public int getWorkingDays() {
        return this.workingDays;
    }
    public Iterator<JobOffer> getJobOffers() {
        return jobOffers.values();
    }

    public void addJobOffer(JobOffer jobOffer) {
        this.workingDays += jobOffer.getWorkingDays();
        this.workedHours = (workingDays * CTTCompaniesJobsPR2.HOURS_PER_DAY);
        this.jobOffers.insertEnd(jobOffer);
        setLevel();
    }

    public void setLevel() {
        this.level = LevelHelper.getLevel(this.workedHours);
    }



    /*
    public boolean isInJobOffer(JobOffer jobOffer) {
        Iterator<Enrollment> it = jobOffer.enrollments();
        return isInJobOffer(it);
    }*/
    public boolean isInJobOffer(Object obj) {
        if (obj instanceof JobOffer) {
            Iterator<Enrollment> it = ((JobOffer) obj).enrollments();
            return isInJobOffer(it);
        } else if (obj instanceof Iterator<?>) {
            Iterator<Enrollment> it = (Iterator<Enrollment>) obj;
            boolean found = false;
            while (!found && it.hasNext()) {
                Enrollment enrollment = it.next();
                found = enrollment.getWorker().getId().equals(this.id);
            }
            return found;
        }
        return false;
    }

    public boolean isInJobOfferAsSubstitute(JobOffer jobOffer) {
        Iterator<Enrollment> it = jobOffer.substitutes();
        return isInJobOffer(it);
    }


    public boolean isInJobOffer(Iterator<Enrollment> it) {
        boolean found = false;

        Enrollment enrollment = null;
        while (!found && it.hasNext()) {
            enrollment = it.next();
            found = enrollment.getWorker().getId().equals(this.id);
        }
        return found;
    }


    @Override
    public int compareTo(Worker o) {
        return Integer.compare(this.getLevel().getValue(), o.getLevel().getValue());
    }
}
