package br.com.linketinder.database

import br.com.linketinder.model.Candidato
import br.com.linketinder.model.Competencia
import br.com.linketinder.services.GerenciamentoCompetencia

import java.time.LocalDate

class CandidatoCompetenciaDAO {

    static sql = new ConnectionFactory().newInstance()

    static void create(Integer candidatos_id, Integer competencias_id) throws Exception {
        String query = "INSERT INTO candidatos_competencias " +
                "(candidatos_id,competencias_id) " +
                "VALUES (?, ?)"

        sql.executeInsert(query, [candidatos_id,
                                  competencias_id])
    }

    static GerenciamentoCompetencia read(Integer candidatos_id) {

        def candidatos_competencias = new GerenciamentoCompetencia()

        String query = "SELECT * FROM candidatos_competencias AS cc " +
                "INNER JOIN competencias AS cs " +
                "ON cc.competencias_id = cs.id " +
                "WHERE cc.candidatos_id = ?;"

        sql.eachRow(query, [candidatos_id]) { rs ->

            def competencia = new Competencia(id: rs[2],
                    nome: rs[3])
            candidatos_competencias.setCompetencia(competencia)
        }

        return candidatos_competencias
    }
}
