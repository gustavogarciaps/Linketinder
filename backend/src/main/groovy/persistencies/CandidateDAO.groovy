package persistencies

import entities.CandidateDTO
import entities.SkillsDTO
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import groovy.transform.Canonical

import java.sql.SQLException

@Canonical
class CandidateDAO {

    Sql sql

    List<CandidateDTO> findAll() {
        GroovyRowResult results = sql.rows("SELECT * FROM candidatos AS c INNER JOIN usuarios AS u ON c.usuarios_id = u.id;") as GroovyRowResult
        List<CandidateDTO> candidates = []
        results.each { row ->
            CandidateDTO candidate = new CandidateDTO(id: row.usuarios_id, name: row.nome, linkedin: row.linkedin)
            candidates.add(candidate)
        }
        return candidates
    }

    CandidateDTO findAll(CandidateDTO candidate) throws SQLException {
        List<SkillsDTO> skills = []
        sql.eachRow("SELECT * FROM candidatos_competencias AS cc INNER JOIN competencias AS cs ON cc.competencias_id = cs.id WHERE cc.candidatos_id = ?;", [candidate.getId()]) { rs ->
            skills.add(new SkillsDTO(id: rs.id, name: rs.nome))
        }
        candidate.setSkills(skills)

        return candidate
    }

    CandidateDTO findById(int id) {
        GroovyRowResult result = sql.firstRow("SELECT * FROM candidatos AS c INNER JOIN usuarios AS u ON c.usuarios_id = u.id WHERE id = ?", id)
        CandidateDTO candidate = new CandidateDTO(id: result.id, name: result.razao_social, linkedin: result.linkedin)
        return result ? candidate : null
    }

    boolean save(CandidateDTO candidate) {
        List<List<Object>> result = sql.executeInsert("INSERT INTO candidatos (usuarios_id, nome, linkedin, data_nascimento) VALUES (?, ?, ?, ?)",
                [candidate.getId(), candidate.getName(), candidate.getLinkedin(), candidate.getDateOfBirth()])
        return result ? true : false
    }

    boolean deleteById(int id) {
        return sql.execute("DELETE FROM candidatos WHERE usuarios_id = ?", [id])
    }

    boolean updateById(CandidateDTO candidate) throws SQLException {
        return sql.execute("UPDATE candidatos SET nome = ?, sobrenome = ?, cpf = ?, descricao = ?, cidades_id = ?, cep = ?, formacao = ?, data_nascimento = ?, linkedin = ? WHERE usuarios_id = ?",
                [candidate.getName(), candidate.getName(), candidate.getCpf(), candidate.getDescription(), candidate.getCity().toInteger(), candidate.getZipCode(), candidate.getAcademicEduation(), candidate.getDateOfBirth(), candidate.getLinkedin(), candidate.getId()])
    }

}


