package repository

import utils.OperationStatus

import java.sql.SQLException

interface InterfaceDatabaseDML<T> {

    List<T> findAll() throws SQLException

    T findAll(T entity) throws SQLException

    T findById(int id) throws SQLException

    OperationStatus save(T entity)

    OperationStatus deleteById(int id)

    OperationStatus updateById(T entity)
}
