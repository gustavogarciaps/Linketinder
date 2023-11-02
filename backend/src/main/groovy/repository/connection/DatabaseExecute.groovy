package repository.connection

import groovy.sql.Sql
import utils.OperationStatus

import java.sql.SQLException

class DatabaseExecute {

    private static OperationStatus response

    private DatabaseExecute() {

    }

    static OperationStatus executeTransaction(Sql sql, String sqlStatement, List<Object> arguments) {

        sql.withTransaction { status ->
            try {
                sql.execute(sqlStatement, arguments)
                status.commit()
                response = OperationStatus.SUCCESS
            } catch (SQLException e) {
                println(e.getMessage())
                status.rollback()
                response = OperationStatus.FAILURE
            }
        }

        return response
    }
}
