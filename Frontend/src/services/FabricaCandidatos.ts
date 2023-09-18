import { Candidato } from "../models/Candidato";

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

     const referenciaCandidato = new Candidato(nome, email, inscricao, CEP, estado, pais, descricao, conversaoDataNascimento) as Candidato;

     return referenciaCandidato;
}

export const selecionarMarcados = (formsCompetenciasCandidato: HTMLFormElement) => {

     const selecionados = [] as string[];

     const selecionadosDisponiveis = formsCompetenciasCandidato.querySelectorAll('.btn-check'); 



     console.log('Checkboxes Marcados:', selecionadosDisponiveis.item(0));
}