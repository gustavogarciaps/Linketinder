package view

import entities.JobsDTO
import entities.SkillsDTO
import persistencies.ConnectionFactory
import persistencies.JobsSkillsDAO
import utils.InputHelper

class JobsSkillsMenu {

    static void showOptions() {

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
                        SkillsMenu.showOptions()
                        break
                    case 2:
                        associateSkillWithJob(new JobsSkillsDAO(sql: ConnectionFactory.newInstance()))
                        break
                    case 3:
                        return
                        break
                    default:
                        break
                }
            } catch (NumberFormatException e) {
                println("\nDigite apenas o número das opções informadas no menu.\n")
            }
        }
    }

    static void associateSkillWithJob(JobsSkillsDAO jobSkillsDAO){

        JobsDTO job = new JobsDTO()
        SkillsDTO skill = new SkillsDTO()

        job.setId(InputHelper.getInputStringWithDefault("id vaga").toInteger())
        skill.setId(InputHelper.getInputStringWithDefault("id competência").toInteger())

        try{
            jobSkillsDAO.save(job, skill)
        }catch(Exception e){
            println(e.getMessage())
        }
    }
}
