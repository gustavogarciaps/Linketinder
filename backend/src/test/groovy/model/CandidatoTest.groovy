package model

import br.com.linketinder.model.Candidato
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test

import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.*;

class CandidatoTest {

    static Candidato candidato

    @BeforeAll
    static void instanciaCandidato(){
        candidato = new Candidato()

        candidato.setNome("GUSTAVO")
        candidato.setEmail("gustavo@gmail.com")
        candidato.setInscricao("123.456.789-10")
        candidato.setCEP("38.200-000")
        candidato.setEstado("MG")
        candidato.setPais("BRASIL")
        candidato.setDescricao("SOU O GUSTAVO")
        candidato.setDataNascimento(LocalDate.of(2001,6,9))
    }

    @Test
    void cadastrarCompetenciaString(){
        candidato.getCompetencia().setCompetencia("JAVA")
        candidato.getCompetencia().setCompetencia("PYTHON")

        def expect = new ArrayList(["JAVA","PYTHON"])
        def result = candidato.getCompetencia().getCompetencia()

        assertEquals(expect, result)
    }

    @Test
    void cadastrarCompetenciaArray(){
        candidato.getCompetencia().setCompetencia(new ArrayList(["JAVA", "PYTHON"]))

        def expect = new ArrayList(["JAVA","PYTHON"])
        def result = candidato.getCompetencia().getCompetencia()

        assertEquals(expect, result)
    }

    @Test
    void consultarCandidato(){
        assertEquals("GUSTAVO",candidato.getNome())
        assertEquals("gustavo@gmail.com",candidato.getEmail())
        assertEquals("123.456.789-10",candidato.getInscricao())
        assertEquals("38.200-000",candidato.getCEP())
        assertEquals("MG",candidato.getEstado())
        assertEquals("BRASIL",candidato.getPais())
        assertEquals("SOU O GUSTAVO",candidato.getDescricao())
        assertEquals(LocalDate.of(2001,6,9),candidato.getDataNascimento())
    }

    @Test
    void consultarIdadeCandidato(){
        assertEquals(22,candidato.getIdade())
    }
}
