export class RepositorioCompetencia {
     private competencias: string[] = []
     
     public adicionarCompetencia(competencia: string): void {
          this.competencias.push(competencia)
     }
     
     public removerCompetencia(competencia: string): void {
          const index = this.competencias.indexOf(competencia)
          this.competencias.splice(index, 1)
     }
     
     public listarCompetencias(): string[] {
          return this.competencias
     }
}