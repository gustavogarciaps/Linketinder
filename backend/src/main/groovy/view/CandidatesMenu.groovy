package view

import model.Candidate
import repository.CandidateSkillsDAO
import repository.connection.DatabaseSingleton
import services.CandidateService
import services.CandidateSkillsService
import utils.DateTimeHelper
import utils.InputHelper
import utils.OperationStatus

import java.time.LocalDate

class CandidatesMenu {

    private final CandidateService candidateService
    private static DatabaseSingleton database = DatabaseSingleton.getInstance()

    private final CandidateSkillsDAO candidateSkillsDAO = new CandidateSkillsDAO(database.getDatabaseConnection())
    private final CandidateSkillsService candidateSkillsService = new CandidateSkillsService(candidateSkillsDAO)
    private final CandidateSkillsMenu candidateSkillsMenu = new CandidateSkillsMenu(candidateSkillsService)

    CandidatesMenu(CandidateService candidateService) {
        this.candidateService = candidateService
    }

    void showOptions() {

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
                        createCandidate()
                        break
                    case 2:
                        loadCandidates()
                        break
                    case 3:
                        loadCandidateById()
                        break
                    case 4:
                        deleteCandidateById()
                        break
                    case 5:
                        updateCandidateById()
                        break
                    case 6:
                        candidateSkillsMenu.showOptions()
                        break
                    case 7:
                        return
                    default:
                        break
                }

            } catch (NumberFormatException ignored) {
                println(OperationStatus.NOT_NUMBER.getMessage())
            }
        }
    }

    void createCandidate() {

        println("****** CADASTRAR NOVO CANDIDATO ******");

        String id = InputHelper.getInputStringWithDefault("id")
        String name = InputHelper.getInputStringWithDefault("nome");
        String description = InputHelper.getInputStringWithDefault("descrição");
        String city = InputHelper.getInputStringWithDefault("cidade (número)");
        String academicEducation = InputHelper.getInputStringWithDefault("Formação Academica");
        String linkedin = InputHelper.getInputStringWithDefault("linkedin");
        LocalDate dateOfBirth = DateTimeHelper.getInputDateWithDefault("data de aniversário (dd/mm/aaaa)");
        String cpf = InputHelper.getInputStringWithDefault("cpf");

        Candidate candidate = new Candidate(
                id: id.toInteger(), name: name, description: description, city: city, academicEducation: academicEducation, linkedin: linkedin, dateOfBirth: dateOfBirth, cpf: cpf)

        OperationStatus status = candidateService.save(candidate)
        println(status.getMessage())

    }

    void loadCandidates() {

        println("Candidatos Cadastrados:")
        InputHelper.printDivider(80)

        ArrayList<String> columns = ["id", "formacao"]
        InputHelper.printColumns(columns)

        candidateService.findAll().forEach { it ->

            Candidate candidate = candidateService.findAll(it)
            InputHelper.printColumns([candidate.getId(), candidate.getAcademicEducation()] as ArrayList<String>)

            println("Competências do candidato")
            candidate.getSkills().each { skill ->
                InputHelper.printColumns([skill.getId(), skill.getName()] as ArrayList<String>)
            }
            InputHelper.printDivider(80)
        }
        InputHelper.printDivider(80)
    }

    void loadCandidateById() {

        println("Candidato:")
        Candidate candidateSelected = candidateService.findById(InputHelper.getInputStringWithDefault("id").toInteger())

        InputHelper.printDivider(80)

        ArrayList<String> columns = ["id", "nome", "linkedin"]
        InputHelper.printColumns(columns)

        Candidate candidate = candidateService.findAll(candidateSelected)

        InputHelper.printColumns([candidate.getId(), candidate.getName(), candidate.getLinkedin()] as ArrayList<String>)

        println("Competências do candidato")
        candidate.getSkills().each { skill ->
            InputHelper.printColumns([skill.getId(), skill.getName()] as ArrayList<String>)
        }
        InputHelper.printDivider(80)
    }

    void deleteCandidateById() {
        println("Excluir Candidato")

        Candidate candidate = new Candidate(id: InputHelper.getInputStringWithDefault("id").toInteger())
        OperationStatus status = candidateService.deleteById(candidate.getId())
        println(status.getMessage())
    }

    void updateCandidateById() {
        println("Atualizar Candidato")

        Candidate candidateSelected = new Candidate(id: InputHelper.getInputStringWithDefault("id").toInteger())
        Candidate candidate = candidateService.findById(candidateSelected.getId())

        candidate.setName(InputHelper.getInputStringWithDefault("nome", candidate.getName()))
        candidate.setDescription(InputHelper.getInputStringWithDefault("descrição:", candidate.getDescription()))
        candidate.setCity(InputHelper.getInputStringWithDefault("cidade:", candidate.getCity()))

        OperationStatus status = candidateService.updateById(candidate)
        println(status.getMessage())
    }
}
