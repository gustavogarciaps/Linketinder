import instanciarCandidato from "./services/FabricaCandidatos";

const formsCandidato = document.getElementById('forms_candidato') as HTMLFormElement;

formsCandidato.addEventListener('submit', function (event: SubmitEvent) {
     console.log("disparou o evento");
     event.preventDefault();
     instanciarCandidato(formsCandidato);
});