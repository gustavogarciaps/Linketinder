package repository


import model.Jobs
import model.Skills
import groovy.sql.Sql
import utils.OperationStatus

import java.sql.SQLException

class JobsSkillsDAO {

    private static final String INSERT_JOB_SKILL = "INSERT INTO vagas_competencias (vagas_id,competencias_id) VALUES (?, ?)"

    private Sql sql

    JobsSkillsDAO(Sql sql) {
        this.sql = sql
    }

    OperationStatus save(Jobs job, Skills skill) {
        List<Object> arguments = [job.getId(), skill.getId()]
        return DatabaseExecute.executeTransaction(sql, INSERT_JOB_SKILL, arguments)
    }
}
