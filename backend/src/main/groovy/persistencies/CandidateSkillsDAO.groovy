package persistencies

import entities.CandidateDTO
import entities.SkillsDTO
import groovy.sql.Sql

import java.sql.SQLException

class CandidateSkillsDAO {

    Sql sql



    boolean save(CandidateDTO candidateDTO, SkillsDTO skillsDTO) throws SQLException {
        def result = sql.executeInsert("INSERT INTO candidatos_competencias (candidatos_id,competencias_id) VALUES (?, ?)",
                [candidateDTO.getId(),skillsDTO.getId()])
        return result ? true : false
    }

}
