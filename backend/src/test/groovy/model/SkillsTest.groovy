package model

import groovy.sql.Sql
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.CandidateSkillsDAO
import repository.connection.DatabaseSingleton
import repository.SkillsDAO
import services.CandidateSkillsService
import services.SkillsService
import utils.OperationStatus

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue

class SkillsTest {

    Sql sql
    Skills skill
    SkillsDAO skillsDAO
    SkillsService skillsService

    @BeforeEach
    void setUp() {
        DatabaseSingleton database = DatabaseSingleton.getInstance()
        sql = database.getDatabaseConnection()

        skillsDAO = new SkillsDAO(sql)
        skillsService = new SkillsService(skillsDAO)

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
        skillsService.findAll().forEach { skill ->
            println("recoverSkillFromDatabase: ${skill.getId()} ${skill.getName()}")
        }
    }

    @Test
    void findSkill() {
        println("findSkill: ${skillsService.findById(5)}")
    }

    @Test
    void deleteSkillById() {
        println(skillsService.deleteById(6).getMessage())
    }

    @Test
    void saveSkillInDatabase() {
        println(skillsService.save(new Skills(name: "NODE JS")).getMessage())
    }

    @Test
    void updateJobsById() {
        Skills skill = new Skills(id: 7, name: "Node JS")
        println(skillsService.updateById(skill).getMessage())
    }

    @Test
    void creatingAssociateSkillsWithUsers() {
        CandidateSkillsDAO candidateSkillsDAO = new CandidateSkillsDAO(sql)
        CandidateSkillsService candidateSkillsService = new CandidateSkillsService(candidateSkillsDAO)

        Candidate candidate = new Candidate(id: 5)
        Skills skill = new Skills(id: 3)

        OperationStatus status = candidateSkillsService.save(candidate, skill)
        println(status.getMessage())
    }


}
