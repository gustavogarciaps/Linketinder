package entities

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
    List<SkillsDTO> skills = new ArrayList<>()

    void addSkills(SkillsDTO skill) {
        this.skills.push(skill)
    }

}
