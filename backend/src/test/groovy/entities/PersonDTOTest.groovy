package entities

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import persistencies.ConnectionFactory
import persistencies.PersonDAO

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue

class PersonDTOTest {

    PersonDTO person

    @BeforeEach
    void setUp() {
        person = new PersonDTO()
    }

    @Test
    void shouldBeInstanceOfPerson() {
        assertTrue(person instanceof PersonDTO)
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

        PersonDAO personDAO = new PersonDAO(sql: ConnectionFactory.newInstance())
        List<PersonDTO> personList = personDAO.findAll()
        personList.forEach { it ->
            println("recoverPersonFromDatabase: ${it.getId()} ${it.getEmail()}")
        }
    }

    @Test
    void findPerson() {
        PersonDAO personDAO = new PersonDAO(sql: ConnectionFactory.newInstance())
        println("findPerson ${personDAO.findById(18)}")
    }

    @Test
    void insertPersonToDataBase() {
        PersonDAO personDAO = new PersonDAO(sql: ConnectionFactory.newInstance())
        PersonDTO personDTO = new PersonDTO(email: "teste${Math.random()}@gmail.com", password: "10987")
        personDAO.save(personDTO)
    }
}

