package DAO

import entities.Candidate
import entities.Skills
import groovy.sql.Sql

import java.sql.SQLException

class CandidateSkillsDAO {

    Sql sql

    boolean save(Candidate candidateDTO, Skills skillsDTO) throws SQLException {
        List<List<Object>> result = sql.executeInsert("INSERT INTO candidatos_competencias (candidatos_id,competencias_id) VALUES (?, ?)",
                [candidateDTO.getId(),skillsDTO.getId()])
        return result ? true : false
    }

}
