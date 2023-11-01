package view

import model.Skills
import exceptions.QuitException
import repository.DatabaseConfig
import repository.SkillsDAO
import utils.InputHelper

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
                String userInput = InputHelper.getInputStringWithDefault("Opção")

                Integer choice = userInput.toInteger()

                switch (choice) {
                    case 1:
                        createSkill(new SkillsDAO(sql: DatabaseConfig.newInstance()))
                        break
                    case 2:
                        loadSkills(new SkillsDAO(sql: DatabaseConfig.newInstance()))
                        break
                    case 3:
                        loadSkillById(new SkillsDAO(sql: DatabaseConfig.newInstance()))
                        break
                    case 4:
                        updateSkillById(new SkillsDAO(sql: DatabaseConfig.newInstance()))
                        break
                    case 5:
                        deleteSkillById(new SkillsDAO(sql: DatabaseConfig.newInstance()))
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
            String name = InputHelper.getInputStringWithDefault("nome");
            Skills skillsDTO = new Skills(name: name)

            skillsDAO.save(skillsDTO) ? println("Competência registrada com sucesso") : println("Falha ao registrar competência")
        } catch (Exception e) {
            e.getMessage()
        }
    }

    static void loadSkills(SkillsDAO skillsDAO) {

        println("Competências Cadastradas:")
        InputHelper.printDivider(80)

        def columns = ["id", "nome"]
        InputHelper.printColumns(columns)

        skillsDAO.findAll().forEach { it ->
            InputHelper.printColumns([it.getId().toString(), it.getName()])
        }

        InputHelper.printDivider(80)
    }

    static void loadSkillById(SkillsDAO skillsDAO) {

        println("Competência")
        String id = InputHelper.getInputStringWithDefault("id")

        InputHelper.printDivider(80)

        def columns = ["id", "nome"]
        InputHelper.printColumns(columns)

        Skills skillsDTO = skillsDAO.findById(id.toInteger())
        InputHelper.printColumns([skillsDTO.getId().toString(), skillsDTO.getName()])

        InputHelper.printDivider(80)
    }

    static void deleteSkillById(SkillsDAO skillsDAO) {
        println("Excluir Competência")

        try {
            String id = InputHelper.getInputStringWithDefault("id")
            skillsDAO.deleteById(id.toInteger()) ? println("Excluído com sucesso. Código ${id}") : println("Falha ao Excluir código ${id}")

        } catch (Exception e) {
            e.getMessage()
        }

    }

    static void updateSkillById(SkillsDAO skillsDAO) {
        println("Atualizar Competência")

        try {
            String id = InputHelper.getInputStringWithDefault("id")
            Skills skillsDTO = skillsDAO.findById(id.toInteger())

            skillsDTO.setName(InputHelper.getInputStringWithDefault("nome", skillsDTO.getName()))

            skillsDAO.updateById(skillsDTO) ? println("Atualizado com sucesso. Código ${id}") : println("Falha ao atualizar o código ${id}")

        } catch (Exception e) {
            e.getMessage()
        }

    }
}
