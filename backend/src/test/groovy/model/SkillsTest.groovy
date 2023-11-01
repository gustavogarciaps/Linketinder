package model

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.CandidateSkillsDAO
import repository.DatabaseConfig
import repository.SkillsDAO

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertFalse
import static org.junit.jupiter.api.Assertions.assertTrue

class SkillsTest {

    Skills skill

    @BeforeEach
    void setUp() {
        skill = new Skills()
    }

    @Test
    void shouldBeInstanceOfPerson() {
        assertTrue(skill instanceof Skills)
    }

    @Test
    void creatingSkill() {
        Skills skill = new Skills(id: 1, name: "Java", level: 2)
        assertEquals("Java", skill.getName())
    }

    @Test
    void recoverSkillFromDatabase() {
        SkillsDAO skillsDAO = new SkillsDAO(sql: DatabaseConfig.newInstance())
        List<Skills> skillList = skillsDAO.findAll()
        skillList.forEach { it ->
            println("recoverSkillFromDatabase: ${it.getId()} ${it.getName()}")
        }
    }

    @Test
    void findSkill() {
        SkillsDAO skillsDAO = new SkillsDAO(sql: DatabaseConfig.newInstance())
        println("findSkill: ${skillsDAO.findById(19)}")
    }

    @Test
    void deleteSkillById() {
        SkillsDAO skillsDAO = new SkillsDAO(sql: DatabaseConfig.newInstance())
        assertFalse(skillsDAO.deleteById(26))
    }

    @Test
    void saveSkillInDatabase() {
        SkillsDAO skillsDAO = new SkillsDAO(sql: DatabaseConfig.newInstance())
        skillsDAO.save(new Skills(name: "CSS 256"))
    }

    @Test
    void updateJobsById() {
        SkillsDAO skillsDAO = new SkillsDAO(sql: DatabaseConfig.newInstance())

        Skills skillsDTO = new Skills(id: 19, name: "Osvaldo Cruz")
        assertTrue(skillsDAO.updateById(skillsDTO))
    }

    @Test
    void loadAssociateSkillsWithJobs() {
        CandidateSkillsDAO candidateSkillsDAO = new CandidateSkillsDAO(sql: DatabaseConfig.newInstance())

        Candidate candidateDTO = new Candidate(id: 5)
        candidateSkillsDAO.findAll(candidateDTO).each {
            it ->
                println(it)
        }
    }

    @Test
    void creatingAssociateSkillsWithUsers() {
        CandidateSkillsDAO candidateSkillsDAO = new CandidateSkillsDAO(sql: DatabaseConfig.newInstance())

        Candidate candidateDTO = new Candidate(id: 5)
        Skills skillsDTO = new Skills(id: 20)

        candidateSkillsDAO.save(candidateDTO, skillsDTO)
    }


}
