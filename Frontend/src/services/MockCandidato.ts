import { Candidato } from "../models/Candidato";
import { Competencia } from "../models/Competencia";
import { RepositorioCandidatos } from "./../repositories/RepositorioCandidatos";
import { guardarCadastro } from "./ServicoArmazenamento";

export const mockCandidatos = () => {

          const repositorioCandidatos = new RepositorioCandidatos();

          repositorioCandidatos.adicionarCandidato
                    (new Candidato
                              ("Nome 1", "Email 1", "Inscrição 1", "CEP 1", "Estado 1", "País 1", "Descrição 1",
                                        new Date("1990-01-01")));
          repositorioCandidatos.adicionarCandidato
                    (new Candidato
                              ("Nome 2", "Email 2", "Inscrição 2", "CEP 2", "Estado 2", "País 2", "Descrição 2",
                                        new Date("1990-01-01")));
          repositorioCandidatos.adicionarCandidato
                    (new Candidato
                              ("Nome 3", "Email 3", "Inscrição 3", "CEP 3", "Estado 3", "País 3", "Descrição 3",
                                        new Date("1990-01-01")));
          repositorioCandidatos.adicionarCandidato
                    (new Candidato
                              ("Nome 4", "Email 4", "Inscrição 4", "CEP 4", "Estado 4", "País 4", "Descrição 4",
                                        new Date("1990-01-01")));
          repositorioCandidatos.adicionarCandidato
                    (new Candidato
                              ("Nome 5", "Email 5", "Inscrição 5", "CEP 5", "Estado 5", "País 5", "Descrição 5",
                                        new Date("1990-01-01")));
          repositorioCandidatos.adicionarCandidato
                    (new Candidato
                              ("Nome 6", "Email 6", "Inscrição 6", "CEP 6", "Estado 6", "País 6", "Descrição 6",
                                        new Date("1990-01-01")));
          repositorioCandidatos.adicionarCandidato
                    (new Candidato
                              ("Nome 7", "Email 7", "Inscrição 7", "CEP 7", "Estado 7", "País 7", "Descrição 7",
                                        new Date("1990-01-01")));

          repositorioCandidatos.listarCandidatos().forEach(candidato => {
                    guardarCadastro('candidatos', candidato);
          });

}