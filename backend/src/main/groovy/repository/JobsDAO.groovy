package repository

import domain.Company
import domain.Jobs
import domain.Skills
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import repository.connection.DatabaseExecute
import utils.OperationStatus

import java.sql.SQLException

class JobsDAO implements InterfaceDatabaseDML<Jobs>{

    private static final String SELECT_ALL_JOBS = "SELECT * FROM vagas;"
    private static final String SELECT_JOB_BY_ID = "SELECT * FROM vagas WHERE id = ?"
    private static final String SELECT_JOB_SKILL = "SELECT * FROM vagas_competencias AS vc INNER JOIN competencias AS cs ON vc.competencias_id = cs.id WHERE vc.vagas_id = ?;"
    private static final String INSERT_JOB = "INSERT INTO vagas (empresas_id, titulo, descricao, cidades_id) VALUES (?,?,?,?)"
    private static final String DELETE_JOB = "DELETE FROM vagas WHERE id = ?"
    private static final String UPDATE_JOB = "UPDATE vagas SET titulo =?, descricao=?, modalidades_id =?, cidades_id=? WHERE id = ?"

    Sql sql

    JobsDAO(Sql sql) {
        this.sql = sql
    }

    List<Jobs> findAll() {
        List<Jobs> jobs = []

        sql.rows(SELECT_ALL_JOBS).each { row ->
            Jobs job = new Jobs(id: row.id as Integer, company: new Company(id: row.empresas_id as Integer), title: row.titulo, description: row.descricao, city: row.cidades_id)
            jobs.add(job)
        }
        return jobs
    }

    Jobs findAll(Jobs job) throws SQLException {
        List<Skills> skills = []

        sql.eachRow(SELECT_JOB_SKILL, [job.getId()]) { rs ->
            skills.add(new Skills(id: rs.competencias_id, name: rs.nome))
        }
        job.setSkills(skills)

        return job
    }

    Jobs findById(int id) {
        GroovyRowResult result = sql.firstRow(SELECT_JOB_BY_ID, id)

        Jobs job = new Jobs(id: result.id as Integer, company: new Company(id: result.empresas_id as Integer), title: result.titulo, description: result.descricao, city: result.cidades_id)
        return job
    }

    OperationStatus save(Jobs job) {
        List<Object> arguments = [job.getCompany().getId(), job.getTitle(), job.getDescription(), job.getCity().toInteger()]
        return DatabaseExecute.executeTransaction(sql, INSERT_JOB, arguments)
    }

    OperationStatus deleteById(int id) {
        List<Object> arguments = [id]
        return DatabaseExecute.executeTransaction(sql, DELETE_JOB, arguments)
    }

    OperationStatus updateById(Jobs job) {
        List<Object> arguments = [job.getTitle(), job.getDescription(), 1, job.getCity().toInteger(), job.getId()]
        return DatabaseExecute.executeTransaction(sql, UPDATE_JOB, arguments)
    }
}
