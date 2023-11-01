package repository


import model.Jobs
import model.Skills
import groovy.sql.Sql

import java.sql.SQLException

class JobsSkillsDAO {

    Sql sql

    boolean save(Jobs job, Skills skill) throws SQLException {
        List<List<Object>> result = sql.executeInsert("INSERT INTO vagas_competencias (vagas_id,competencias_id) VALUES (?, ?)",
                [job.getId(), skill.getId()])
        return result ? true : false
    }
}
