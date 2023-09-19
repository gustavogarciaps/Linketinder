import { RepositorioCompetencias } from "../repositories/RepositorioCompetencias";

export class Vaga {
     constructor(private titulo: string,
          private descricao: string,
          private criacao: Date,
          private competencias: RepositorioCompetencias = new RepositorioCompetencias()
     ) { }
}