package entities

import groovy.transform.Canonical

@Canonical
class CompanyLikes implements InterfacePersonLikes {

    CompanyDTO company
    List<CandidateDTO> candidates = new ArrayList<>()

    @Override
    void like(PersonDTO person) {
        this.candidates.push(person as CandidateDTO)
    }

}

