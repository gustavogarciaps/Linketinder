package domain

import groovy.sql.Sql
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.CandidateDAO
import repository.connection.DatabaseSingleton
import services.CandidateService

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue

class CandidateTest {

    Sql sql
    CandidateDAO candidateDAO
    CandidateService candidateService
    Candidate candidate

    @BeforeEach
    void setUp() {

        DatabaseSingleton database = DatabaseSingleton.getInstance()
        sql = database.getDatabaseConnection()

        candidateDAO = new CandidateDAO(sql)
        candidateService = new CandidateService(candidateDAO)

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
        candidateService.findAll().forEach { it ->
            println("recoverCandidateFromDatabase: ${it.getId()} ${it.getName()}")
        }
    }

    @Test
    void findCandidate() {

        Candidate candidate = candidateService.findById(17)
        println("findCandidate: ${candidate.getName()}")
    }

    @Test
    void deleteCandidateById() {
        println(candidateService.deleteById(21).getMessage())
    }

    @Test
    void updateCandidateById() {
        Candidate candidate = new Candidate(id: 17, name: "Osvaldo Cruz", city: 1)
        def result = candidateService.updateById(candidate)
        println(result.getMessage())
    }

    @Test
    void findAllCandidateSkills() {

        Candidate candidate = candidateService.findAll(new Candidate(id: 5))

        candidate.each {
            it ->
                println(it.getSkills().each { skill ->
                    println(skill.getName())
                })
        }
    }


}
