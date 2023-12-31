import { carregarCompetencias } from "./view/CarregarCompetencias"
import { instanciarCandidato, validacaoCandidato } from "./services/FabricaCandidatos";
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
     event.preventDefault();
     instanciarCandidato();
});

adicionarEventoSeExistir('forms_empresa', 'submit', function (event: Event) {
     event.preventDefault();
     instanciarEmpresa();
});

adicionarEventoSeExistir('forms_vaga', 'submit', function (event: Event) {
     const formsVaga = document.getElementById('forms_vaga') as HTMLFormElement;
     instanciarVaga(formsVaga);
});

//mockCandidatos();
//mockVagas();






