package repository

import groovy.sql.Sql
import utils.OperationStatus

import java.sql.SQLException

class DatabaseExecute {

    private DatabaseExecute() {

    }

    static OperationStatus executeTransaction(Sql sql, String sqlStatement, List<Object> arguments) {
        OperationStatus response = OperationStatus.IN_PROGRESS

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
