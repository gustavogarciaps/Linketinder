import { RepositorioCompetencias } from "../repositories/RepositorioCompetencias";
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
          private competencias: RepositorioCompetencias = new RepositorioCompetencias()) {
          super(nome, email, inscricao, CEP, estado, pais, descricao)
     }

     get DataNascimento() {
          return this.dataNascimento;
     }

     get Competencias() {
          return this.competencias;
     }

     set Competencias(competencias: RepositorioCompetencias) {
          this.competencias = competencias;
     }
}