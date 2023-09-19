import { RepositorioCompetencias } from "../repositories/RepositorioCompetencias";
import { Pessoa } from "./Pessoa";

export class Candidato extends Pessoa {

     constructor(
          _nome?: string,
          _email?: string,
          _inscricao?: string,
          _cep?: string,
          _estado?: string,
          _pais?: string,
          _descricao?: string,
          private _dataNascimento?: Date | null,
          private _competencias: RepositorioCompetencias = new RepositorioCompetencias()) {
          super(_nome, _email, _inscricao, _cep, _estado, _pais, _descricao)
     }

     get dataNascimento(): Date | null {
          return this._dataNascimento ?? null;
     }

     set dataNascimento(dataNascimento: Date | null) {
          this._dataNascimento = dataNascimento;
     }

     get competencias() {
          return this._competencias ?? "";
     }

}