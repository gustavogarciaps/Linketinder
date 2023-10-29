package entities

import groovy.transform.Canonical

import java.time.LocalDate

@Canonical
class Candidate extends Person {

    String linkedin
    String cpf
    LocalDate dateOfBirth
    String academicEducation
    List<Skills> skills = new ArrayList<>()

    void addSkills(Skills skill) {
        this.skills.push(skill)
    }

}
