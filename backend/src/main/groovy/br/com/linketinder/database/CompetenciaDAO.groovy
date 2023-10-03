package br.com.linketinder.database

import br.com.linketinder.model.Competencia

import java.util.concurrent.ExecutionException

class CompetenciaDAO {

	static sql = new ConnectionFactory().newInstance()
	static ArrayList<Competencia> competencias = new ArrayList<>()

	static void create(Competencia c) throws Exception {
		String query = "INSERT INTO competencias (nome) VALUES (?)"
		sql.executeInsert(query, [c.getNome()])
	}

	static ArrayList<Competencia> read() throws Exception {
		competencias.clear()

		String query = "SELECT * FROM competencias"
		sql.eachRow(query) { rs ->
			competencias.add(new Competencia(rs.id, rs.nome))
		}
		return competencias
	}

	static void delete(Competencia c) throws Exception {
		String query = "DELETE FROM competencias WHERE id = ?"
		sql.execute(query, [c.getId()])
	}

	static void update(Competencia c) throws Exception {
		String query = "UPDATE competencias SET nome =? WHERE id = ?"
		sql.execute(query, [c.getNome(), c.getId()])
	}

	static void close() {
		sql.close();
	}
}
