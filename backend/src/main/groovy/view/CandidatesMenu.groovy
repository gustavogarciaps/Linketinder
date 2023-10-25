package view

import entities.CandidateDTO
import exceptions.QuitException
import persistencies.CandidateDAO
import persistencies.ConnectionFactory
import utils.DateTimeHelper
import utils.InputHelper

import java.time.LocalDate

class CandidatesMenu {

    static void showOptions() {

        HashMap<Integer, String> menu = [
                1: "Cadastrar NOVO Candidato",
                2: "Visualizar Candidatos",
                3: "Visualizar Candidato pelo ID",
                4: "Excluir Candidato",
                5: "Atualizar Candidato",
                6: "Vincular Competências à Candidatos",
                7: "Voltar ao menu anterior"
        ]

        while (true) {

            println("****** MENU DE OPÇÕES ******")

            menu.eachWithIndex { key, value, index ->
                println("[$key] - $value")
            }

            String userInput = InputHelper.getInputString("Opção (ou 'q' para sair)")

            try {

                Integer choice = userInput.toInteger()

                switch (choice) {
                    case 1:
                        createCandidate(new CandidateDAO(sql: ConnectionFactory.newInstance()))
                        break
                    case 2:
                        loadCandidates(new CandidateDAO(sql: ConnectionFactory.newInstance()))
                        break
                    case 3:
                        loadCandidateById(new CandidateDAO(sql: ConnectionFactory.newInstance()))
                        break
                    case 4:
                        deleteCandidateById(new CandidateDAO(sql: ConnectionFactory.newInstance()))
                        break
                    case 5:
                        updateCandidateById(new CandidateDAO(sql: ConnectionFactory.newInstance()))
                        break
                    case 6:
                        SkillsMenu.showOptions()
                        break
                    default:
                        break
                }

            } catch (NumberFormatException e) {
                println("\nDigite apenas o número das opções informadas no menu.\n")
            }
        }
    }

    static void createCandidate(CandidateDAO candidateDAO) {

        println("****** CADASTRAR NOVO CANDIDATO ******");
        try {
            String id = InputHelper.getInputString("id")
            String name = InputHelper.getInputString("nome");
            String description = InputHelper.getInputString("descrição");
            String city = InputHelper.getInputString("cidade (número)");
            String linkedin = InputHelper.getInputString("linkedin");
            LocalDate dateOfBirth = DateTimeHelper.getInputDate("data de aniversário (dd/mm/aaaa)");
            String cpf = InputHelper.getInputString("cpf");

            CandidateDTO candidateDTO = new CandidateDTO(
                    id: id.toInteger(), name: name, description: description, city: city, linkedin: linkedin, dateOfBirth: dateOfBirth, cpf: cpf)

            candidateDAO.save(candidateDTO) ? println("Candidato registrado com sucesso") : println("Falha ao registrar candidato")

        } catch (QuitException e) {
            e.getMessage()
        }
    }

    static void loadCandidates(CandidateDAO candidateDAO) {

        println("Candidatos Cadastrados:")
        InputHelper.divider(80)

        def columns = ["id", "nome", "linkedin"]
        InputHelper.creatingTable(columns)

        candidateDAO.findAll().forEach { it ->
            InputHelper.creatingTable([it.getId(), it.getName(), it.getLinkedin()])
        }

        InputHelper.divider(80)
    }

    static void loadCandidateById(CandidateDAO candidateDAO) {

        println("Candidato:")
        String id = InputHelper.getInputString("id")

        InputHelper.divider(80)

        def columns = ["id", "nome", "linkedin"]
        InputHelper.creatingTable(columns)

        CandidateDTO candidateDTO = candidateDAO.findById(id.toInteger())
        InputHelper.creatingTable([candidateDTO.getId(), candidateDTO.getName(), candidateDTO.getLinkedin()])

        InputHelper.divider(80)
    }

    static void deleteCandidateById(CandidateDAO candidateDAO) {
        println("Excluir Candidato")

        try {
            String id = InputHelper.getInputString("id")
            candidateDAO.deleteById(id.toInteger()) ? println("Excluído com sucesso. Código ${id}") : println("Falha ao Excluir código ${id}")

        } catch (QuitException e) {
            e.getMessage()
        }

    }

    static void updateCandidateById(CandidateDAO candidateDAO) {
        println("Atualizar Candidato")

        try {
            String id = InputHelper.getInputString("id")
            CandidateDTO candidateDTO = candidateDAO.findById(id.toInteger())

            candidateDTO.setName(InputHelper.getInputString("nome",candidateDTO.getName()))
            candidateDTO.setDescription(InputHelper.getInputString("descrição:",candidateDTO.getDescription()))
            candidateDTO.setCity(InputHelper.getInputString("cidade:",candidateDTO.getCity()))

            candidateDAO.updateById(candidateDTO) ? println("Atualizado com sucesso. Código ${id}") : println("Falha ao atualizar o código ${id}")

        } catch (QuitException e) {
            e.getMessage()
        }

    }
}
