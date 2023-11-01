package repository


import model.Company
import model.Jobs
import model.Skills
import groovy.sql.GroovyRowResult
import groovy.sql.Sql

import java.sql.SQLException

class JobsDAO {

    Sql sql

    List<Jobs> findAll() {
        List<GroovyRowResult> results = sql.rows("SELECT * FROM vagas;")
        List<Jobs> jobs = []
        results.each { row ->
            Jobs job = new Jobs(id: row.id as Integer, company: new Company(id: row.empresas_id as Integer), title: row.titulo, description: row.descricao, city: row.cidades_id)
            jobs.add(job)
        }
        return jobs
    }

    Jobs findAll(Jobs job) throws SQLException {
        List<Skills> skills = []
        sql.eachRow("SELECT * FROM vagas_competencias AS vc INNER JOIN competencias AS cs ON vc.competencias_id = cs.id WHERE vc.vagas_id = ?;", [job.getId()]) { rs ->
            skills.add(new Skills(id: rs.competencias_id, name: rs.nome))
        }
        job.setSkills(skills)

        return job
    }

    Jobs findById(int id) {
        GroovyRowResult result = sql.firstRow("SELECT * FROM vagas WHERE id = ?", id)
        Jobs job = new Jobs(id: result.id as Integer, company: new Company(id: result.empresas_id as Integer), title: result.titulo, description: result.descricao, city: result.cidades_id)
        return result ? job : null
    }

    boolean save(Jobs job) {
        List<List<Object>> result = sql.executeInsert("INSERT INTO vagas (empresas_id, titulo, descricao, cidades_id) VALUES (?,?,?,?)",
                [job.getCompany().getId(), job.getTitle(), job.getDescription(), job.getCity().toInteger()])
        return result ? true : false
    }

    boolean deleteById(int id) {
        return sql.execute("DELETE FROM vagas WHERE id = ?", [id])
    }

    boolean updateById(Jobs job) throws SQLException {
        return sql.execute("UPDATE vagas SET titulo =?, descricao=?, modalidades_id =?, cidades_id=? WHERE id = ?",
                [job.getTitle(), job.getTitle(), 1, job.getCity().toInteger(), job.getId()])
    }
}
