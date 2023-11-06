package repository.dto

import domain.Candidate
import repository.CandidateDAO

class CandidateDTO {

    private final CandidateDAO candidateDAO

    CandidateDTO(CandidateDAO candidateDAO) {
        this.candidateDAO = candidateDAO
    }

    List<Candidate> findAll() {
        List<Candidate> result = []

        candidateDAO.findAll().forEach { candidate ->
            result.add(new Candidate(id: candidate.getId(), academicEducation: candidate.getAcademicEducation()))
        }
        return result
    }
}
