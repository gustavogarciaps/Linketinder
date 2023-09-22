import { carregarCompetencias } from "./view/CarregarCompetencias"
import { instanciarCandidato } from "./services/FabricaCandidatos";
import { adicionarEventoSeExistir } from "./view/CarregarFormularios";
import { carregarVagas } from "./view/CarregarVagas";
import { instanciarEmpresa } from "./services/FabricaEmpresas";
import { instanciarVaga } from "./services/FabricaVagas";
import { carregarCandidatos } from "./view/CarregarCandidatos";
import { carregarCompetenciasCandidatos } from "./services/ServicoRelacionamento";
import { carregarGrafico } from "./view/CarregarGrafico";

document.addEventListener('DOMContentLoaded', function () {
     carregarCompetencias();
     carregarVagas();
     carregarCandidatos();
     carregarCompetenciasCandidatos();
     carregarGrafico();
     console.error("DOM main.ts carregado");
});

adicionarEventoSeExistir('forms_candidato', 'submit', function (event: Event) {
     const formsCandidato = document.getElementById('forms_candidato') as HTMLFormElement;
     instanciarCandidato(formsCandidato);
});

adicionarEventoSeExistir('forms_empresa', 'submit', function (event: Event) {
     const formsEmpresa = document.getElementById('forms_empresa') as HTMLFormElement;
     instanciarEmpresa(formsEmpresa);
});

adicionarEventoSeExistir('forms_vaga', 'submit', function (event: Event) {
     const formsVaga = document.getElementById('forms_vaga') as HTMLFormElement;
     instanciarVaga(formsVaga);
});

//mockCandidatos();
//mockVagas();






