import { RepositorioCompetencia } from "../repositories/RepositorioCompetencia";
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
          private dataNascimento: Date,
          private competencias: RepositorioCompetencia = new RepositorioCompetencia()) {
          super(nome, email, inscricao, CEP, estado, pais, descricao)
     }

}