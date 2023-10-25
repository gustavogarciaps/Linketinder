package entities

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import persistencies.CandidateDAO
import persistencies.ConnectionFactory
import persistencies.SkillsDAO

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue

class SkillsDTOTest {

    SkillsDTO skill

    @BeforeEach
    void setUp() {
        skill = new SkillsDTO()
    }

    @Test
    void shouldBeInstanceOfPerson() {
        assertTrue(skill instanceof SkillsDTO)
    }

    @Test
    void creatingSkill() {
        SkillsDTO skill = new SkillsDTO(id: 1, name: "Java", level: 2)
        assertEquals("Java", skill.getName())
    }

    @Test
    void recoverSkillFromDatabase() {
        SkillsDAO skillsDAO = new SkillsDAO(sql: ConnectionFactory.newInstance())
        List<SkillsDTO> skillList = skillsDAO.findAll()
        skillList.forEach { it ->
            println("recoverSkillFromDatabase: ${it.getId()} ${it.getName()}")
        }
    }

    @Test
    void findSkill() {
        SkillsDAO skillsDAO = new SkillsDAO(sql: ConnectionFactory.newInstance())
        println("findSkill: ${skillsDAO.findById(19)}")
    }

    @Test
    void deleteSkillById() {
        SkillsDAO skillsDAO = new SkillsDAO(sql: ConnectionFactory.newInstance())
        assertTrue(skillsDAO.deleteById(21))
    }

    @Test
    void saveSkillInDatabase(){
        SkillsDAO skillsDAO = new SkillsDAO(sql: ConnectionFactory.newInstance())
        skillsDAO.save(new SkillsDTO(name: "CSS 256"))
    }

    @Test
    void updateCandidateById() {
        SkillsDAO skillsDAO = new SkillsDAO(sql: ConnectionFactory.newInstance())

        SkillsDTO skillsDTO = new SkillsDTO(id: 19, name: "Osvaldo Cruz")
        assertTrue(skillsDAO.updateById(skillsDTO))
    }
}
