package cybersoft.java18.crm.respository;

import cybersoft.java18.crm.exception.DatabaseNotFoundException;
import cybersoft.java18.crm.jdbc.MySqlConnection;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public abstract class AbstractRepository<T> {
    protected List<T> executeQuery(JdbcExecute<List<T>> processor) {
        try (Connection connection = MySqlConnection.getConnection()) {
            //lamda function
            return processor.processQuery(connection);
        } catch (SQLException e) {
            throw new DatabaseNotFoundException(e.getMessage());
        }
    }
    protected T executeQuery2(JdbcExecute<T> processor) {
        try (Connection connection = MySqlConnection.getConnection()) {
            //lamda function
            return processor.processQuery(connection);
        } catch (SQLException e) {
            throw new DatabaseNotFoundException(e.getMessage());
        }
    }
    protected Integer executeSaveAndUpdate(JdbcExecute<Integer> processor) {
        try (Connection connection = MySqlConnection.getConnection()) {
            //lamda function
            return processor.processQuery(connection);
        } catch (SQLException e) {
            throw new DatabaseNotFoundException(e.getMessage());
        }
    }
}
