import { Candidato } from "../models/Candidato";
import { deletarChaveCadastro, guardarCadastro } from "./ServicoArmazenamento";

export const mockCandidatos = () => {

          const candidatos = [] as Candidato[];

          candidatos.push(new Candidato({
                    nome: 'Nome 1',
                    email: 'E-mail 1',
                    linkedin: 'Linkedin 1',
                    inscricao: 'Inscrição 1',
                    cep: 'CEP 1',
                    estado: 'Estado 1',
                    pais: 'País 1',
                    descricao: 'Descrição 1',
                    dataNascimento: new Date('2000-01-01'),
                    formacao: 'Formação 1',
          }));

          deletarChaveCadastro('candidatos');

          candidatos.forEach(candidato => {
                    guardarCadastro('candidatos', candidato);
          });

}