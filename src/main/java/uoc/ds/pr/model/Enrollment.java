package uoc.ds.pr.model;

import uoc.ds.pr.CTTCompaniesJobs;

import java.util.*;

public class Enrollment implements Comparable<Enrollment> {

    //public static final Comparator<Enrollment> CMP_V = (e1, e2) -> Integer.compare(e1.getWorker().getLevel().getValue(), e2.getWorker().getLevel().getValue());
    public static final Comparator<Enrollment> CMP_V = Enrollment::compareTo;
    private Worker worker;
    private JobOffer jobOffer;

    private CTTCompaniesJobs.Response response;

    public Enrollment(JobOffer jobOffer, Worker worker, CTTCompaniesJobs.Response response) {
        this.jobOffer = jobOffer;
        this.worker = worker;
        this.response = response;
    }

    public Worker getWorker() {
        return this.worker;
    }

    @Override
    public int compareTo(Enrollment o) {
        return Integer.compare(this.worker.getLevel().getValue(), o.getWorker().getLevel().getValue());
    }
}
