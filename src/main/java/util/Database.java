package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    public Connection conn;

    /**
     * Clasa singleton care va avea un constructor privat ce va asigna veriabilei membru conexiunea la baza de date
     *  Oracle.
     * @throws SQLException
     */
    private Database() throws SQLException {
        conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "dbaJava", "sql");
    }

    /**
     * Fiind o clasa de tip singleton, am creat aceasta metoda care returneaza o instanta a clasei Database
     * @return instanta a clasei Database
     * @throws SQLException
     */
    public static Database getConnection() throws SQLException {
        return new Database();
    }
}
