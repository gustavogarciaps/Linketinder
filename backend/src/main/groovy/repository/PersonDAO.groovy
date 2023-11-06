package repository

import domain.Person
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import groovy.transform.Canonical
import repository.connection.DatabaseExecute
import utils.OperationStatus

@Canonical
class PersonDAO implements InterfaceDatabaseDML<Person> {

    private static final String SELECT_ALL_PERSON = "SELECT * FROM usuarios"
    private static final String SELECT_PERSON_BY_ID = "SELECT * FROM usuarios WHERE id = ?"
    private static final String INSERT_PERSON = "INSERT INTO usuarios (email, senha) VALUES (?, ?)"

    Sql sql

    PersonDAO(Sql sql) {
        this.sql = sql
    }

    List<Person> findAll() {
        List<Person> persons = []

        sql.rows(SELECT_ALL_PERSON).each { row ->
            Person person = new Person(id: row.id, email: row.email, password: row.senha)
            persons.add(person)
        }
        return persons
    }

    Person findById(int id) {
        GroovyRowResult result = sql.firstRow(SELECT_PERSON_BY_ID, id)

        Person person = new Person(id: result.id, email: result.email, password: result.senha)
        return person
    }

    OperationStatus save(Person person) {
        List<Object> arguments = [person.email, person.password]
        return DatabaseExecute.executeTransaction(sql, INSERT_PERSON, arguments)
    }

    @Override
    OperationStatus deleteById(int id) {
        return null
    }

    @Override
    OperationStatus updateById(Person entity) {
        return null
    }
}


