package model

import groovy.sql.Sql
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import repository.CompanyDAO
import repository.DatabaseConfig
import repository.DatabaseSingleton
import services.CompanyService
import utils.OperationStatus

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.assertEquals
import static org.junit.jupiter.api.Assertions.assertTrue

class CompanyTest {

    Sql sql
    CompanyDAO companyDAO
    CompanyService companyService
    Company company

    @BeforeEach
    void setUp() {

        DatabaseSingleton database = DatabaseSingleton.getInstance()
        sql = database.getDatabaseConnection()

        companyDAO = new CompanyDAO(sql)
        companyService = new CompanyService(companyDAO)

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
    void companyLikeToCandidate() {

        Candidate candidate = new Candidate(name: "Gustavo")
        CompanyLikes companyLikes = new CompanyLikes(company: company)

        companyLikes.like(candidate)

        assertEquals(candidate, companyLikes.getCandidates()[0])

    }

    @Test
    void recoverCompaniesFromDatabase() {
        companyService.findAll().forEach { company ->
            println("recoverCompaniesFromDatabase: ${company.getId()} ${company.getName()}")
        }
    }

    @Test
    void findCompany() {
        println("findCompany: ${companyService.findById(6).getName()}")
    }

    @Test
    void insertCompanyToDataBase() {
        Company company = new Company(id: 11, name: "Sorvetes e Tech Solutions", description: "Somos bons", creationDate: LocalDate.of(2022, 6, 9))
        OperationStatus status = companyService.save(company)
        println(status.getMessage())
    }

    @Test
    void updateCompanyById() {

        Company company = companyService.findById(11)
        company.setName("Açai e Tech")
        company.setCity("1")
        company.setCreationDate(LocalDate.of(2023, 10, 25))

        OperationStatus status = companyService.updateById(company)
        println(status.getMessage())
    }

    @Test
    void deleteCompanyById() {
        OperationStatus status = companyService.deleteById(11)
        println(status.getMessage())
    }

}

