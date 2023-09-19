import { Vaga } from "../models/Vaga";

export class RepositorioVagas {

     private vagas: Vaga[] = []

     public adicionarVaga(vaga: Vaga): void {
          this.vagas.push(vaga)
     }

     public adicionarVagas(vaga: Vaga[]): void {
          this.vagas = vaga;
     }

     public removeVaga(vaga: Vaga): void {
          const index = this.vagas.indexOf(vaga)
          this.vagas.splice(index, 1)
     }

     public listarVaga(): Vaga[] {
          return this.vagas
     }
}