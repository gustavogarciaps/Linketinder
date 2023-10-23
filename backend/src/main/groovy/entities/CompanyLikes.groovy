package entities

import groovy.transform.Canonical

@Canonical
class CompanyLikes implements InterfacePersonLikes {

    Company company
    List<Candidate> candidates = new ArrayList<>()

    @Override
    void like(PersonDTO person) {
        this.candidates.push(person as Candidate)
    }

}

