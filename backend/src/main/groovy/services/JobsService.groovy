package services

import domain.Jobs
import repository.JobsDAO
import utils.OperationStatus

class JobsService {

    private final JobsDAO jobsDAO

    JobsService(JobsDAO jobsDAO) {
        this.jobsDAO = jobsDAO
    }

    List<Jobs> findAll() {
        List<Jobs> result = jobsDAO.findAll()
        return result
    }

    Jobs findAll(Jobs job) {
        Jobs result = jobsDAO.findAll(job)
        return result
    }

    Jobs findById(int id) {
        Jobs result = jobsDAO.findById(id)
        return result
    }

    OperationStatus save(Jobs job) {
        return jobsDAO.save(job);
    }

    OperationStatus updateById(Jobs job){
        return jobsDAO.updateById(job)
    }

    OperationStatus deleteById(int id){
        return jobsDAO.deleteById(id)
    }
}
