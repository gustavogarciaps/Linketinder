package repository.connection

import groovy.sql.Sql
import groovy.transform.Canonical

@Canonical
class DatabaseSingleton {

    static private DatabaseSingleton instance
    static private Sql databaseConnection

    private DatabaseSingleton() {
        databaseConnection = DatabasePostgresConfig.newInstance()
    }

    static synchronized DatabaseSingleton getInstance() {
        if (instance == null) {
            instance = new DatabaseSingleton()
        }
        return instance
    }

    static Sql getDatabaseConnection() {
        return databaseConnection
    }
}
