package entities

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import persistencies.CandidateDAO
import persistencies.ConnectionFactory
import persistencies.JobsDAO

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue
import static org.junit.jupiter.api.Assertions.assertTrue
import static org.junit.jupiter.api.Assertions.assertTrue
import static org.junit.jupiter.api.Assertions.assertTrue

class JobsDTOTest {

    JobsDTO job

    @BeforeEach
    void setUp() {
        job = new JobsDTO()
    }

    @Test
    void shouldBeInstanceOfJobs() {
        assertTrue(job instanceof JobsDTO)
    }

    @Test
    void addSkillToJobs() {
        job.addSkills(
                new SkillsDTO(id: 1, name: "Java", level: 2)
        )
        SkillsDTO skill = job.getSkills()[0]
        assertTrue(skill instanceof SkillsDTO)
        assertEquals("Java", skill.getName())
    }

    @Test
    void recoverJobsFromDatabase() {
        JobsDAO jobsDAO = new JobsDAO(sql: ConnectionFactory.newInstance())
        List<JobsDTO> jobs = jobsDAO.findAll()
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
        JobsDTO job = jobsDAO.findById(9)
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
