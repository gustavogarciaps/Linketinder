package dao

import model.Candidato

import java.time.LocalDate

class CandidatoDAO {

    static sql = new ConnectionFactory().newInstance()
    static ArrayList<Candidato> candidatos = new ArrayList<>()

    static void create(Candidato c) throws Exception {
        String query = "INSERT INTO candidatos " + "(usuarios_id, nome, sobrenome, cpf, descricao, cidades_id, cep, formacao, data_nascimento, linkedin) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"

        sql.executeInsert(query, [c.getId(),
                                  c.getNome(),
                                  c.getSobrenome(),
                                  c.getInscricao(),
                                  c.getDescricao(),
                                  c.getCidade(),
                                  c.getCEP(),
                                  c.getFormacao(),
                                  c.getDataNascimento(),
                                  c.getLinkedin()])
    }

    static ArrayList<Candidato> read() throws Exception {
        candidatos.clear()

        String query = "SELECT * FROM candidatos"

        sql.eachRow(query) { rs ->

            String data = rs[8];
            String[] conversaoDataNascimento = data.split("-");
            Integer ano = Integer.parseInt(conversaoDataNascimento[0]);
            Integer mes = Integer.parseInt(conversaoDataNascimento[1]);
            Integer dia = Integer.parseInt(conversaoDataNascimento[2]);

            Candidato candidato = new Candidato(id: rs[0],
                    nome: rs[1],
                    sobrenome: rs[2],
                    inscricao: rs[3],
                    descricao: rs[4],
                    cidade: rs[5],
                    CEP: rs[6],
                    formacao: rs[7],
                    dataNascimento: LocalDate.of(ano, mes, dia),
                    linkedin: rs[9],
                    pais: null,
                    competencias: null
            )

            candidato.setCompetencias(CandidatoCompetenciaDAO.read(candidato.getId()))

            candidatos.add(candidato)

        }

        return candidatos
    }

    static ArrayList<Candidato> readOne(Integer id) throws Exception {
        candidatos.clear()

        String query = "SELECT * FROM candidatos WHERE usuarios_id=?"

        sql.eachRow(query, [id]) { rs ->

            String data = rs.data_nascimento;
            String[] conversaoDataNascimento = data.split("-");
            Integer ano = Integer.parseInt(conversaoDataNascimento[0]);
            Integer mes = Integer.parseInt(conversaoDataNascimento[1]);
            Integer dia = Integer.parseInt(conversaoDataNascimento[2]);

            candidatos.add(new Candidato(id: rs.usuarios_id,
                    nome: rs.nome,
                    sobrenome: rs.sobrenome,
                    inscricao: rs.cpf,
                    descricao: rs.descricao,
                    cidade: rs.cidades_id,
                    CEP: rs.cep,
                    formacao: rs.formacao,
                    dataNascimento: LocalDate.of(ano, mes, dia),
                    linkedin: rs.linkedin,
                    pais: null))
        }

        return candidatos
    }

    static void delete(Candidato c) throws Exception {
        String query = "DELETE FROM candidatos WHERE usuarios_id = ?"
        sql.execute(query, [c.getId()])
    }

    static void update(Candidato c) throws Exception {
        String query = "UPDATE candidatos SET " + "nome = ?, " + "sobrenome = ?, " + "cpf = ?, " + "descricao = ?, " + "cidades_id = ?, " + "cep = ?, " + "formacao = ?, " + "data_nascimento = ?, " + "linkedin = ? " + "WHERE usuarios_id = ?"

        sql.execute(query, [c.getNome(),
                            c.getSobrenome(),
                            c.getInscricao(),
                            c.getDescricao(),
                            c.getCidade(),
                            c.getCEP(),
                            c.getFormacao(),
                            c.getDataNascimento(),
                            c.getLinkedin(),
                            c.getId()])
    }

    static void close() {
        sql.close();
    }
}
