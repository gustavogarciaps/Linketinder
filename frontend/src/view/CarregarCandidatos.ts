import { recuperarCadastro } from "../services/ServicoArmazenamento";
import { desestruturarJSON } from "../services/FabricaCandidatos";
import { Candidato } from "../models/Candidato";

export const carregarCandidatos = () => {

          const candidatosJSON = recuperarCadastro('candidatos') as any[];

          if (!candidatosJSON) {
                    console.error('Não há candidatos cadastrados.');
                    return;
          }

          const candidatos: Candidato[] = [];

          candidatosJSON.forEach(
                    (candidatoJSON: any) => {
                              candidatos.push(desestruturarJSON(candidatoJSON) as Candidato);
                    }
          );

          candidatos.forEach(candidato => {
                    montarHTMLCandidatos(candidato);
          });
}

const montarHTMLCandidatos = (candidato: Candidato) => {
          const HTMLCandidatos = document.querySelector("#carregar_candidatos");

          if (!HTMLCandidatos) {
                    console.error('Não há candidatos cadastrados.');
                    return;
          }

          HTMLCandidatos.innerHTML += `
    <div class="col">
        <div class="card text-center text-bg-light mb-3 p-3">
            <h5 class="card-header fw-bold">${candidato.formacao}</h5>
            <div class="card-body placeholder-glow">
                <div class="row d-flex">
                <div class="col">
                <h5 class="card-title placeholder">${candidato.nome.split(" ")[0] + " " + candidato.nome.split(" ")[1]}</h5>
                </div>
                <div class="col">
                <i class='fas fa-question-circle' style='font-size:24px' data-bs-toggle="tooltip"
                    data-bs-title="${candidato.descricao}"></i>
                </div>
                </div>
                <p class="card-text">${candidato.descricao}</p>
                ${carregarCompetencias(candidato)}
                <hr>
                <div class="hstack gap-3">
                    <div class="col p-2">
                        <button class="btn btn-success btn-lg"><i class="far fa-thumbs-up"></i></button>
                    </div>
                    <div class="col p-2">
                        <strong>
                            <span>${Math.floor(Math.random() * 100) + 1}% compatível.</span>
                        </strong>
                    </div>
                    <div class="col p-2">
                        <button class="btn btn-danger btn-lg"><i class="far fa-thumbs-down"></i></button>
                    </div>
                </div>
                <div class="card-body alert alert-success" role="alert">
                    Você <strong>curtiu</strong> esta Vaga!
                </div>
                <div class="card-body alert alert-danger" role="alert">
                    Você <strong>não curtiu</strong> esta Vaga!
                </div>
            </div>
        </div>
    </div>`;
}

const carregarCompetencias = (candidato: Candidato) => {
          let HTMLCompetencias = '';
          candidato.competencias.forEach(competencia => {
                    HTMLCompetencias += `<span class="badge text-bg-warning p-1">#${competencia.nome.toLocaleUpperCase()}</span>`;
          });
          return HTMLCompetencias;
}

const definirDataPublicacao = (data: Date | null) => {

          if (data) {
                    const dataAtual = new Date();
                    const dataPublicacao = new Date(data);
                    const diferenca = dataAtual.getTime() - dataPublicacao.getTime();
                    const dias = Math.floor(diferenca / (1000 * 3600 * 24));

                    if (dias > 0) {
                              return `Publicado há ${dias} ${dias > 1 ? 'dias' : 'dia'}.`;
                    }
                    return `Publicado hoje.`;
          }

          return `Data de publicação não definida.`
}
