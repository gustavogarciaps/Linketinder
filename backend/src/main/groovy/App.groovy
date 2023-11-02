import repository.DatabaseExecute
import repository.DatabaseSingleton
import repository.PersonDAO
import services.PersonService
import view.MainMenu

class App {

    static void main(args){

        DatabaseSingleton database = DatabaseSingleton.getInstance()
        PersonDAO personDAO = new PersonDAO(database.getDatabaseConnection())
        PersonService personService = new PersonService(personDAO)
        MainMenu mainMenu = new MainMenu(personService)
        mainMenu.showOptions()

    }
}
