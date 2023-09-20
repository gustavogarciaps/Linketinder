import { RepositorioCompetencias } from "../repositories/RepositorioCompetencias";

export class Vaga {
     constructor(private _titulo: string,
          private _descricao: string,
          private _criacao: Date,
          private _competencias: RepositorioCompetencias = new RepositorioCompetencias()
     ) { }

     get titulo(): string {
          return this._titulo;
     }

     get descricao(): string {
          return this._descricao;
     }

     get criacao(): Date | null {
          return this._criacao ?? null;
     }

     get competencias(): RepositorioCompetencias {
          return this._competencias;
     }
}