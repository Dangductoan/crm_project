package cybersoft.java18.crm.respository;

import java.sql.Connection;
import java.sql.SQLException;

@FunctionalInterface
public interface JdbcExecute<T> {
    T processQuery(Connection connection) throws SQLException;
}
