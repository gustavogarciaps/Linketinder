package repository

import groovy.sql.Sql
import org.junit.jupiter.api.Test

import static org.junit.jupiter.api.Assertions.assertEquals

class ConnectionTest {

    @Test
    void testDatabaseConnection() {

        DatabaseSingleton database = DatabaseSingleton.getInstance()
        Sql sql = database.getDatabaseConnection()

        def result = sql.firstRow("SELECT 1 as value")

        assertEquals(result.value, 1)
    }
}


