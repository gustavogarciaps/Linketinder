package view

import entities.CompanyDTO
import entities.JobsDTO
import exceptions.QuitException
import persistencies.ConnectionFactory
import persistencies.JobsDAO
import utils.InputHelper

class JobsMenu {

    static void showOptions() {

        HashMap<Integer, String> menu = [
                1: "Cadastrar NOVO Emprego",
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

            String userInput = InputHelper.getInputString("Opção (ou 'q' para sair)")

            try {

                Integer choice = userInput.toInteger()

                switch (choice) {
                    case 1:
                        createJob(new JobsDAO(sql: ConnectionFactory.newInstance()))
                        break
                    case 2:
                        loadJobs(new JobsDAO(sql: ConnectionFactory.newInstance()))
                        break
                    case 3:
                        loadJobById(new JobsDAO(sql: ConnectionFactory.newInstance()))
                        break
                    case 4:
                        deleteJobById(new JobsDAO(sql: ConnectionFactory.newInstance()))
                        break
                    case 5:
                        updateJobById(new JobsDAO(sql: ConnectionFactory.newInstance()))
                        break
                    case 6:
                        JobsSkillsMenu.showOptions()
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

    static void createJob(JobsDAO jobsDAO) {

        println("****** CADASTRAR NOVA VAGA ******");
        try {
            String title = InputHelper.getInputString("titulo");
            String description = InputHelper.getInputString("descrição");
            String company = InputHelper.getInputString("empresa (número)");
            String city = InputHelper.getInputString("cidade");

            JobsDTO job = new JobsDTO(
                    title: title, description: description, city: city, company: new CompanyDTO(id: company.toInteger()))

            jobsDAO.save(job) ? println("Vaga registrada com sucesso") : println("Falha ao registrar vaga")

        } catch (Exception e) {
            e.getMessage()
        }
    }

    static void loadJobs(JobsDAO jobsDAO) {

        println("Vagas Cadastradas:")
        InputHelper.divider(80)

        jobsDAO.findAll().each {
            job ->
                InputHelper.creatingTable(["id", "titulo", "empresa"])
                InputHelper.creatingTable([job.getId(), job.getTitle(), job.getCompany().getId()])

                println("Competências Requisitadas")
                InputHelper.creatingTable(["id", "nome"])

                jobsDAO.findAll(job).getSkills().each { it ->
                    InputHelper.creatingTable([it.getId(), it.getName()])
                }
                InputHelper.divider(80)
        }
    }

    static void loadJobById(JobsDAO jobsDAO) {

        String id = InputHelper.getInputString("id")
        JobsDTO job = jobsDAO.findById(id.toInteger())

        println("Vaga Cadastrada:")
        InputHelper.divider(80)

        InputHelper.creatingTable(["id", "titulo", "empresa"])
        InputHelper.creatingTable([job.getId(), job.getTitle(), job.getCompany().getId()])

        println("Competências Requisitadas")
        InputHelper.creatingTable(["id", "nome"])

        jobsDAO.findAll(job).getSkills().each { it ->
            InputHelper.creatingTable([it.getId(), it.getName()])
        }
        InputHelper.divider(80)
    }

    static void deleteJobById(JobsDAO jobsDAO) {
        println("Excluir Vaga")

        try {
            String id = InputHelper.getInputString("id")
            jobsDAO.deleteById(id.toInteger()) ? println("Excluído com sucesso. Código ${id}") : println("Falha ao Excluir código ${id}")

        } catch (Exception e) {
            e.getMessage()
        }
    }

    static void updateJobById(JobsDAO jobsDAO) {
        println("Atualizar Vaga")

        try {
            String id = InputHelper.getInputString("id")
            JobsDTO job = jobsDAO.findById(id.toInteger())

            job.setTitle(InputHelper.getInputString("titulo", job.getTitle()))
            job.setDescription(InputHelper.getInputString("descrição", job.getDescription()))
            job.setCity(InputHelper.getInputString("cidade", job.getCity()))

            jobsDAO.updateById(job) ? println("Atualizado com sucesso. Código ${id}") : println("Falha ao atualizar o código ${id}")

        } catch (Exception e) {
            e.getMessage()
        }

    }
}
