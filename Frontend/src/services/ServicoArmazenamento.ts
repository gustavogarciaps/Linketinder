export const guardarCadastro = <T>(chave: string, valor: T) => {
          const registroJSON = localStorage.getItem(chave);
          let registros: T[] = [];

          if (registroJSON) {
                    registros = JSON.parse(registroJSON) as T[];
          }

          console.log("Registros", registros)

          registros = [...registros, ...[valor]];

          localStorage.setItem(chave, JSON.stringify(registros));
}

export const recuperarCadastro = <T>(chave: string): T[] => {
          const registroJSON = localStorage.getItem(chave);

          if (registroJSON) {
                    return JSON.parse(registroJSON) as T[];
          }

          return [];
}

export const deletarChaveCadastro = (chave: string) => {
          localStorage.removeItem(chave);
}

export const atualizarCadastro = <T>(chave: string, valor: T, indice: number) => {
          const registrosJSON = localStorage.getItem(chave);
          let registros: T[] = [];

          if (registrosJSON) {
                    registros = JSON.parse(registrosJSON) as T[];
          }

          if (indice >= 0 && indice < registros.length) {

                    console.log("Registros", registros[indice])
                    registros[indice] = valor;
                    console.log("Valor", valor)
                    localStorage.setItem(chave, JSON.stringify(registros));
          }
}
