package entities

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

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
                new Jobs(id: 1, title: "Desenvolvedor HTML", description: "Conhecimento avançado em HTML")
        )

        Jobs job = company.getJobs()[0]
        assertEquals("Desenvolvedor HTML", job.getTitle())
    }

    @Test
    void companyLikeToCandidate(){

        CandidateDTO candidate = new CandidateDTO(name: "Gustavo")
        CompanyLikes companyLikes = new CompanyLikes(company: company)

        companyLikes.like(candidate)

        assertEquals(candidate, companyLikes.getCandidates()[0])

    }

}

