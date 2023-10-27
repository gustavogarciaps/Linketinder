package entities

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import persistencies.CandidateDAO
import persistencies.CompanyDAO
import persistencies.ConnectionFactory
import persistencies.PersonDAO

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue

class CompanyDTOTest {

    CompanyDTO company

    @BeforeEach
    void setUp() {
        company = new CompanyDTO()
    }

    @Test
    void shouldBeInstanceOfPerson() {
        assertTrue(company instanceof PersonDTO)
    }

    @Test
    void creatingCompany() {
        company.setId(1)
        company.setName("Coffe Life")
        company.setDescription("Maior exportadora de Café da América Latina")

        assertEquals("Coffe Life", company.getName())
        assertEquals(1, company.getId())
        assertEquals("Maior exportadora de Café da América Latina", company.getDescription())
    }

    @Test
    void addJobsToCompany() {
        company.addJobs(
                new JobsDTO(id: 1, title: "Desenvolvedor HTML", description: "Conhecimento avançado em HTML")
        )

        JobsDTO job = company.getJobs()[0]
        assertEquals("Desenvolvedor HTML", job.getTitle())
    }

    @Test
    void companyLikeToCandidate(){

        CandidateDTO candidate = new CandidateDTO(name: "Gustavo")
        CompanyLikes companyLikes = new CompanyLikes(company: company)

        companyLikes.like(candidate)

        assertEquals(candidate, companyLikes.getCandidates()[0])

    }

    @Test
    void recoverCompaniesFromDatabase() {

        CompanyDAO companyDAO = new CompanyDAO(sql: ConnectionFactory.newInstance())
        List<CompanyDTO> companies = companyDAO.findAll()
        companies.forEach { it ->
            println("recoverCompaniesFromDatabase: ${it.getId()} ${it.getName()}")
        }
    }

    @Test
    void findCompany() {
        CompanyDAO companyDAO = new CompanyDAO(sql: ConnectionFactory.newInstance())
        println("findCompany: ${companyDAO.findById(22).getName()}")
    }

    @Test
    void insertCompanyToDataBase() {
        CompanyDAO companyDAO = new CompanyDAO(sql: ConnectionFactory.newInstance())
        CompanyDTO company = new CompanyDTO(id: 22, name: "Sorvetes e Tech Solutions", description: "10987", creationDate: LocalDate.of(2022,6,9))
        companyDAO.save(company)
    }

    @Test
    void updateCompanyById() {
        CompanyDAO companyDAO = new CompanyDAO(sql: ConnectionFactory.newInstance())
        CompanyDTO company = companyDAO.findById(22)
        company.setName( "Sorvetes e Tech")
        company.setCity("1")
        company.setCreationDate(LocalDate.of(2023,10,25))
        assertTrue(companyDAO.updateById(company))
    }

    @Test
    void deleteCompanyById() {
        CompanyDAO companyDAO = new CompanyDAO(sql: ConnectionFactory.newInstance())
        assertTrue(companyDAO.deleteById(22))
    }

}

