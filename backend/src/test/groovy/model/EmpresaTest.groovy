package model


import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class EmpresaTest {
    static Empresa empresa

    @BeforeAll
    static void instanciaEmpresa(){
        empresa = new Empresa()

        empresa.setRazaoSocial("NESTLE")
        empresa.setInscricao("123.456.789-10")
        empresa.setCEP("38.200-000")
        empresa.setPais("BRASIL")
        empresa.setDescricao("QUALQUER")
    }

    @Test
    void consultarEmpresa(){
        assertEquals("NESTLE",empresa.getRazaoSocial())
        assertEquals("123.456.789-10",empresa.getInscricao())
        assertEquals("38.200-000",empresa.getCEP())
        assertEquals("BRASIL",empresa.getPais())
        assertEquals("QUALQUER",empresa.getDescricao())
    }

}
