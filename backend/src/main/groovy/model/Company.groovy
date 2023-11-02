package model

import groovy.transform.Canonical

import java.time.LocalDate

@Canonical
class Company extends Person{

    String cnpj
    LocalDate creationDate
    List<Jobs> jobs = new ArrayList<>()

    void addJobs (Jobs job){
        this.jobs.push(job)
    }

}
