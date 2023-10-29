package view

import entities.Candidate
import DAO.CandidateDAO
import DAO.ConnectionFactory
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

            String userInput = InputHelper.getInputStringWithDefault("Opção (ou 'q' para sair)")

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
                        CandidateSkillsMenu.showOptions()
                        break
                    case 7:
                        return
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
            String id = InputHelper.getInputStringWithDefault("id")
            String name = InputHelper.getInputStringWithDefault("nome");
            String description = InputHelper.getInputStringWithDefault("descrição");
            String city = InputHelper.getInputStringWithDefault("cidade (número)");
            String linkedin = InputHelper.getInputStringWithDefault("linkedin");
            LocalDate dateOfBirth = DateTimeHelper.getInputDateWithDefault("data de aniversário (dd/mm/aaaa)");
            String cpf = InputHelper.getInputStringWithDefault("cpf");

            Candidate candidateDTO = new Candidate(
                    id: id.toInteger(), name: name, description: description, city: city, linkedin: linkedin, dateOfBirth: dateOfBirth, cpf: cpf)

            candidateDAO.save(candidateDTO) ? println("Candidato registrado com sucesso") : println("Falha ao registrar candidato")

        } catch (Exception e) {
            e.getMessage()
        }
    }

    static void loadCandidates(CandidateDAO candidateDAO) {

        println("Candidatos Cadastrados:")
        InputHelper.printDivider(80)

        def columns = ["id", "nome", "linkedin"]
        InputHelper.printColumns(columns)

        candidateDAO.findAll().forEach { it ->

            Candidate candidateDTO = candidateDAO.findAll(it)
            InputHelper.printColumns([candidateDTO.getId(), candidateDTO.getName(), candidateDTO.getLinkedin()])

            println("Competências do candidato")
            candidateDTO.getSkills().each {skillDTO ->
                InputHelper.printColumns([skillDTO.getId(), skillDTO.getName()])
            }
            InputHelper.printDivider(80)
        }
        InputHelper.printDivider(80)
    }

    static void loadCandidateById(CandidateDAO candidateDAO) {

        println("Candidato:")
        String id = InputHelper.getInputStringWithDefault("id")

        InputHelper.printDivider(80)

        def columns = ["id", "nome", "linkedin"]
        InputHelper.printColumns(columns)

        Candidate candidateDTO = candidateDAO.findById(id.toInteger())
        candidateDTO = candidateDAO.findAll(candidateDTO)

        InputHelper.printColumns([candidateDTO.getId(), candidateDTO.getName(), candidateDTO.getLinkedin()])
        println("Competências do candidato")
        candidateDTO.getSkills().each {skillDTO ->
            InputHelper.printColumns([skillDTO.getId(), skillDTO.getName()])
        }
        InputHelper.printDivider(80)
    }

    static void deleteCandidateById(CandidateDAO candidateDAO) {
        println("Excluir Candidato")

        try {
            String id = InputHelper.getInputStringWithDefault("id")
            candidateDAO.deleteById(id.toInteger()) ? println("Excluído com sucesso. Código ${id}") : println("Falha ao Excluir código ${id}")
        } catch (Exception e) {
            e.getMessage()
        }
    }

    static void updateCandidateById(CandidateDAO candidateDAO) {
        println("Atualizar Candidato")

        try {
            String id = InputHelper.getInputStringWithDefault("id")
            Candidate candidateDTO = candidateDAO.findById(id.toInteger())

            candidateDTO.setName(InputHelper.getInputStringWithDefault("nome", candidateDTO.getName()))
            candidateDTO.setDescription(InputHelper.getInputStringWithDefault("descrição:", candidateDTO.getDescription()))
            candidateDTO.setCity(InputHelper.getInputStringWithDefault("cidade:", candidateDTO.getCity()))

            candidateDAO.updateById(candidateDTO) ? println("Atualizado com sucesso. Código ${id}") : println("Falha ao atualizar o código ${id}")

        } catch (Exception e) {
            e.getMessage()
        }
    }
}
