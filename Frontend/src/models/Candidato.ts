import { Pessoa } from "./Pessoa";

export class Candidato extends Pessoa {

     constructor(
          nome: string,
          email: string,
          inscricao: string,
          CEP: string,
          estado: string,
          pais: string,
          descricao: string,
          private competencias: string[],
          private dataNascimento: Date) {
          super(nome, email, inscricao, CEP, estado, pais, descricao)
     }
}