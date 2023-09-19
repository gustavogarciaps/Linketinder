import { Candidato } from "../models/Candidato";
import { Competencia } from "../models/Competencia";
import { instanciarCompetencias } from "./FabricaCompetencias";

let referenciaCandidato: Candidato;

export const instanciarCandidato = (formsCandidato: HTMLFormElement) => {

     const formData = new FormData(formsCandidato);

     const nome = formData.get('nome_candidato') as string;
     const email = formData.get('email_candidato') as string;
     const inscricao = formData.get('inscricao_candidato') as string;
     const CEP = formData.get('CEP_candidato') as string;
     const estado = formData.get('estado_candidato') as string;
     const pais = formData.get('pais_candidato') as string;
     const descricao = formData.get('descricao_candidato') as string;
     const dataNascimento = formData.get('data_nascimento_candidato') as string;

     const conversaoDataNascimento = new Date(dataNascimento);

     referenciaCandidato = new Candidato(nome, email, inscricao, CEP, estado, pais, descricao, conversaoDataNascimento) as Candidato;

     return referenciaCandidato;
}

export const selecionarCompetencias = (formsCompetenciasCandidato: HTMLFormElement) => {

     let selecionados: Competencia[] = [];

     const competenciasCadastradas = instanciarCompetencias();
     const competenciasSelecionadas = formsCompetenciasCandidato.querySelectorAll('.btn-check');

     competenciasSelecionadas.forEach((checkbox) => {

          if (checkbox instanceof HTMLInputElement && checkbox.checked) {
               const checkboxId = parseInt(checkbox.id.split('-')[2]) as number;
               selecionados.push(competenciasCadastradas[checkboxId]);
          }

     });

     referenciaCandidato.Competencias.adicionarCompetencia(selecionados);
};
