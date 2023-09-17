import instanciarCandidato from "./services/FabricaCandidatos";

const formsCandidato = document.getElementById('formsCandidato') as HTMLFormElement;

formsCandidato.addEventListener('submit', function (event: SubmitEvent) {
     console.log("disparou o evento");
     event.preventDefault();
     instanciarCandidato(formsCandidato);
});