package domain

import groovy.sql.Sql
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.CandidateDAO
import repository.connection.DatabasePostgresConfig
import repository.connection.DatabaseSingleton
import repository.JobsDAO
import repository.JobsSkillsDAO
import services.JobsService
import services.JobsSkillsService
import utils.OperationStatus

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue

class JobsTest {

    Sql sql
    JobsDAO jobsDAO
    JobsService jobsService
    Jobs job

    @BeforeEach
    void setUp() {

        DatabaseSingleton database = DatabaseSingleton.getInstance()
        sql = database.getDatabaseConnection()

        jobsDAO = new JobsDAO(sql)
        jobsService = new JobsService(jobsDAO)

        job = new Jobs()
    }

    @Test
    void shouldBeInstanceOfJobs() {
        assertTrue(job instanceof Jobs)
    }

    @Test
    void addSkillToJobs() {
        job.addSkills(
                new Skills(id: 1, name: "Java", level: 2)
        )
        Skills skill = job.getSkills()[0]
        assertTrue(skill instanceof Skills)
        assertEquals("Java", skill.getName())
    }

    @Test
    void recoverJobsFromDatabase() {

        jobsService.findAll().forEach { it ->
            println("recoverJobsFromDatabase: ${it.getId()} ${it.getTitle()} ${it.getCompany().getId()}")
        }
    }

    @Test
    void findJob() {
        println("findJob: ${jobsService.findById(7).properties}")
    }

    @Test
    void insertJobDatabase() {
        Jobs job = new Jobs(company: new Company(id: 6), title: "Desenvolvedor Ruby", description: "Desenvolver soluções em Ruby Rails e integras com X", city: 1)
        OperationStatus status = jobsService.save(job)
        println(status.getMessage())
    }


    @Test
    void deleteJobById() {
        CandidateDAO candidateDAO = new CandidateDAO(sql: DatabasePostgresConfig.newInstance())
        assertTrue(candidateDAO.deleteById(21))
    }

    @Test
    void updateJobById() {

        Jobs job = jobsService.findById(6)
        job.setTitle("Desenvolvedor Java Sr.")
        jobsService.updateById(job)
        recoverJobsFromDatabase()
    }

    @Test
    void findAllJobSkills() {

        jobsService.findAll().each {
            job ->
                println(job.getTitle())

                jobsService.findAll(job).getSkills().each { it ->
                    println(it.getId())
                    println(it.getName())
                }

        }
    }

    @Test
    void associateJobsWithSkills() {
        Jobs job = new Jobs(id: 6)
        Skills skill = new Skills(id: 5)

        JobsSkillsDAO jobsSkillsDAO = new JobsSkillsDAO(sql)
        JobsSkillsService jobsSkillsService = new JobsSkillsService(jobsSkillsDAO)
        OperationStatus status = jobsSkillsService.save(job, skill)
        println(status.getMessage())
    }
}
