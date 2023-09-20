import { Candidato } from "../models/Candidato";
import { Empresa } from "../models/Empresa";

export const guardarCadastro = (chave: string, valor: Candidato | Empresa) => {

          const registroJSON = localStorage.getItem(chave);

          let registros: (Candidato | Empresa)[] = [];

          if (registroJSON) {
                    registros = JSON.parse(registroJSON) as (Candidato | Empresa)[];
          }

          registros.push(valor);

          localStorage.setItem(chave, JSON.stringify(registros));
}


export const recuperarCadastro = (chave: string): Candidato[] | Empresa[] => {
          
          const registroJSON = localStorage.getItem(chave);

          if (registroJSON) {
                    return JSON.parse(registroJSON) as Candidato[] | Empresa[] ;
          }

          return [];
}

export const deletarChaveCadastro = (chave: string) => {
          localStorage.removeItem(chave);
}
