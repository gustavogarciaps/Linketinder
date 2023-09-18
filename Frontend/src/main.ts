import { Candidato } from "./models/Candidato";
import { instanciarCandidato, selecionarMarcados } from "./services/FabricaCandidatos";

const carregarCompetencias = () => {
     

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
     const referenciaCandidato = instanciarCandidato(formsCandidato);
     console.log(referenciaCandidato);
});

adicionarEventoSeExistir('forms_competencias_candidato', 'submit', function (event: Event) {
     console.log("enviado forms competencia");
     event.preventDefault();
     const formsCompetenciasCandidato = document.getElementById('forms_competencias_candidato') as HTMLFormElement;
     selecionarMarcados(formsCompetenciasCandidato);
});


