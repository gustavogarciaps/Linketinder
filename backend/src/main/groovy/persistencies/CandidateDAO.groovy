package persistencies

import entities.CandidateDTO
import entities.SkillsDTO
import groovy.sql.Sql
import groovy.transform.Canonical

import java.sql.SQLException

@Canonical
class CandidateDAO {

    Sql sql

    List<CandidateDTO> findAll() {
        def results = sql.rows("SELECT * FROM candidatos AS c INNER JOIN usuarios AS u ON c.usuarios_id = u.id;")
        List<CandidateDTO> candidatesDTO = []
        results.each { row ->
            CandidateDTO candidate = new CandidateDTO(id: row.usuarios_id, name: row.nome, linkedin: row.linkedin)
            candidatesDTO.add(candidate)
        }
        return candidatesDTO
    }

    CandidateDTO findAll(CandidateDTO candidateDTO) throws SQLException {
        List<SkillsDTO> skillsDTOS = []
        sql.eachRow("SELECT * FROM candidatos_competencias AS cc INNER JOIN competencias AS cs ON cc.competencias_id = cs.id WHERE cc.candidatos_id = ?;", [candidateDTO.getId()]) { rs ->
            skillsDTOS.add(new SkillsDTO(id: rs.id, name: rs.nome))
        }
        candidateDTO.setSkills(skillsDTOS)

        return candidateDTO
    }

    CandidateDTO findById(int id) {
        def result = sql.firstRow("SELECT * FROM candidatos AS c INNER JOIN usuarios AS u ON c.usuarios_id = u.id WHERE id = ?", id)
        CandidateDTO candidate = new CandidateDTO(id: result.id, name: result.nome, linkedin: result.linkedin)
        return result ? candidate : null
    }

    boolean save(CandidateDTO candidate) {
        def result = sql.executeInsert("INSERT INTO candidatos (usuarios_id, nome, linkedin, data_nascimento) VALUES (?, ?, ?, ?)",
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


