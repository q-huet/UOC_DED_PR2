package uoc.ds.pr.model;

import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.adt.sequential.List;
import edu.uoc.ds.traversal.Iterator;
import uoc.ds.pr.CTTCompaniesJobs;
import uoc.ds.pr.CTTCompaniesJobsPR2;

import java.time.LocalDate;
import java.util.*;

public class Worker implements Comparable<Worker> {
    public static final Comparator<Worker> CMP_V = (w1, w2) -> Integer.compare(w1.getLevel().getValue(), w2.getLevel().getValue());
    private String id;
    private String name;
    private String surname;
    private LocalDate dateOfBirth;
    private CTTCompaniesJobs.Qualification qualification;

    private List<JobOffer> jobOffers;
    private int workingDays;
    private CTTCompaniesJobsPR2.Level level;
    private int workedHours;

    public Worker(String id, String name, String surname,
                  LocalDate dateOfBirth, CTTCompaniesJobs.Qualification qualification) {
        this.setId(id);
        this.setName(name);
        this.setSurname(surname);
        this.setDateOfBirth(dateOfBirth);
        this.setQualification(qualification);
        this.jobOffers = new LinkedList<>();
        this.workingDays = 0;
        this.workedHours = 0;
    }


    public void update(String name, String surname,
                       LocalDate dateOfBirth, CTTCompaniesJobs.Qualification qualification) {
        this.setName(name);
        this.setSurname(surname);
        this.setDateOfBirth(dateOfBirth);
        this.setQualification(qualification);
    }

    private void setQualification(CTTCompaniesJobs.Qualification qualification) {
        this.qualification = qualification;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
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

    public CTTCompaniesJobs.Qualification getQualification() {
        return qualification;
    }


    public void addJobOffer(JobOffer jobOffer) {
        this.workingDays += jobOffer.getWorkingDays();
        this.workedHours += (jobOffer.getWorkingDays() * CTTCompaniesJobsPR2.HOURS_PER_DAY);
        this.jobOffers.insertEnd(jobOffer);
    }

    public CTTCompaniesJobsPR2.Level getLevel() {

        if (workedHours < 10)
            this.level = CTTCompaniesJobsPR2.Level.BEFINNER;
        if (workedHours >= 10 && workedHours < 200)
            this.level = CTTCompaniesJobsPR2.Level.INTERN;
        if (workedHours >= 200 && workedHours < 500)
            this.level = CTTCompaniesJobsPR2.Level.JUNIOR;
        if (workedHours >= 500 && workedHours < 1000)
            this.level = CTTCompaniesJobsPR2.Level.SENIOR;
        if (workedHours >= 1000)
            this.level = CTTCompaniesJobsPR2.Level.EXPERT;

        return this.level;
    }

    public Iterator<JobOffer> getJobOffers() {
        return jobOffers.values();
    }

    public boolean isInJobOffer(JobOffer jobOffer) {
        Iterator<Enrollment> it = jobOffer.enrollments();
        return isInJobOffer(it);
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

    public int getWorkingDays() {
        return this.workingDays;
    }

    @Override
    public int compareTo(Worker o) {
        return Integer.compare(this.getLevel().getValue(), o.getLevel().getValue());
    }
}
