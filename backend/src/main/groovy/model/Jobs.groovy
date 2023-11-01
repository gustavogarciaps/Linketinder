package model

import groovy.transform.Canonical

import java.time.LocalDate

@Canonical
class Jobs {

    Integer id
    Company company
    String title
    String description
    String modality
    String city
    String state
    String country
    LocalDate dateOfCreate
    List<Skills> skills = new ArrayList<>()

    void addSkills(Skills skill) {
        this.skills.push(skill)
    }

}
