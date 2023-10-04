package br.com.linketinder.view

import br.com.linketinder.database.CompetenciaDAO
import br.com.linketinder.database.EmpresaDAO
import br.com.linketinder.database.UsuarioDAO
import br.com.linketinder.model.Usuario
import br.com.linketinder.services.FabricaCompetencias
import br.com.linketinder.services.FabricaUsuarios

class MenuInicial {

	static Scanner scanner = new Scanner(System.in)
	static ArrayList<Usuario> usuarios = new ArrayList<>()

	static void exibir() {

		def opcaoMenu = 0

		while (opcaoMenu != 4) {

			println "Menu de Opções:"
			println "1. Criar Usuário"
			println "2. Entrar na plataforma"
			println "3. Visualizar Usuários"
			println "4. Sair"
			print "Escolha uma opção: "

			try {
				opcaoMenu = scanner.nextInt()
				scanner.nextLine()

				switch (opcaoMenu) {
					case 1:
						criar();
						break
					case 2:
						acessar();
						break
					case 3:
						carregar()
						break
					case 4:
						println "Saindo..."
						break
					default:
						println "Opção inválida. Escolha novamente."
						break
				}
			} catch (Exception e) {
				println "Entrada inválida. Por favor, insira um número válido."
				scanner.nextLine()
			}
		}
	}

	static void criar() {
		println("Cadastrar Novo Usuário:  (Escreva EXIT para voltar)");
		print("email: ");
		def email = scanner.nextLine();
		if (email.equals("EXIT")) {
			return
		}
		print("senha: ");
		def senha = scanner.nextLine();
		if (senha.equals("EXIT")) {
			return
		}
		println(FabricaUsuarios.criar(email, senha));
	}

	static void acessar(){
		println("Acessar:  (Escreva EXIT para voltar)");
		println "1. Candidato"
		println "2. Empresa"
		print "opção: "
		def opcaoMenu = scanner.nextInt()
		scanner.nextLine()

		switch(opcaoMenu){
			case 1:
				MenuCandidatos.exibir()
				break
			case 2:
				MenuEmpresas.exibir()
				break
			default:
				println("Voltando...")
				return
				break;
		}
	}

	static void carregar(){
		usuarios = UsuarioDAO.read()

		println("Usuários Cadastrados:")
		println("-" * 80);
		println("-" * 80);
		println("|id" + ("\t" * 2) + "|" + "nome" + ("\t" * 4))

		usuarios.each { e ->
			println("Usuários:")
			println("|" + e.getId() + ("\t" * 2) + "|" + e.getEmail() + ("\t" * 4) )

		}

		println("-" * 80);
		println("-" * 80);
	}
}
