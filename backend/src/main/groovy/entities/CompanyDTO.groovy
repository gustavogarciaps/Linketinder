package entities

import groovy.transform.Canonical

import java.time.LocalDate

@Canonical
class CompanyDTO extends PersonDTO{

    String cnpj
    LocalDate creationDate
    List<JobsDTO> jobs = new ArrayList<>()

    void addJobs (JobsDTO job){
        this.jobs.push(job)
    }

}
