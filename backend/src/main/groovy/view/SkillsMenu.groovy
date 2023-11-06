package view


import domain.Skills
import exceptions.QuitException
import repository.SkillsDAO
import services.SkillsService
import utils.InputHelper
import utils.OperationStatus

class SkillsMenu {

    private final SkillsService skillsService

    SkillsMenu(SkillsService skillsService) {
        this.skillsService = skillsService
    }

    void showOptions() {

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
                        createSkill()
                        break
                    case 2:
                        loadSkills()
                        break
                    case 3:
                        loadSkillById()
                        break
                    case 4:
                        updateSkillById()
                        break
                    case 5:
                        deleteSkillById()
                        break
                    case 6:
                        return
                    default:
                        break
                }

            } catch (NumberFormatException ignored) {
                println(OperationStatus.NOT_NUMBER.getMessage())
            } catch (QuitException e) {
                e.getMessage()
            }
        }

    }

    void createSkill() {

        println("****** CADASTRAR NOVA COMPETÊNCIA ******");

        String name = InputHelper.getInputStringWithDefault("nome");
        Skills skill = new Skills(name: name)
        OperationStatus status = skillsService.save(skill)
        println(status.getMessage())

    }

    void loadSkills() {

        println("Competências Cadastradas:")
        InputHelper.printDivider(80)

        ArrayList<String> columns = ["id", "nome"]
        InputHelper.printColumns(columns)

        skillsService.findAll().forEach { skill ->
            InputHelper.printColumns([skill.getId().toString(), skill.getName()])
        }

        InputHelper.printDivider(80)
    }

    void loadSkillById() {

        println("Competência")
        String id = InputHelper.getInputStringWithDefault("id")

        InputHelper.printDivider(80)

        ArrayList<String> columns = ["id", "nome"]
        InputHelper.printColumns(columns)

        Skills skill = skillsService.findById(id.toInteger())
        InputHelper.printColumns([skill.getId().toString(), skill.getName()])

        InputHelper.printDivider(80)
    }

    void deleteSkillById() {
        println("Excluir Competência")

        Skills skill = new Skills(id: InputHelper.getInputStringWithDefault("id").toInteger())
        OperationStatus result = skillsService.deleteById(skill.getId())
        println(result.getMessage())
    }

    void updateSkillById(SkillsDAO skillsDAO) {
        println("Atualizar Competência")

        Skills skillSelected = new Skills(id: InputHelper.getInputStringWithDefault("id").toInteger())
        Skills skill = skillsService.findById(skillSelected.getId())

        skill.setName(InputHelper.getInputStringWithDefault("nome", skill.getName()))

        OperationStatus result = skillsService.updateById(skill)
        println(result.getMessage())
    }
}


