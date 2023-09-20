import { Candidato } from "../models/Candidato";
import { Empresa } from "../models/Empresa";
import { Vaga } from "../models/Vaga";

export const guardarCadastro = (chave: string, valor: Candidato | Empresa | Vaga) => {

          const registroJSON = localStorage.getItem(chave);

          let registros: (Candidato | Empresa | Vaga)[] = [];

          if (registroJSON) {
                    registros = JSON.parse(registroJSON) as (Candidato | Empresa | Vaga)[];
          }

          registros.push(valor);

          localStorage.setItem(chave, JSON.stringify(registros));
}


export const recuperarCadastro = (chave: string): Candidato[] | Empresa[] | Vaga[] => {

          const registroJSON = localStorage.getItem(chave);

          if (registroJSON) {
                    return JSON.parse(registroJSON) as Candidato[] | Empresa[] | Vaga[];
          }

          return [];
}

export const deletarChaveCadastro = (chave: string) => {
          localStorage.removeItem(chave);
}

export const atualizarCadastro = (chave: string, valor: Candidato | Empresa | Vaga) => {

          const registros = recuperarCadastro(chave);

          registros.forEach(registro => {

                    if (registro instanceof Candidato || registro instanceof Empresa) {
                              if (registro.nome === valor.nome) {
                                        registro = valor;
                              }
                    }
          });

          localStorage.setItem(chave, JSON.stringify(registros));
}
