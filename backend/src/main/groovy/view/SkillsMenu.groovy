package view

import entities.SkillsDTO
import exceptions.QuitException
import persistencies.ConnectionFactory
import persistencies.SkillsDAO
import utils.InputHelper

import java.time.LocalDate

class SkillsMenu {

    static void showOptions() {

        HashMap<Integer, String> menu = [
                1: "Criar Competência",
                2: "Visualizar Competências",
                3: "Visualizar Skill por ID",
                4: "Atualizar Competência",
                5: "Excluir Competência",
                6: "Voltar ao menu anterior"
        ]

        while (true) {

            println("****** MENU DE OPÇÕES ******")

            menu.eachWithIndex { key, value, index ->
                println("[$key] - $value")
            }

            try {
                String userInput = InputHelper.getInputString("Opção")

                Integer choice = userInput.toInteger()

                switch (choice) {
                    case 1:
                        createSkill(new SkillsDAO(sql: ConnectionFactory.newInstance()))
                        break
                    case 2:
                        loadSkills(new SkillsDAO(sql: ConnectionFactory.newInstance()))
                        break
                    case 3:
                        loadSkillById(new SkillsDAO(sql: ConnectionFactory.newInstance()))
                        break
                    case 4:
                        updateSkillById(new SkillsDAO(sql: ConnectionFactory.newInstance()))
                        break
                    case 5:
                        deleteSkillById(new SkillsDAO(sql: ConnectionFactory.newInstance()))
                        break
                    case 6:
                        return
                    default:
                        break
                }

            } catch (NumberFormatException e) {
                println("\nDigite apenas o número das opções informadas no menu.\n")
            } catch (QuitException e) {
                e.getMessage()
            }
        }

    }

    static void createSkill(SkillsDAO skillsDAO) {

        println("****** CADASTRAR NOVA COMPETÊNCIA ******");
        try {

            String name = InputHelper.getInputString("nome");

            SkillsDTO skillsDTO = new SkillsDTO(name: name)

            skillsDAO.save(skillsDTO) ? println("Competência registrada com sucesso") : println("Falha ao registrar competência")

        } catch (QuitException e) {
            e.getMessage()
        }
    }

    static void loadSkills(SkillsDAO skillsDAO) {

        println("Competências Cadastradas:")
        InputHelper.divider(80)

        def columns = ["id", "nome"]
        InputHelper.creatingTable(columns)

        skillsDAO.findAll().forEach { it ->
            InputHelper.creatingTable([it.getId().toString(), it.getName()])
        }

        InputHelper.divider(80)
    }

    static void loadSkillById(SkillsDAO skillsDAO) {

        println("Competência")
        String id = InputHelper.getInputString("id")

        InputHelper.divider(80)

        def columns = ["id", "nome"]
        InputHelper.creatingTable(columns)

        SkillsDTO skillsDTO = skillsDAO.findById(id.toInteger())
        InputHelper.creatingTable([skillsDTO.getId().toString(), skillsDTO.getName()])

        InputHelper.divider(80)
    }

    static void deleteSkillById(SkillsDAO skillsDAO) {
        println("Excluir Competência")

        try {
            String id = InputHelper.getInputString("id")
            skillsDAO.deleteById(id.toInteger()) ? println("Excluído com sucesso. Código ${id}") : println("Falha ao Excluir código ${id}")

        } catch (QuitException e) {
            e.getMessage()
        }

    }

    static void updateSkillById(SkillsDAO skillsDAO) {
        println("Atualizar Competência")

        try {
            String id = InputHelper.getInputString("id")
            SkillsDTO skillsDTO = skillsDAO.findById(id.toInteger())

            skillsDTO.setName(InputHelper.getInputString("nome", skillsDTO.getName()))

            skillsDAO.updateById(skillsDTO) ? println("Atualizado com sucesso. Código ${id}") : println("Falha ao atualizar o código ${id}")

        } catch (QuitException e) {
            e.getMessage()
        }

    }
}
