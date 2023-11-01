package repository

import model.Candidate
import model.Skills
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import groovy.transform.Canonical
import utils.OperationStatus

import java.sql.SQLException

class CandidateDAO implements InterfaceDatabaseDML<Candidate> {

    private static final String SELECT_ALL_CANDIDATES = "SELECT * FROM candidatos AS c INNER JOIN usuarios AS u ON c.usuarios_id = u.id;";
    private static final String SELECT_CANDIDATE_SKILLS = "SELECT * FROM candidatos_competencias AS cc INNER JOIN competencias AS cs ON cc.competencias_id = cs.id WHERE cc.candidatos_id = ?;";
    private static final String SELECT_CANDIDATE_BY_ID = "SELECT * FROM candidatos AS c INNER JOIN usuarios AS u ON c.usuarios_id = u.id WHERE id = ?";
    private static final String INSERT_CANDIDATE = "INSERT INTO candidatos (usuarios_id, nome, linkedin, data_nascimento) VALUES (?, ?, ?, ?)";
    private static final String DELETE_CANDIDATE = "DELETE FROM candidatos WHERE usuarios_id = ?"
    private static final String UPDATE_CANDIDATE = "UPDATE candidatos SET nome = ?, sobrenome = ?, cpf = ?, descricao = ?, cidades_id = ?, cep = ?, formacao = ?, data_nascimento = ?, linkedin = ? WHERE usuarios_id = ?"

    private Sql sql

    CandidateDAO(Sql sql) {
        this.sql = sql
    }

    List<Candidate> findAll() throws SQLException {
        List<Candidate> candidates = []

        sql.rows(SELECT_ALL_CANDIDATES).each { row ->
            Candidate candidate = new Candidate(id: row.usuarios_id, name: row.nome, linkedin: row.linkedin)
            candidates.add(candidate)
        }
        return candidates
    }

    Candidate findAll(Candidate candidate) throws SQLException {
        List<Skills> skills = []

        sql.eachRow(SELECT_CANDIDATE_SKILLS, [candidate.getId()]) { row ->
            skills.add(new Skills(id: row.id, name: row.nome))
        }
        candidate.setSkills(skills)

        return candidate
    }

    Candidate findById(int id) throws SQLException {
        GroovyRowResult result = sql.firstRow(SELECT_CANDIDATE_BY_ID, [id])

        Candidate candidate = new Candidate(id: result.id, name: result.nome, linkedin: result.linkedin)
        return candidate
    }

    OperationStatus save(Candidate candidate) {
        List<Object> arguments = [candidate.getId(), candidate.getName(), candidate.getLinkedin(), candidate.getDateOfBirth()]
        return executeTransaction(INSERT_CANDIDATE, arguments)
    }

    OperationStatus deleteById(int id) {
        List<Object> arguments = [id]
        return executeTransaction(DELETE_CANDIDATE, arguments)
    }

    OperationStatus updateById(Candidate candidate) {
        List<Object> arguments = [candidate.getName(), candidate.getName(), candidate.getCpf(), candidate.getDescription(), candidate.getCity().toInteger(), candidate.getZipCode(), candidate.getAcademicEducation(), candidate.getDateOfBirth(), candidate.getLinkedin(), candidate.getId()]
        return executeTransaction(UPDATE_CANDIDATE, arguments)
    }

    OperationStatus executeTransaction(String sqlStatement, List<Object> arguments) {
        OperationStatus response = OperationStatus.IN_PROGRESS

        sql.withTransaction { status ->
            try {
                sql.execute(sqlStatement, arguments)
                status.commit()
                response = OperationStatus.SUCCESS
            } catch (SQLException e) {
                println(e.getMessage())
                status.rollback()
                response = OperationStatus.FAILURE
            }
        }

        return response
    }

}


