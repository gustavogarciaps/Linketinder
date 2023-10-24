package entities

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import persistencies.CandidateDAO
import persistencies.ConnectionFactory
import persistencies.PersonDAO

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue

class CandidateDTOTest {

    CandidateDTO candidate

    @BeforeEach
    void setUp() {
        candidate = new CandidateDTO()
    }

    @Test
    void shouldBeInstanceOfPerson() {
        assertTrue(candidate instanceof PersonDTO)
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

        CandidateDAO candidateDAO = new CandidateDAO(sql: ConnectionFactory.newInstance())
        List<CandidateDTO> candidateList = candidateDAO.findAll()
        candidateList.forEach { it ->
            println("recoverCandidateFromDatabase: ${it.getId()} ${it.getName()}")
        }
    }

    @Test
    void findCandidate() {
        CandidateDAO candidateDAO = new CandidateDAO(sql: ConnectionFactory.newInstance())
        println("findCandidate: ${candidateDAO.findById(19)}")
    }

    @Test
    void deleteCandidateById() {
        CandidateDAO candidateDAO = new CandidateDAO(sql: ConnectionFactory.newInstance())
        assertTrue(candidateDAO.deleteById(21))
    }

    @Test
    void updateCandidateById() {
        CandidateDAO candidateDAO = new CandidateDAO(sql: ConnectionFactory.newInstance())

        CandidateDTO candidateDTO = new CandidateDTO(id: 19, name: "Osvaldo Cruz")
        assertTrue(candidateDAO.updateById(candidateDTO))
    }
}
