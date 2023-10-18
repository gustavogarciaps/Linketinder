package model


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test

import java.lang.reflect.Array
import java.time.LocalDate

import static org.junit.jupiter.api.Assertions.*;

class CandidatoTest {

    static Candidato candidato

    @BeforeAll
    static void instanciaCandidato(){

        candidato = new Candidato()

        candidato.setNome("GUSTAVO")
        candidato.setSobrenome("GARCIA")
        candidato.setInscricao("123.456.789-10")
        candidato.setCEP("38.200-000")
        candidato.setCidade(1)
        candidato.setPais("BRASIL")
        candidato.setDescricao("SOU O GUSTAVO")
        candidato.setDataNascimento(LocalDate.of(2001,6,9))
    }

    @Test
    void cadastrarCompetencias(){

        ArrayList<Competencia> competencia = new ArrayList<>()

        competencia.add("JAVA");
        competencia.add("PYTHON")

        candidato.setCompetencias(competencia)

        def expect = new ArrayList(["JAVA","PYTHON"])
        def result = candidato.getCompetencias()

        assertEquals(expect, result)
    }

    @Test
    void consultarCandidato(){
        assertEquals("GUSTAVO",candidato.getNome())
        assertEquals("GARCIA",candidato.getSobrenome())
        assertEquals("123.456.789-10",candidato.getInscricao())
        assertEquals("38.200-000",candidato.getCEP())
        assertEquals(1,candidato.getCidade())
        assertEquals("BRASIL",candidato.getPais())
        assertEquals("SOU O GUSTAVO",candidato.getDescricao())
        assertEquals(LocalDate.of(2001,6,9),candidato.getDataNascimento())
    }

    @Test
    void consultarIdadeCandidato(){
        assertEquals(22,candidato.getIdade())
    }

}
