package repository


import model.Company
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import utils.OperationStatus

import java.sql.SQLException

class CompanyDAO implements InterfaceDatabaseDML<Company> {

    private static final String SELECT_ALL_COMPANIES = "SELECT * FROM empresas AS e INNER JOIN usuarios AS u ON e.usuarios_id = u.id;"
    private static final String SELECT_COMPANY_BY_ID = "SELECT * FROM empresas AS e INNER JOIN usuarios AS u ON e.usuarios_id = u.id WHERE id = ?"
    private static final String INSERT_COMPANY = "INSERT INTO empresas (usuarios_id, razao_social, descricao, data_fundacao) VALUES (?, ?, ?, ?)"
    private static final String DELETE_COMPANY = "DELETE FROM empresas WHERE usuarios_id = ?"
    private static final String UPDATE_COMPANY = "UPDATE empresas SET razao_social = ?, cnpj = ?, descricao = ?, cidades_id = ?, cep = ?, data_fundacao = ? WHERE usuarios_id = ?"

    Sql sql

    CompanyDAO(Sql sql) {
        this.sql = sql
    }

    List<Company> findAll() {

        List<Company> companies = []
        sql.rows(SELECT_ALL_COMPANIES).each { row ->
            Company company = new Company(id: row.usuarios_id, name: row.razao_social, description: row.descricao)
            companies.add(company)
        }
        return companies
    }

    Company findById(int id) {
        GroovyRowResult result = sql.firstRow(SELECT_COMPANY_BY_ID, id)

        Company company = new Company(id: result.id, name: result.razao_social, description: result.descricao)
        return company
    }

    OperationStatus save(Company company) {
        List<Object> arguments = [company.getId(), company.getName(), company.getDescription(), company.getCreationDate()]
        return DatabaseExecute.executeTransaction(sql, INSERT_COMPANY, arguments)
    }

    OperationStatus deleteById(int id) {
        List<Object> arguments = [id]
        return DatabaseExecute.executeTransaction(sql, DELETE_COMPANY, arguments)
    }

    OperationStatus updateById(Company company) throws SQLException {
        List<Object> arguments = [company.getName(), company.getCnpj(), company.getDescription(), company.getCity().toInteger(), company.getZipCode(), company.getCreationDate(), company.getId()]
        return DatabaseExecute.executeTransaction(sql, UPDATE_COMPANY, arguments)
    }

}


