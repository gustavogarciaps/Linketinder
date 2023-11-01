package repository

import model.Skills
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import utils.OperationStatus

import java.sql.SQLException

class SkillsDAO implements InterfaceDatabaseDML<Skills> {

    private static final SELECT_ALL_SKILLS = "SELECT * FROM competencias"
    private static final SELECT_SKILL_BY_ID = "SELECT * FROM competencias WHERE id = ?"
    private static final INSERT_SKILL = "INSERT INTO competencias (nome) VALUES (?)"
    private static final DELETE_SKILL = "DELETE FROM competencias WHERE id = ?"
    private static final UPDATE_SKILL = "UPDATE competencias SET nome = ? WHERE id = ?"

    Sql sql

    SkillsDAO(Sql sql) {
        this.sql = sql
    }

    List<Skills> findAll() throws SQLException {
        List<Skills> skills = []

        sql.rows(SELECT_ALL_SKILLS).each { row ->
            Skills skill = new Skills(id: row.id, name: row.nome)
            skills.add(skill)
        }
        return skills
    }

    Skills findById(int id) throws SQLException {
        GroovyRowResult result = sql.firstRow(SELECT_SKILL_BY_ID, id)

        Skills skill = new Skills(id: result.id, name: result.nome)
        return skill
    }

    OperationStatus save(Skills skill) {
        List<Object> arguments = [skill.getName()]
        return DatabaseExecute.executeTransaction(sql, INSERT_SKILL, arguments)
    }

    OperationStatus deleteById(int id){
        List<Object> arguments =[id]
        return DatabaseExecute.executeTransaction(sql, DELETE_SKILL, arguments)
    }

    OperationStatus updateById(Skills skill){
        List<Object> arguments = [skill.getName(), skill.getId()]
        return DatabaseExecute.executeTransaction(sql, UPDATE_SKILL, arguments)
    }
}
