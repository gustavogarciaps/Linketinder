import { Candidato } from "../models/Candidato";
import { Competencia } from "../models/Competencia";
import { recuperarCadastro } from "../services/ServicoArmazenamento";

export const carregarCandidatos = () => {

          const candidatosJSON = recuperarCadastro('candidatos') as Candidato[];

          const candidatos: Candidato[] = candidatosJSON.map((candidatoJSON) => {

                    const competenciasJSON = candidatoJSON.competencias || [];

                    const competencias: Competencia[] = competenciasJSON.map(

                              (competenciaJSON: any) => {
                                        return new Competencia(competenciaJSON._nome);
                              });

                    return new Candidato({
                              nome: candidatoJSON.nome,
                              email: candidatoJSON.email,
                              inscricao: candidatoJSON.inscricao,
                              cep: candidatoJSON.cep,
                              estado: candidatoJSON.estado,
                              pais: candidatoJSON.pais,
                              competencias: competencias
                    });
          });

          candidatos.forEach(candidato => {

          });
}

