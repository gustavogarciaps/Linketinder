package model


import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class EmpresaTest {
    static Empresa empresa

    @BeforeAll
    static void instanciaEmpresa(){
        empresa = new Empresa()

        empresa.setNome("NESTLE")
        empresa.setEmail("contato@nestle.com")
        empresa.setInscricao("123.456.789-10")
        empresa.setCEP("38.200-000")
        empresa.setEstado("MG")
        empresa.setPais("BRASIL")
        empresa.setDescricao("QUALQUER")
    }

    @Test
    void consultarEmpresa(){
        assertEquals("NESTLE",empresa.getNome())
        assertEquals("contato@nestle.com",empresa.getEmail())
        assertEquals("123.456.789-10",empresa.getInscricao())
        assertEquals("38.200-000",empresa.getCEP())
        assertEquals("MG",empresa.getEstado())
        assertEquals("BRASIL",empresa.getPais())
        assertEquals("QUALQUER",empresa.getDescricao())
    }

}
