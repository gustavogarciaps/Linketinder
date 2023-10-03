package model

import br.com.linketinder.model.Competencia
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class CompetenciaTest {

        static Competencia competencia;

        @BeforeAll
        static void instanciarCompetencia(){
            competencia = new Competencia(null, "Aleatória");
        }

        @Test
        void criarCompetencia(){

            def expectNome = "Aleatória";
            def resultNome = competencia.getNome();

            assertEquals(expectNome,resultNome);
        }
}

