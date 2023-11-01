package model

import groovy.sql.Sql
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.CandidateDAO
import repository.DatabaseConfig
import repository.DatabaseSingleton
import services.CandidateService

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue

class CandidateTest {

    Candidate candidate

    @BeforeEach
    void setUp() {
        candidate = new Candidate()
    }

    @Test
    void shouldBeInstanceOfPerson() {
        assertTrue(candidate instanceof Person)
    }

    @Test
    void addSkillToCandidate() {
        candidate.addSkills(
                new Skills(id: 1, name: "Java", level: 2)
        )
        Skills skill = candidate.getSkills()[0]
        assertTrue(skill instanceof Skills)
        assertEquals("Java", skill.getName())
    }

    @Test
    void recoverCandidateFromDatabase() {

        DatabaseSingleton database = DatabaseSingleton.getInstance()
        Sql sql = database.getDatabaseConnection()

        CandidateDAO candidateDAO = new CandidateDAO(sql)
        CandidateService candidateService = new CandidateService(candidateDAO)

        candidateService.findAll().forEach { it ->
            println("recoverCandidateFromDatabase: ${it.getId()} ${it.getName()}")
        }
    }

    @Test
    void findCandidate() {
        DatabaseSingleton database = DatabaseSingleton.getInstance()
        Sql sql = database.getDatabaseConnection()

        CandidateDAO candidateDAO = new CandidateDAO(sql)
        CandidateService candidateService = new CandidateService(candidateDAO)

        Candidate candidate = candidateService.findById(17)
        println("findCandidate: ${candidate.getName()}")
    }

    @Test
    void deleteCandidateById() {
        DatabaseSingleton database = DatabaseSingleton.getInstance()
        Sql sql = database.getDatabaseConnection()

        CandidateDAO candidateDAO = new CandidateDAO(sql)
        CandidateService candidateService = new CandidateService(candidateDAO)

        assertTrue(candidateService.deleteById(21))
    }

    @Test
    void updateCandidateById() {
        DatabaseSingleton database = DatabaseSingleton.getInstance()
        Sql sql = database.getDatabaseConnection()

        CandidateDAO candidateDAO = new CandidateDAO(sql)
        CandidateService candidateService = new CandidateService(candidateDAO)

        Candidate candidate = new Candidate(id: 17, name: "Osvaldo Cruz", city: 1)
        def result = candidateService.updateById(candidate)
        println(result.getMessage())
    }

    @Test
    void findAllCandidateSkills() {
        CandidateDAO candidateDAO = new CandidateDAO(sql: DatabaseConfig.newInstance())

        Candidate candidateDTO = candidateDAO.findAll(new Candidate(id: 5))

        candidateDTO.each {
            candidate->
                println(candidate.getSkills().each{ it ->
                    println(it.getName())
                })
        }
    }


}
