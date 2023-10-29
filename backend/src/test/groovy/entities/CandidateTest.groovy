package entities

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import DAO.CandidateDAO
import DAO.Connection

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

        CandidateDAO candidateDAO = new CandidateDAO(sql: Connection.newInstance())
        List<Candidate> candidateList = candidateDAO.findAll()
        candidateList.forEach { it ->
            println("recoverCandidateFromDatabase: ${it.getId()} ${it.getName()}")
        }
    }

    @Test
    void findCandidate() {
        CandidateDAO candidateDAO = new CandidateDAO(sql: Connection.newInstance())
        println("findCandidate: ${candidateDAO.findById(19)}")
    }

    @Test
    void deleteCandidateById() {
        CandidateDAO candidateDAO = new CandidateDAO(sql: Connection.newInstance())
        assertTrue(candidateDAO.deleteById(21))
    }

    @Test
    void updateCandidateById() {
        CandidateDAO candidateDAO = new CandidateDAO(sql: Connection.newInstance())

        Candidate candidateDTO = new Candidate(id: 19, name: "Osvaldo Cruz")
        assertTrue(candidateDAO.updateById(candidateDTO))
    }

    @Test
    void findAllCandidateSkills() {
        CandidateDAO candidateDAO = new CandidateDAO(sql: Connection.newInstance())

        Candidate candidateDTO = candidateDAO.findAll(new Candidate(id: 5))

        candidateDTO.each {
            candidate->
                println(candidate.getSkills().each{ it ->
                    println(it.getName())
                })
        }
    }


}
