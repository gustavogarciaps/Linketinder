package br.com.linketinder.database

import br.com.linketinder.model.Competencia
import br.com.linketinder.services.GerenciamentoCompetencia

class VagaCompetenciaDAO {

    static sql = new ConnectionFactory().newInstance()

    static void create(Integer vagas_id, Integer competencias_id) throws Exception {
        String query = "INSERT INTO vagas_competencias " +
                "(vagas_id,competencias_id) " +
                "VALUES (?, ?)"

        sql.executeInsert(query, [vagas_id,
                                  competencias_id])
    }

    static GerenciamentoCompetencia read(Integer vagas_id) {

        def vagas_competencias = new GerenciamentoCompetencia()

        String query = "SELECT * FROM vagas_competencias AS cc " +
                "INNER JOIN competencias AS cs " +
                "ON cc.competencias_id = cs.id " +
                "WHERE cc.candidatos_id = ?;"

        sql.eachRow(query, [vagas_competencias]) { rs ->

            def competencia = new Competencia(id: rs[2],
                    nome: rs[3])
            vagas_competencias.setCompetencia(competencia)
        }

        return vagas_competencias
    }
}
