import { RepositorioCompetencias } from "../repositories/RepositorioCompetencias";
import { Pessoa } from "./Pessoa";

export class Candidato extends Pessoa {
     private _dataNascimento: Date | null;
     private _competencias: RepositorioCompetencias = new RepositorioCompetencias();

     constructor(dados: Candidato | any = {}) {
          const {
               nome = '',
               email = '',
               inscricao = '',
               cep = '',
               estado = '',
               pais = '',
               descricao = '',
               dataNascimento = null
          } = dados;

          super(nome, email, inscricao, cep, estado, pais, descricao);
          this._dataNascimento = dataNascimento;
     }

     get dataNascimento(): Date | null {
          return this._dataNascimento;
     }

     set dataNascimento(dataNascimento: Date | null) {
          this._dataNascimento = dataNascimento;
     }

     get competencias(): RepositorioCompetencias {
          return this._competencias;
     }

     static fromJSON(json: any): Candidato {
          return new Candidato(json);
     }
}
