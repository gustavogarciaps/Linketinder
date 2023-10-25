package entities

import groovy.transform.Canonical

import java.time.LocalDate

@Canonical
class CandidateDTO extends PersonDTO {

    String linkedin
    String cpf
    LocalDate dateOfBirth
    String academicEduation
    List<SkillsDTO> skills = new ArrayList<>()

    void addSkills(SkillsDTO skill) {
        this.skills.push(skill)
    }

}
