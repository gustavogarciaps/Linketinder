import { carregarCompetencias } from "./view/CarregarCompetencias"
import { selecionarCompetencias, instanciarCandidato } from "./services/FabricaCandidatos";
import { Candidato } from "./models/Candidato";
import { adicionarEventoSeExistir } from "./view/CarregarFormularios";

document.addEventListener('DOMContentLoaded', function () {
     carregarCompetencias();
});

adicionarEventoSeExistir('forms_candidato', 'submit', function (event: Event) {
     const formsCandidato = document.getElementById('forms_candidato') as HTMLFormElement;
     instanciarCandidato(formsCandidato);
});

adicionarEventoSeExistir('forms_competencias_candidato', 'submit', function (event: Event) {
     const formsCompetenciasCandidato = document.getElementById('forms_competencias_candidato') as HTMLFormElement;
     selecionarCompetencias(formsCompetenciasCandidato);
});







