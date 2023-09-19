import { Competencia } from "../models/Competencia"

export class RepositorioCompetencias {
     private competencias: Competencia[] = []
     
     public adicionarCompetencia(competencia: Competencia[]): void {
          this.competencias = competencia;
     }
     
     public removerCompetencia(competencia: Competencia): void {
          const index = this.competencias.indexOf(competencia)
          this.competencias.splice(index, 1)
     }
     
     public listarCompetencias(): Competencia[] {
          return this.competencias
     }
}