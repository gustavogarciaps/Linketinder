import { Candidato } from "../models/Candidato";

export const guardarCadastroFuncionario = (candidato: Candidato) => {
     const cadastroCandidatoJSON = localStorage.getItem('cadastro_candidato');
     let cadastroCandidato: Candidato[] = [];

     if (cadastroCandidatoJSON) {
          cadastroCandidato = JSON.parse(cadastroCandidatoJSON) as Candidato[];
     }

     cadastroCandidato.push(candidato);

     localStorage.setItem('candidatos', JSON.stringify(cadastroCandidato));
}

export const recuperarCadastroFuncionario = (): Candidato[] => {
     const cadastroCandidatoJSON = localStorage.getItem('candidatos');

     if (cadastroCandidatoJSON) {
          return JSON.parse(cadastroCandidatoJSON) as Candidato[];
     }

     return [];
}

export const deletarCadastroFuncionario = () => {
     localStorage.removeItem('candidatos');
}
