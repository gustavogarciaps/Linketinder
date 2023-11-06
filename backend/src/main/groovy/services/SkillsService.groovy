package services

import domain.Skills
import repository.SkillsDAO
import utils.OperationStatus

class SkillsService {

    private final SkillsDAO skillsDAO

    SkillsService(SkillsDAO skillsDAO) {
        this.skillsDAO = skillsDAO
    }

    List<Skills> findAll() {
        List<Skills> result = skillsDAO.findAll()
        return result
    }

    Skills findAll(Skills skill) {
        Skills result = skillsDAO.findAll(skill)
        return result
    }

    Skills findById(int id) {
        Skills result = skillsDAO.findById(id)
        return result
    }

    OperationStatus save(Skills skill) {
        return skillsDAO.save(skill);
    }

    OperationStatus updateById(Skills skill){
        return skillsDAO.updateById(skill)
    }

    OperationStatus deleteById(int id){
        return skillsDAO.deleteById(id)
    }
}
