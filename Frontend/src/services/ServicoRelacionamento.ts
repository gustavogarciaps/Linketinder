import { Candidato } from "../models/Candidato";
import { Competencia } from "../models/Competencia";
import { desestruturarJSON } from "./FabricaCandidatos";
import { instanciarCompetencias } from "./FabricaCompetencias";
import { recuperarCadastro } from "./ServicoArmazenamento"

export const carregarCompetenciasCandidatos = () => {

          const candidatosJSON = recuperarCadastro('candidatos');
          const candidatos: Candidato[] = [];

          candidatosJSON.forEach((candidatoJSON: any) => {
                    candidatos.push(desestruturarJSON(candidatoJSON) as Candidato);
          });

          console.log(relacionarCompetenciasCandidatos(candidatos));

}

const relacionarCompetenciasCandidatos = (candidatos: Candidato[]) => {

          interface Relacionamento {
                    competencia: string;
                    quantidade: number;
          }

          const competencias = instanciarCompetencias();
          const relacionamentos: Relacionamento[] = [];


          candidatos.forEach(candidato => {
                    candidato.competencias.forEach(competencia => {

                              const index = relacionamentos.findIndex(relacionamento => relacionamento.competencia === competencia.nome);

                              if (index < 0) {
                                        relacionamentos.push({ competencia: competencia.nome, quantidade: 1 });
                              } else {
                                        relacionamentos[index].quantidade++;
                              }
                    });
          });

          return relacionamentos;
}