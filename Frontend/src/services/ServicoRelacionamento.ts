import { Candidato } from "../models/Candidato";
import { desestruturarJSON } from "./FabricaCandidatos";
import { recuperarCadastro } from "./ServicoArmazenamento"

interface Relacionamento {
          competencia: string;
          quantidade: number;
}

export const carregarCompetenciasCandidatos = (): Relacionamento[] => {

          const candidatosJSON = recuperarCadastro('candidatos');
          const candidatos: Candidato[] = [];

          candidatosJSON.forEach((candidatoJSON: any) => {
                    candidatos.push(desestruturarJSON(candidatoJSON) as Candidato);
          });

          return (relacionarCompetenciasCandidatos(candidatos));

}

const relacionarCompetenciasCandidatos = (candidatos: Candidato[]): Relacionamento[] => {

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