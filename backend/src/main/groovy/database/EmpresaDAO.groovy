package database


import model.Empresa

import java.time.LocalDate

class EmpresaDAO {

    static sql = new ConnectionFactory().newInstance()
    static ArrayList<Empresa> empresas = new ArrayList<>()

    static void create(Empresa e) throws Exception {
        String query = "INSERT INTO empresas " +
                "(usuarios_id, razao_social, cnpj, descricao, cidades_id, cep, data_fundacao) " + "VALUES (?, ?, ?, ?, ?, ?, ?)"

        sql.executeInsert(query, [e.getId(),
                                  e.getRazaoSocial(),
                                  e.getInscricao(),
                                  e.getDescricao(),
                                  e.getCidade(),
                                  e.getCEP(),
                                  e.getDataFundacao()])
    }

    static ArrayList<Empresa> read() throws Exception {
        empresas.clear()

        String query = "SELECT * FROM empresas"

        sql.eachRow(query) { rs ->

            String data = rs[6];
            String[] conversaoDataFundacao = data.split("-");
            Integer ano = Integer.parseInt(conversaoDataFundacao[0]);
            Integer mes = Integer.parseInt(conversaoDataFundacao[1]);
            Integer dia = Integer.parseInt(conversaoDataFundacao[2]);

            def empresa = new Empresa(id: rs[0],
                    razaoSocial: rs[1],
                    inscricao: rs[2],
                    CEP: rs[5],
                    cidade: rs[4],
                    pais: null,
                    descricao: rs[3],
                    dataFundacao: LocalDate.of(ano, mes, dia),
            )

            //empresa.setVagas()
            //candidato.setCompetencias(CandidatoCompetenciaDAO.read(candidato.getId()))

            empresas.add(empresa)
        }

        return empresas
    }

    static ArrayList<Empresa> readOne(Integer id) throws Exception {
        empresas.clear()

        String query = "SELECT * FROM empresas WHERE usuarios_id=?"

        sql.eachRow(query, [id]) { rs ->

            String data = rs[6];
            String[] conversaoDataFundacao = data.split("-");
            Integer ano = Integer.parseInt(conversaoDataFundacao[0]);
            Integer mes = Integer.parseInt(conversaoDataFundacao[1]);
            Integer dia = Integer.parseInt(conversaoDataFundacao[2]);

            def empresa = new Empresa(id: rs[0],
                    razaoSocial: rs[1],
                    inscricao: rs[2],
                    CEP: rs[5],
                    cidade: rs[4],
                    pais: null,
                    descricao: rs[3],
                    dataFundacao: LocalDate.of(ano, mes, dia),
            )

            empresas.add(empresa)
        }

        return empresas
    }

    static void delete(Empresa e) throws Exception {
        String query = "DELETE FROM empresas WHERE usuarios_id = ?"
        sql.execute(query, [e.getId()])
    }

    static void update(Empresa e) throws Exception {
        String query = "UPDATE empresas SET " + "razao_social = ?, " + "cnpj = ?, " + "descricao = ?, " + "cidades_id = ?, " + "cep = ?, " + "data_fundacao = ? " + "WHERE usuarios_id = ?"

        sql.execute(query, [
                e.getRazaoSocial(),
                e.getInscricao(),
                e.getDescricao(),
                e.getCidade(),
                e.getCEP(),
                e.getDataFundacao(),
                e.getId()
        ])
    }

    static void close() {
        sql.close();
    }
}
