package entities

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import DAO.CandidateDAO
import DAO.ConnectionFactory
import DAO.JobsDAO

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue

class JobsTest {

    Jobs job

    @BeforeEach
    void setUp() {
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
        JobsDAO jobsDAO = new JobsDAO(sql: ConnectionFactory.newInstance())
        List<Jobs> jobs = jobsDAO.findAll()
        jobs.forEach { it ->
            println("recoverJobsFromDatabase: ${it.getId()} ${it.getTitle()} ${it.getCompany().getId()}")
        }
    }

    @Test
    void findJob() {
        JobsDAO jobsDAO = new JobsDAO(sql: ConnectionFactory.newInstance())
        println("findJob: ${jobsDAO.findById(9).properties}")
    }

    @Test
    void deleteJobById() {
        CandidateDAO candidateDAO = new CandidateDAO(sql: ConnectionFactory.newInstance())
        assertTrue(candidateDAO.deleteById(21))
    }

    @Test
    void updateJobById() {
        JobsDAO jobsDAO = new JobsDAO(sql: ConnectionFactory.newInstance())
        Jobs job = jobsDAO.findById(9)
        job.setTitle("Desenvolvedor Java Sr.")
        jobsDAO.updateById(job)
        recoverJobsFromDatabase()
    }

    @Test
    void findAllJobSkills() {
        JobsDAO jobsDAO = new JobsDAO(sql: ConnectionFactory.newInstance())

        jobsDAO.findAll().each {
            job ->
                println(job.getTitle())

                jobsDAO.findAll(job).getSkills().each { it ->
                    println(it.getId())
                    println(it.getName())
                }

        }
    }
}
