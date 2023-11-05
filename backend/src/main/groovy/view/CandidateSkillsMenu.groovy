package view

import model.Candidate
import model.Skills
import repository.connection.DatabaseSingleton
import repository.SkillsDAO
import services.CandidateSkillsService
import services.SkillsService
import utils.InputHelper
import utils.OperationStatus

class CandidateSkillsMenu {

    private final CandidateSkillsService candidateSkillsService
    private static DatabaseSingleton database = DatabaseSingleton.getInstance()

    private final SkillsDAO skillsDAO = new SkillsDAO(database.getDatabaseConnection())
    private final SkillsService skillsService = new SkillsService(skillsDAO)
    private final SkillsMenu skillsMenu = new SkillsMenu(skillsService)

    CandidateSkillsMenu(CandidateSkillsService candidateSkillsService) {
        this.candidateSkillsService = candidateSkillsService
    }

    void showOptions() {

        HashMap<Integer, String> menu = [
                1: "Gerenciamento de Competências",
                2: "Vincular competência ao candidato",
                3: "Voltar ao menu anterior"
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
                        skillsMenu.showOptions()
                        break
                    case 2:
                        associateSkillWithCandidate()
                        break
                    case 3:
                        return
                        break
                    default:
                        break
                }
            } catch (NumberFormatException ignored) {
                println(OperationStatus.NOT_NUMBER.getMessage())
            }
        }
    }

    void associateSkillWithCandidate(){

        Candidate candidate = new Candidate()
        Skills skill = new Skills()

        candidate.setId(InputHelper.getInputStringWithDefault("id candidato").toInteger())
        skill.setId(InputHelper.getInputStringWithDefault("id competência").toInteger())

        OperationStatus status = candidateSkillsService.save(candidate, skill)
        println(status.getMessage())

    }
}
