package br.com.linketinder.services

import br.com.linketinder.database.CandidatoCompetenciaDAO

class FabricaCandidatosCompetencias {

    static String criar(Integer candidatos_id, ArrayList<Integer> competencias) {

        try {

            competencias.each { competencia ->
                CandidatoCompetenciaDAO.create(candidatos_id, competencia)
            }
            return "Concluído";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro na requisição: " + e.getMessage();
        }
    }
}