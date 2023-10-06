package database

import model.Usuario

import java.time.LocalDate

class UsuarioDAO {

	static sql = new ConnectionFactory().newInstance()
	static ArrayList<Usuario> usuarios = new ArrayList<>()

	static void create(Usuario u) throws Exception {
		String query = "INSERT INTO usuarios (email, senha) VALUES (?,?)"
		sql.executeInsert(query, [u.getEmail(), u.getSenha()])
	}

	static ArrayList<Usuario> read() throws Exception {
		usuarios.clear()

		String query = "SELECT * FROM usuarios"
		sql.eachRow(query) { rs ->
			usuarios.add(new Usuario(rs.id, rs.email, rs.senha, null))
		}
		return usuarios
	}

	static void delete(Usuario u) throws Exception {
		String query = "DELETE FROM usuarios WHERE id = ?"
		sql.execute(query, [u.getId()])
	}

	static void update(Usuario u) throws Exception {
		String query = "UPDATE usuarios SET senha = ? WHERE id = ?"
		sql.execute(query, [u.getSenha(), u.getId()])
	}

	static void validar(Usuario u) throws Exception {
		String query = "SELECT * FROM usuarios WHERE email = ? AND senha = ?"

		sql.eachRow(query, [u.getEmail(), u.getSenha()]) { rs ->

			if (rs) {
				def usuario = checar(new Usuario(rs.id, rs.email, rs.senha, new LocalDate(rs.data_criacao)))

				if(usuario == 1){
					println("Candidato")
				}else if(Usuario == 2){
					println("Empresa")
				}else{
					println("!Não cadastrado")
				}
			} else {
				throw new Exception("Usuário inválido")
			}
		}
	}

	static Integer checar(Usuario u) throws Exception {

		String queryCandidatos = "SELECT * FROM candidatos"
		sql.eachRow(queryCandidatos) { rs -> if (rs) return 1;
		}

		String queryEmpresas = "SELECT * FROM empresas"
		sql.eachRow(queryEmpresas) { rs -> if (rs) return 2;
		}

		return 3
	}

	static void close() {
		sql.close();
	}
}
