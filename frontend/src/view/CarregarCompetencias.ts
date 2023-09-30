import { instanciarCompetencias } from "./../services/FabricaCompetencias";

export const carregarCompetencias = () => {

     const HTMLCompetencias = document.querySelector("#carregar_competencias");

     const competencias = instanciarCompetencias();

     if (HTMLCompetencias) {
          competencias.forEach(competencia => {

               HTMLCompetencias.innerHTML += `
                    <div class="col p-1">
                         <input type="checkbox" class="btn-check" id="btn-check-${competencias.indexOf(competencia)}">
                         <label class="btn btn-outline-dark" for="btn-check-${competencias.indexOf(competencia)}">${competencia.nome}</label>
                    </div>`;
          });
     }

}
