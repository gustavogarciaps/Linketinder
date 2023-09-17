import { Candidato } from "../models/Candidato"

export class GerenciamentoCandidatos {
     private candidatos: Candidato[] = []

     public adicionarCandidato(candidato: Candidato): void {
          this.candidatos.push(candidato)
     }

     public removerCandidato(candidato: Candidato): void {
          const index = this.candidatos.indexOf(candidato)
          this.candidatos.splice(index, 1)
     }

     public listarCandidatos(): Candidato[] {
          return this.candidatos
     }
}

