package br.com.linketinder.database

import br.com.linketinder.model.Competencia

class CompetenciaDAO {

    static sql = new ConnectionFactory().newInstance();

    static void create(Competencia c){
        String query = "INSERT INTO competencias (nome) VALUES ('${c.getNome}')"
        sql.execute(query)
    }

    static ArrayList<Competencia> read(){

        ArrayList<Competencia> competencias = new ArrayList<>();

        String query = "SELECT * FROM competencias";

        sql.eachRow(query) { rs ->
            competencias.add(new Competencia(rs.nome));
        }

        return competencias;
    }

}
