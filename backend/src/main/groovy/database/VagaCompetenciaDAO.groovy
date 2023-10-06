package database

import model.Competencia
import services.GerenciamentoCompetencia

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

        String query = "SELECT * FROM vagas_competencias AS vc INNER JOIN competencias AS cs ON vc.competencias_id = cs.id WHERE vc.vagas_id = ?"

        sql.eachRow(query, [vagas_id]) { rs ->

            def competencia = new Competencia(id: rs[2],
                    nome: rs[3])
            vagas_competencias.setCompetencia(competencia)
        }

        return vagas_competencias
    }
}
