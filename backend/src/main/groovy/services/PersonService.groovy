package services

import model.Person
import repository.PersonDAO
import utils.OperationStatus

class PersonService {

    private final PersonDAO personDAO

    PersonService(PersonDAO personDAO) {
        this.personDAO = personDAO
    }

    List<Person> findAll() {
        List<Person> result = personDAO.findAll()
        return result
    }

    Person findAll(Person person) {
        Person result = personDAO.findAll(person)
        return result
    }

    Person findById(int id) {
        Person result = personDAO.findById(id)
        return result
    }

    OperationStatus save(Person person) {
        return personDAO.save(person);
    }

    OperationStatus updateById(Person person){
        return personDAO.updateById(person)
    }

    OperationStatus deleteById(int id){
        return personDAO.deleteById(id)
    }
}
