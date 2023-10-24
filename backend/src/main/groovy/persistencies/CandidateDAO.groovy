package persistencies

import entities.CandidateDTO
import groovy.sql.Sql
import groovy.transform.Canonical

@Canonical
class CandidateDAO {

    Sql sql

    List<CandidateDTO> findAll() {
        def results = sql.rows("SELECT * FROM candidatos AS c INNER JOIN usuarios AS u ON c.usuarios_id = u.id;")
        List<CandidateDTO> candidates = []
        results.each { row ->
            CandidateDTO candidate = new CandidateDTO(id: row.usuarios_id, name: row.nome, linkedin: row.linkedin)
            candidates.add(candidate)
        }
        return candidates
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
        def result = sql.execute("DELETE FROM candidatos WHERE usuarios_id = ?", [c.getId()])
        return result
    }

}


