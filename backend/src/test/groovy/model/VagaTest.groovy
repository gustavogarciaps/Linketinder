package model

import br.com.linketinder.model.Vaga
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class VagaTest {

    static Vaga vaga;

    @BeforeAll
    static void instanciarVaga(){
        vaga = new Vaga();

        vaga.setTitulo("Teste de Titulo");
        vaga.setDescricao("Teste de Descricao");
    }

    @Test
    void criacaoVaga(){

        def expectNome = "Teste de Titulo";
        def resultNome = vaga.getTitulo();

        assertEquals(expectNome,resultNome);
    }
}
