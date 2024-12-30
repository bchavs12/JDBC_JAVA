package jdbc_example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.print.PrintException;

public class PostgresConnection {

    private static Connection instance = null;

    private static Connection getInstance() throws SQLException {

        Connection conn = null;

        try {
            String URL = "jdbc:postgresql://localhost:5432/postgres";
            String USERNAME = "postgres";
            String PASSWORD = "bchavs12";

            Class.forName("org.postgresql.Driver");

            conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            conn.setAutoCommit(false);

        } catch (ClassNotFoundException ex) {
            new Exception(ex);
        }

        return conn;
    }

    public static Connection getConexao() throws SQLException {

        if (instance == null) {
            instance = getInstance();
        }

        return instance;
    }

    public static void commit() throws SQLException {
        if (instance != null && !instance.isClosed()) {
            instance.commit();
        }
    }

    public static void rollback() throws SQLException {
        if (instance != null && !instance.isClosed()) {
            instance.rollback();
        }
    }

    public static void close() throws SQLException {

        try {
            if (instance != null && !instance.isClosed()) {
                instance.close();
            }
        } catch (SQLException ex) {
            throw ex;
        } finally {
            instance = null;
        }

    }
}
