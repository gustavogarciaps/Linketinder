export const guardarCadastro = (chave: string, valor: object) => {
          const registroJSON = localStorage.getItem(chave);
          let registros: object[] = [];

          if (registroJSON) {
                    registros = JSON.parse(registroJSON) as object[];
          }

          registros = [...registros, ...[valor]];

          localStorage.setItem(chave, JSON.stringify(registros));
}

export const recuperarCadastro = (chave: string): object[] => {
          const registroJSON = localStorage.getItem(chave);

          if (registroJSON) {
                    return JSON.parse(registroJSON) as object[];
          }

          return [];
}

export const deletarChaveCadastro = (chave: string) => {
          localStorage.removeItem(chave);
}

export const atualizarCadastro = (chave: string, valor: object, indice: number) => {
          const registrosJSON = localStorage.getItem(chave);

          if (registrosJSON) {
                    const registros: object[] = JSON.parse(registrosJSON) as object[];

                    if (indice >= 0 && indice < registros.length) {
                              registros[indice] = valor;
                              localStorage.setItem(chave, JSON.stringify(registros));
                    } else {
                              console.error('Índice fora dos limites.');
                    }
          } else {
                    console.error('Nenhum registro encontrado para atualização.');
          }
}
