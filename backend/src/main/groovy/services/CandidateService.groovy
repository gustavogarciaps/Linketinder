package services

import repository.CandidateDAO
import model.Candidate
import repository.dto.CandidateDTO
import utils.OperationStatus

class CandidateService {

    private final CandidateDAO candidateDAO

    CandidateService(CandidateDAO candidateDAO) {
        this.candidateDAO = candidateDAO
    }

    List<Candidate> findAll() {
        List<Candidate> result = candidateDAO.findAll()
        return result
    }

    Candidate findAll(Candidate candidate) {
        Candidate result = candidateDAO.findAll(candidate)
        return result
    }

    Candidate findById(int id) {
        Candidate result = candidateDAO.findById(id)
        return result
    }

    OperationStatus save(Candidate candidate) {
        return candidateDAO.save(candidate);
    }

    OperationStatus updateById(Candidate candidate){
        return candidateDAO.updateById(candidate)
    }

    OperationStatus deleteById(int id){
        return candidateDAO.deleteById(id)
    }
}
