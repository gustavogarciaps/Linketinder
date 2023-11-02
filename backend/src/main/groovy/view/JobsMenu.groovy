package view

import model.Company
import model.Jobs
import repository.connection.DatabaseSingleton
import repository.JobsSkillsDAO
import services.JobsService
import services.JobsSkillsService
import utils.InputHelper
import utils.OperationStatus

class JobsMenu {

    private final JobsService jobsService
    private static DatabaseSingleton database = DatabaseSingleton.getInstance()

    private final JobsSkillsDAO jobsSkillsDAO = new JobsSkillsDAO(database.getDatabaseConnection())
    private final JobsSkillsService jobsSkillsService = new JobsSkillsService(jobsSkillsDAO)
    private final JobsSkillsMenu jobsSkillsMenu = new JobsSkillsMenu(jobsSkillsService)

    JobsMenu(JobsService jobsService) {
        this.jobsService = jobsService
    }

    void showOptions() {

        HashMap<Integer, String> menu = [
                1: "Cadastrar NOVA Vaga",
                2: "Visualizar Empregos",
                3: "Visualizar Emprego pelo ID",
                4: "Excluir Emprego",
                5: "Atualizar Emprego",
                6: "Vincular Competências à Empregos",
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
                        createJob()
                        break
                    case 2:
                        loadJobs()
                        break
                    case 3:
                        loadJobById()
                        break
                    case 4:
                        deleteJobById()
                        break
                    case 5:
                        updateJobById()
                        break
                    case 6:
                        jobsSkillsMenu.showOptions()
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

    void createJob() {

        println("****** CADASTRAR NOVA VAGA ******");

        String title = InputHelper.getInputStringWithDefault("titulo");
        String description = InputHelper.getInputStringWithDefault("descrição");
        String company = InputHelper.getInputStringWithDefault("empresa (número)");
        String city = InputHelper.getInputStringWithDefault("cidade (número)");

        Jobs job = new Jobs(
                title: title, description: description, city: city, company: new Company(id: company.toInteger()))

        OperationStatus status = jobsService.save(job)
        println(status.getMessage())
    }

    void loadJobs() {

        println("Vagas Cadastradas:")
        InputHelper.printDivider(80)

        jobsService.findAll().each {
            job ->
                InputHelper.printColumns(["id", "titulo", "empresa"])
                InputHelper.printColumns([job.getId(), job.getTitle(), job.getCompany().getId()] as ArrayList<String>)

                println("Competências Requisitadas")
                InputHelper.printColumns(["id", "nome"])

                jobsService.findAll(job).getSkills().each { it ->
                    InputHelper.printColumns([it.getId(), it.getName()] as ArrayList<String>)
                }
                InputHelper.printDivider(80)
        }
    }

    void loadJobById() {

        Jobs jobSelected = new Jobs(id: InputHelper.getInputStringWithDefault("id").toInteger())
        Jobs job = jobsService.findById(jobSelected.getId())

        println("Vaga Cadastrada:")
        InputHelper.printDivider(80)

        InputHelper.printColumns(["id", "titulo", "empresa"])
        InputHelper.printColumns([job.getId(), job.getTitle(), job.getCompany().getId()] as ArrayList<String>)

        println("Competências Requisitadas")
        InputHelper.printColumns(["id", "nome"])

        jobsService.findAll(job).getSkills().each { it ->
            InputHelper.printColumns([it.getId(), it.getName()] as ArrayList<String>)
        }
        InputHelper.printDivider(80)
    }

    void deleteJobById() {
        println("Excluir Vaga")

        Jobs job = new Jobs(id: InputHelper.getInputStringWithDefault("id").toInteger())
        OperationStatus status = jobsService.deleteById(job.getId())
        println(status.getMessage())
    }

    void updateJobById() {
        println("Atualizar Vaga")

        Jobs jobSelected = new Jobs(id: InputHelper.getInputStringWithDefault("id").toInteger())

        Jobs job = jobsService.findById(jobSelected.getId())

        job.setTitle(InputHelper.getInputStringWithDefault("titulo", job.getTitle()))
        job.setDescription(InputHelper.getInputStringWithDefault("descrição", job.getDescription()))
        job.setCity(InputHelper.getInputStringWithDefault("cidade", job.getCity()))

        OperationStatus status = jobsService.updateById(job)
        println(status.getMessage())
    }
}
