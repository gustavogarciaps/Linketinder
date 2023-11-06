package services

import domain.Jobs
import domain.Skills
import repository.JobsSkillsDAO
import utils.OperationStatus

class JobsSkillsService {

    private final JobsSkillsDAO jobsSkillsDAO

    JobsSkillsService(JobsSkillsDAO jobsSkillsDAO) {
        this.jobsSkillsDAO = jobsSkillsDAO
    }

    OperationStatus save(Jobs job, Skills skill) {
        return jobsSkillsDAO.save(job, skill);
    }

}
