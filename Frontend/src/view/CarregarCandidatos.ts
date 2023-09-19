import { recuperarCadastroFuncionario } from "./../services/ArmazenamentoCandidatos";

export const carregarCandidatos = () => {

          const HTMLCandidatos = document.querySelector("#");

          const candidatos = recuperarCadastroFuncionario();

          if (HTMLCandidatos) {
                    candidatos.forEach(candidato => {

                              HTMLCandidatos.innerHTML += ``;
                    });
          }

}
