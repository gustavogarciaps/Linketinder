package dao

import model.Competencia

class CandidatoCompetenciaDAO {

    static sql = new ConnectionFactory().newInstance()

    static void create(Integer candidatos_id, Integer competencias_id) throws Exception {
        String query = "INSERT INTO candidatos_competencias " +
                "(candidatos_id,competencias_id) " +
                "VALUES (?, ?)"

        sql.executeInsert(query, [candidatos_id,
                                  competencias_id])
    }

    static ArrayList<Competencia> read(Integer candidatos_id) {

        ArrayList<Competencia> candidatos_competencias = new ArrayList<>()

        String query = "SELECT * FROM candidatos_competencias AS cc " +
                "INNER JOIN competencias AS cs " +
                "ON cc.competencias_id = cs.id " +
                "WHERE cc.candidatos_id = ?;"

        sql.eachRow(query, [candidatos_id]) { rs ->

            Competencia competencia = new Competencia(id: rs[2],
                    nome: rs[3])
            candidatos_competencias.add(competencia)
        }

        return candidatos_competencias
    }
}
