package entities

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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


}
