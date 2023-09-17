import { RepositorioCompetencia } from "../repositories/RepositorioCompetencia";

export class Vaga {
     constructor(private titulo: string,
          private descricao: string,
          private criacao: Date,
          private competencias: RepositorioCompetencia = new RepositorioCompetencia()
     ) { }
}