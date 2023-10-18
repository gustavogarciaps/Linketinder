package view

import dao.CompetenciaDAO
import model.Competencia

class MenuCompetencias {

	static private ArrayList<Competencia> competencias;
	static private Scanner scanner = new Scanner(System.in)

	static void exibir() {

		int opcaoMenu = 0

		while (opcaoMenu != 5) {
			println "Menu de Opções:";
			println "1. Visualizar Competências";
			println "2. Cadastrar Nova Competência";
			println "3. Excluir Competência";
			println "4. Atualizar Competência";
			println "5. Voltar"
			print "Escolha uma opção: "

			try {
				opcaoMenu = scanner.nextInt()
				scanner.nextLine()

				switch (opcaoMenu) {
					case 1:
						carregar();
						break;
					case 2:
						criar();
						break;
					case 3:
						deletar();
						break
					case 4:
						atualizar()
						break
					case 5:
						println "Voltando..."
						break
					default:
						println "Opção inválida. Escolha novamente."
						break
				}
			} catch (Exception e) {
				println "Opção inválida. Escolha novamente."
			}

		}

	}

	static void carregar() {

		competencias = CompetenciaDAO.read()

		println("Competências Cadastradas:")
		println("-" * 30);
		println("|id" + ("\t" * 2) + "|" + "nome" + ("\t" * 4))

		competencias.each { c ->
			println("|" + c.getId() + ("\t" * 2) + "|" + c.getNome() + ("\t" * 4))
		}
	}

	static void criar() {
		println("Cadastrar Nova Competência:  (Escreva EXIT para voltar)");
		println("Qual o nome da Competência?");
		print("Competência: ");
		def nome = scanner.nextLine();
		if (nome.equals("EXIT")) {
			return
		}
		CompetenciaDAO.create(new Competencia(nome: nome))

	}

	static void deletar() {
		println("Deletar Competência: (Escreva EXIT para voltar)")
		carregar();
		println("Qual o código (id) da competência?");
		print("Id: ")
		String id = scanner.nextLine();
		if (id.equals("EXIT")) {
			return
		}

		CompetenciaDAO.delete(new Competencia(id: Integer.parseInt(id)))
	}

	static void atualizar() {
		println("Alterar Nome da Competência: (Escreva EXIT para voltar)")
		carregar();
		println("Qual o código (id) da competência?");
		print("Id: ")
		String id = scanner.nextLine();
		if (id.equals("EXIT")) {
			return
		}
		println("Qual o novo Nome?")
		print("Nome: ")
		String nome = scanner.nextLine();
		if (nome.equals("EXIT")) {
			return
		}

		CompetenciaDAO.update(new Competencia(id:Integer.parseInt(id), nome: nome ))
	}

}

