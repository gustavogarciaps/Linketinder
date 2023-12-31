package view

import domain.Jobs
import domain.Skills
import repository.connection.DatabaseSingleton
import repository.SkillsDAO
import services.JobsSkillsService
import services.SkillsService
import utils.InputHelper
import utils.OperationStatus

class JobsSkillsMenu {

    private final JobsSkillsService jobsSkillsService
    private static DatabaseSingleton database = DatabaseSingleton.getInstance()

    private final SkillsDAO skillsDAO = new SkillsDAO(database.getDatabaseConnection())
    private final SkillsService skillsService = new SkillsService(skillsDAO)
    private final SkillsMenu skillsMenu = new SkillsMenu(skillsService)

    JobsSkillsMenu(JobsSkillsService jobsSkillsService) {
        this.jobsSkillsService = jobsSkillsService
    }

    void showOptions() {

        HashMap<Integer, String> menu = [
                1: "Gerenciamento de Competências",
                2: "Vincular competência à Vaga",
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
                        associateSkillWithJob()
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

    void associateSkillWithJob() {

        Jobs job = new Jobs()
        Skills skill = new Skills()

        job.setId(InputHelper.getInputStringWithDefault("id vaga").toInteger())
        skill.setId(InputHelper.getInputStringWithDefault("id competência").toInteger())

        OperationStatus status = jobsSkillsService.save(job, skill)
        println(status.getMessage())

    }
}
