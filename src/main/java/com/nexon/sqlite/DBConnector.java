package com.nexon.sqlite;

import org.sqlite.SQLiteConfig;

import java.sql.*;

/**
 * Created by chan8 on 2017-01-23.
 */
public class DBConnector {

    private Connection connection;
    private String dbFileName;
    private boolean isOpened = false;

    public DBConnector(String dbFileName) {
        this.dbFileName = dbFileName;
    }

    public void initialize() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean connectDB() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        SQLiteConfig config = new SQLiteConfig();

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:/" + dbFileName, config.toProperties());
        } catch (SQLException e) {
            e.printStackTrace();
            isOpened = false;
            return false;
        }
        isOpened = true;
        return true;
    }

    public boolean closeDB() {
        if (!isOpened) {
            return true;
        }

        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void createTable() {
        try {
            Statement stmt = connection.createStatement();
            String sql = "CREATE TABLE TEAM " +
                    "(NAME CHAR(20) NOT NULL, " +
                    "AGE int NOT NULL, " +
                    "ID CHAR(20) NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insert() {
        try {
            Statement stmt = connection.createStatement();
            String sql = "INSERT INTO TEAM (NAME, AGE, ID) " +
                    "VALUES('KANG WON', '28', 'kangwon@nexon.co.kr');";
            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUsingPreparedStatement() {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO TEAM(NAME, AGE, ID) VALUES(?, ?, ?)");
            ps.setString(1, "JU HWI");
            ps.setString(2, "26");
            ps.setString(3, "KJS4883");
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertUsingPreparedStatement(String name, int age, String id) {
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO TEAM(NAME, AGE, ID) VALUES(?, ?, ?)");
            ps.setString(1, name);
            ps.setString(2, String.valueOf(age));
            ps.setString(3, id);
            ps.executeUpdate();
            ps.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        String sql = "DELETE FROM TEAM;";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable() {
        String sql = "DROP TABLE TEAM;";
        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectAll() {
        String sql = "SELECT * FROM TEAM;";
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString("NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
