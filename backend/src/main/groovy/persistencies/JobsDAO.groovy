package persistencies

import entities.CandidateDTO
import entities.CompanyDTO
import entities.JobsDTO
import entities.SkillsDTO
import groovy.sql.GroovyRowResult
import groovy.sql.Sql

import java.sql.SQLException

class JobsDAO {

    Sql sql

    List<JobsDTO> findAll() {
        List<GroovyRowResult> results = sql.rows("SELECT * FROM vagas;")
        List<JobsDTO> jobs = []
        results.each { row ->
            JobsDTO job = new JobsDTO(id: row.id as Integer, company: new CompanyDTO(id: row.empresas_id as Integer), title: row.titulo, description: row.descricao, city: row.cidades_id)
            jobs.add(job)
        }
        return jobs
    }

    JobsDTO findAll(JobsDTO job) throws SQLException {
        List<SkillsDTO> skills = []
        sql.eachRow("SELECT * FROM vagas_competencias AS vc INNER JOIN competencias AS cs ON vc.competencias_id = cs.id WHERE vc.vagas_id = ?;", [job.getId()]) { rs ->
            skills.add(new SkillsDTO(id: rs.competencias_id, name: rs.nome))
        }
        job.setSkills(skills)

        return job
    }

    JobsDTO findById(int id) {
        GroovyRowResult result = sql.firstRow("SELECT * FROM vagas WHERE id = ?", id)
        JobsDTO job = new JobsDTO(id: result.id as Integer, company: new CompanyDTO(id: result.empresas_id as Integer), title: result.titulo, description: result.descricao, city: result.cidades_id)
        return result ? job : null
    }

    boolean save(JobsDTO job) {
        List<List<Object>> result = sql.executeInsert("INSERT INTO vagas (empresas_id, titulo, descricao, cidades_id) VALUES (?,?,?,?)",
                [job.getCompany().getId(), job.getTitle(), job.getDescription(), job.getCity().toInteger()])
        return result ? true : false
    }

    boolean deleteById(int id) {
        return sql.execute("DELETE FROM vagas WHERE id = ?", [id])
    }

    boolean updateById(JobsDTO job) throws SQLException {
        return sql.execute("UPDATE vagas SET titulo =?, descricao=?, modalidades_id =?, cidades_id=? WHERE id = ?",
                [job.getTitle(), job.getTitle(), 1, job.getCity().toInteger(), job.getId()])
    }
}
