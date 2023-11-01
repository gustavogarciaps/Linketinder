package repository


import model.Skills
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import groovy.transform.Canonical

import java.sql.SQLException

@Canonical
class SkillsDAO {

    Sql sql

    List<Skills> findAll() throws SQLException {
        List<GroovyRowResult> results = sql.rows("SELECT * FROM competencias")
        List<Skills> skills = []
        results.each { row ->
            Skills skill = new Skills(id: row.id, name: row.nome)
            skills.add(skill)
        }
        return skills
    }

    Skills findById(int id) throws SQLException {
        GroovyRowResult result = sql.firstRow("SELECT * FROM competencias WHERE id = ?", id)
        Skills skill = new Skills(id: result.id, name: result.nome)
        return result ? skill : null
    }

    boolean save(Skills skill) throws SQLException {
        List<List<Object>> result = sql.executeInsert("INSERT INTO competencias (nome) VALUES (?)",
                [skill.getName()])
        return result ? true : false
    }

    boolean deleteById(int id) throws SQLException {
        return sql.execute("DELETE FROM competencias WHERE id = ?", [id])
    }

    boolean updateById(Skills skill) throws SQLException {
        return sql.execute("UPDATE competencias SET nome = ? WHERE id = ?",
                [skill.getName(), skill.getId()])
    }
}
