package persistencies

import entities.CandidateDTO
import entities.SkillsDTO
import groovy.sql.Sql
import groovy.transform.Canonical

import java.sql.SQLException

@Canonical
class SkillsDAO {

    Sql sql

    List<SkillsDTO> findAll() throws SQLException {
        def results = sql.rows("SELECT * FROM competencias")
        List<SkillsDTO> skills = []
        results.each { row ->
            SkillsDTO skill = new SkillsDTO(id: row.id, name: row.nome)
            skills.add(skill)
        }
        return skills
    }

    SkillsDTO findById(int id) throws SQLException {
        def result = sql.firstRow("SELECT * FROM competencias WHERE id = ?", id)
        SkillsDTO skill = new SkillsDTO(id: result.id, name: result.nome)
        return result ? skill : null
    }

    boolean save(SkillsDTO skill) throws SQLException {
        def result = sql.executeInsert("INSERT INTO competencias (nome) VALUES (?)",
                [skill.getName()])
        return result ? true : false
    }

    boolean deleteById(int id) throws SQLException {
        return sql.execute("DELETE FROM competencias WHERE id = ?", [id])
    }

    boolean updateById(SkillsDTO skill) throws SQLException {
        return sql.execute("UPDATE competencias SET nome = ? WHERE id = ?",
                [skill.getName(), skill.getId()])
    }
}
