package persistencies

import entities.CandidateDTO
import entities.JobsDTO
import entities.SkillsDTO
import groovy.sql.Sql

import java.sql.SQLException

class JobsSkillsDAO {

    Sql sql

    boolean save(JobsDTO job, SkillsDTO skill) throws SQLException {
        List<List<Object>> result = sql.executeInsert("INSERT INTO vagas_competencias (vagas_id,competencias_id) VALUES (?, ?)",
                [job.getId(), skill.getId()])
        return result ? true : false
    }
}
