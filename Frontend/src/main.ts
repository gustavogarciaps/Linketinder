import { carregarCompetencias } from "./view/CarregarCompetencias"
import { instanciarCandidato } from "./services/FabricaCandidatos";
import { adicionarEventoSeExistir } from "./view/CarregarFormularios";
import { carregarVagas } from "./view/CarregarVagas";
import { instanciarEmpresa } from "./services/FabricaEmpresas";
import { mockCandidatos } from "./services/MockCandidato";

document.addEventListener('DOMContentLoaded', function () {
     carregarCompetencias();
     carregarVagas();
});

adicionarEventoSeExistir('forms_candidato', 'submit', function (event: Event) {
     const formsCandidato = document.getElementById('forms_candidato') as HTMLFormElement;
     instanciarCandidato(formsCandidato);
});

adicionarEventoSeExistir('forms_empresa', 'submit', function (event: Event) {
     const formsEmpresa = document.getElementById('forms_empresa') as HTMLFormElement;
     instanciarEmpresa(formsEmpresa);
});

mockCandidatos();







