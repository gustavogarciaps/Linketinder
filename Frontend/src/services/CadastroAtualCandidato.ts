import { Candidato } from "../models/Candidato";

export const guardarCadastroFuncionario = (candidato: Candidato) => {
     const cadastroCandidatoJSON = JSON.stringify(candidato);
     localStorage.setItem('cadastro_candidato', cadastroCandidatoJSON);
}

export const recuperarCadastroFuncionario = (): Candidato | null => {
     const cadastroCandidatoJSON = localStorage.getItem('cadastro_candidato');
     
     if (cadastroCandidatoJSON) {
          return JSON.parse(cadastroCandidatoJSON) as Candidato;
     }
     
     return null;
}

export const deletarCadastroFuncionario = () => {
     localStorage.removeItem('cadastro_candidato');
}
