package persistencies

import entities.PersonDTO
import groovy.sql.Sql
import groovy.transform.Canonical

@Canonical
class PersonDAO {

    Sql sql

    List<PersonDTO> findAll() {
        def results = sql.rows("SELECT * FROM usuarios")
        List<PersonDTO> persons = []
        results.each { row ->
            PersonDTO person = new PersonDTO(id: row.id, email: row.email, password: row.senha)
            persons.add(person)
        }
        return persons
    }

    PersonDTO findById(int id) {

        def result = sql.firstRow("SELECT * FROM usuarios WHERE id = ?", id)

        PersonDTO person = new PersonDTO(id: result.id, email: result.email, password: result.senha)

        return result ? person : null
    }

    boolean save(PersonDTO person) {
        def result = sql.executeInsert("INSERT INTO usuarios (email, senha) VALUES (?, ?)",
                [person.email, person.password])

        return result ? true : false
    }

}


