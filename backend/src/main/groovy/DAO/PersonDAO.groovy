package DAO

import entities.Person
import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import groovy.transform.Canonical

@Canonical
class PersonDAO {

    Sql sql

    List<Person> findAll() {
        List<GroovyRowResult> results = sql.rows("SELECT * FROM usuarios")
        List<Person> persons = []
        results.each { row ->
            Person person = new Person(id: row.id, email: row.email, password: row.senha)
            persons.add(person)
        }
        return persons
    }

    Person findById(int id) {
        GroovyRowResult result = sql.firstRow("SELECT * FROM usuarios WHERE id = ?", id)
        Person person = new Person(id: result.id, email: result.email, password: result.senha)
        return result ? person : null
    }

    boolean save(Person person) {
        List<List<Object>> result = sql.executeInsert("INSERT INTO usuarios (email, senha) VALUES (?, ?)",
                [person.email, person.password])
        return result ? true : false
    }

}


