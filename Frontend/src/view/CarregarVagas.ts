import { instanciarVagas } from "./../services/FabricaVagas";

export const carregarVagas = () => {

          const HTMLCandidatos = document.querySelector("#");

          const vagas = instanciarVagas();

          if (HTMLCandidatos) {
                    vagas.forEach(vaga => {

                              HTMLCandidatos.innerHTML += ``;
                    });
          }

}
