package services

import domain.Candidate
import domain.Skills
import repository.CandidateSkillsDAO
import utils.OperationStatus

class CandidateSkillsService {

    private final CandidateSkillsDAO candidateSkillsDAO

    CandidateSkillsService(CandidateSkillsDAO candidateSkillsDAO) {
        this.candidateSkillsDAO = candidateSkillsDAO
    }

    OperationStatus save(Candidate candidate, Skills skill) {
        return candidateSkillsDAO.save(candidate, skill);
    }

}
