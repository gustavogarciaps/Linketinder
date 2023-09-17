import { Candidato } from "../models/Candidato";

export class RepositorioCandidatos {

     private candidatos: Candidato[] = [];

     constructor() {
          this.candidatos = []
     }
     adicionarCandidato(candidato: Candidato): void {
          this.candidatos.push(candidato)
     }
     removerCandidato(candidato: Candidato): void {
          const index = this.candidatos.indexOf(candidato)
          this.candidatos.splice(index, 1)
     }
     listarCandidatos(): Candidato[] {
          return this.candidatos
     }
}