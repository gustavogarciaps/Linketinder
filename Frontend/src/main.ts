import { Candidato } from "./models/Candidato";
import { instanciarCandidato, selecionarCompetencias } from "./services/FabricaCandidatos";
import { instanciarCompetencias } from "./services/FabricaCompetencias";

document.addEventListener('DOMContentLoaded', function () {
     carregarCompetencias();
});

const carregarCompetencias = () => {

     const HTMLCompetencias = document.querySelector("#carregar_competencias");

     const competencias = instanciarCompetencias();

     if (HTMLCompetencias) {
          competencias.forEach(competencia => {

               HTMLCompetencias.innerHTML += `
               <div class="col p-1">
                    <input type="checkbox" class="btn-check" id="btn-check-${competencias.indexOf(competencia)}">
                    <label class="btn btn-outline-dark" for="btn-check-${competencias.indexOf(competencia)}">${competencia.Nome}</label>
               </div>
          `;
          });
     }

}

const adicionarEventoSeExistir = (elementId: string, evento: string, callback: EventListenerOrEventListenerObject) => {

     const element = document.getElementById(elementId);

     if (element) {
          element.addEventListener(evento, callback);
     }

}

let referenciaCandidato: Candidato;

adicionarEventoSeExistir('forms_candidato', 'submit', function (event: Event) {

     event.preventDefault();
     const formsCandidato = document.getElementById('forms_candidato') as HTMLFormElement;
     referenciaCandidato = instanciarCandidato(formsCandidato);

});

adicionarEventoSeExistir('forms_competencias_candidato', 'submit', function (event: Event) {

     event.preventDefault();
     const formsCompetenciasCandidato = document.getElementById('forms_competencias_candidato') as HTMLFormElement;
     selecionarCompetencias(formsCompetenciasCandidato);

});


