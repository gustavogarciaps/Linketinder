package view

import entities.Candidate
import entities.Skills
import DAO.CandidateSkillsDAO
import DAO.ConnectionFactory
import utils.InputHelper

class CandidateSkillsMenu {

    static void showOptions() {

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
                        SkillsMenu.showOptions()
                        break
                    case 2:
                        associateSkillWithCandidate(new CandidateSkillsDAO(sql: ConnectionFactory.newInstance()))
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

    static void associateSkillWithCandidate(CandidateSkillsDAO candidateSkillsDAO){

        Candidate candidateDTO = new Candidate()
        Skills skillsDTO = new Skills()

        candidateDTO.setId(InputHelper.getInputStringWithDefault("id candidato").toInteger())
        skillsDTO.setId(InputHelper.getInputStringWithDefault("id competência").toInteger())

        try{
            candidateSkillsDAO.save(candidateDTO, skillsDTO)
        }catch(Exception e){
            println(e.getMessage())
        }
    }
}
