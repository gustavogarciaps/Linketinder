import { carregarCompetencias } from "./view/CarregarCompetencias"
import { instanciarCandidato } from "./services/FabricaCandidatos";
import { adicionarEventoSeExistir } from "./view/CarregarFormularios";
import { carregarVagas } from "./view/CarregarVagas";

document.addEventListener('DOMContentLoaded', function () {
     carregarCompetencias();
     carregarVagas();
});

adicionarEventoSeExistir('forms_candidato', 'submit', function (event: Event) {
     const formsCandidato = document.getElementById('forms_candidato') as HTMLFormElement;
     instanciarCandidato(formsCandidato);
});







