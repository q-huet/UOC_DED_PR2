package uoc.ds.pr.model;

import edu.uoc.ds.adt.sequential.LinkedList;
import edu.uoc.ds.adt.sequential.List;
import edu.uoc.ds.traversal.Iterator;

public class Company {
    private String id;
    private String name;
    private String description;
    private List<JobOffer> jobOffers;

    public Company(String id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.jobOffers = new LinkedList<>();
    }

    public void update(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public void addJobOffer(JobOffer jobOffer) {
        this.jobOffers.insertEnd(jobOffer);
    }

    public Iterator<JobOffer> getJobOffers() {
        return this.jobOffers.values();
    }
}
