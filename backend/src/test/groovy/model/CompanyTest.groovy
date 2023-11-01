package model

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.CompanyDAO
import repository.DatabaseConfig

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue

class CompanyTest {

    Company company

    @BeforeEach
    void setUp() {
        company = new Company()
    }

    @Test
    void shouldBeInstanceOfPerson() {
        assertTrue(company instanceof Person)
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
                new Jobs(id: 1, title: "Desenvolvedor HTML", description: "Conhecimento avançado em HTML")
        )

        Jobs job = company.getJobs()[0]
        assertEquals("Desenvolvedor HTML", job.getTitle())
    }

    @Test
    void companyLikeToCandidate(){

        Candidate candidate = new Candidate(name: "Gustavo")
        CompanyLikes companyLikes = new CompanyLikes(company: company)

        companyLikes.like(candidate)

        assertEquals(candidate, companyLikes.getCandidates()[0])

    }

    @Test
    void recoverCompaniesFromDatabase() {

        CompanyDAO companyDAO = new CompanyDAO(sql: DatabaseConfig.newInstance())
        List<Company> companies = companyDAO.findAll()
        companies.forEach { it ->
            println("recoverCompaniesFromDatabase: ${it.getId()} ${it.getName()}")
        }
    }

    @Test
    void findCompany() {
        CompanyDAO companyDAO = new CompanyDAO(sql: DatabaseConfig.newInstance())
        println("findCompany: ${companyDAO.findById(22).getName()}")
    }

    @Test
    void insertCompanyToDataBase() {
        CompanyDAO companyDAO = new CompanyDAO(sql: DatabaseConfig.newInstance())
        Company company = new Company(id: 22, name: "Sorvetes e Tech Solutions", description: "10987", creationDate: LocalDate.of(2022,6,9))
        companyDAO.save(company)
    }

    @Test
    void updateCompanyById() {
        CompanyDAO companyDAO = new CompanyDAO(sql: DatabaseConfig.newInstance())
        Company company = companyDAO.findById(22)
        company.setName( "Sorvetes e Tech")
        company.setCity("1")
        company.setCreationDate(LocalDate.of(2023,10,25))
        assertTrue(companyDAO.updateById(company))
    }

    @Test
    void deleteCompanyById() {
        CompanyDAO companyDAO = new CompanyDAO(sql: DatabaseConfig.newInstance())
        assertTrue(companyDAO.deleteById(22))
    }

}

