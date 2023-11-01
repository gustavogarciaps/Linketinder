package services

import model.Candidate
import model.Skills
import repository.CandidateDAO
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
