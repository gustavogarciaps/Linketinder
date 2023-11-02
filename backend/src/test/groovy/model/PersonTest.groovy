package model

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.DatabaseConfig
import repository.PersonDAO

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue

class PersonTest {

    Person person

    @BeforeEach
    void setUp() {
        person = new Person()
    }

    @Test
    void shouldBeInstanceOfPerson() {
        assertTrue(person instanceof Person)
    }

    @Test
    void creatingPerson() {
        person.setId(1)
        person.setEmail("gustavo@gmail.com")
        person.setPassword("123456")

        assertEquals("gustavo@gmail.com", person.getEmail())
        assertEquals(1, person.getId())
        assertEquals("123456", person.getPassword())
    }


    @Test
    void personPersistenceInTheDatabase() {
        person.setEmail("gustavo@gmail.com")
        person.setPassword("123456")
    }

    @Test
    void recoverPersonFromDatabase() {

        PersonDAO personDAO = new PersonDAO(sql: DatabaseConfig.newInstance())
        List<Person> personList = personDAO.findAll()
        personList.forEach { it ->
            println("recoverPersonFromDatabase: ${it.getId()} ${it.getEmail()}")
        }
    }

    @Test
    void findPerson() {
        PersonDAO personDAO = new PersonDAO(sql: DatabaseConfig.newInstance())
        println("findPerson ${personDAO.findById(18)}")
    }

    @Test
    void insertPersonToDataBase() {
        PersonDAO personDAO = new PersonDAO(sql: DatabaseConfig.newInstance())
        Person personDTO = new Person(email: "teste${Math.random()}@gmail.com", password: "10987")
        personDAO.save(personDTO)
    }
}

