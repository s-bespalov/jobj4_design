package ru.job4j.jdbc;

import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private final Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        try {
            initConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initConnection() throws SQLException, ClassNotFoundException {
        Class.forName(properties.getProperty("driver_class"));
        connection = DriverManager.getConnection(
                properties.getProperty("url"),
                properties.getProperty("username"),
                properties.getProperty("password"));
    }

    public void createTable(String tableName) throws SQLException {
        var sql = String.format("CREATE TABLE IF NOT EXISTS %s();", tableName);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    public void dropTable(String tableName) throws SQLException {
        var sql = String.format("DROP TABLE %s;", tableName);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws SQLException {
        var sql = String.format("ALTER TABLE %s ADD COLUMN %s %s;", tableName, columnName, type);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    public void dropColumn(String tableName, String columnName) throws SQLException {
        var sql = String.format("ALTER TABLE %s DROP COLUMN %s;", tableName, columnName);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) throws SQLException {
        var sql = String.format("ALTER TABLE %s RENAME COLUMN %s TO %s;", tableName, columnName, newColumnName);
        try (var statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        }
    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        var config = new Properties();
        try (var in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        try (var tableEditor = new TableEditor(config)) {
            tableEditor.createTable("cities");
            System.out.println(tableEditor.getTableScheme("cities"));
            tableEditor.addColumn("cities", "name", "varchar(20)");
            System.out.println(tableEditor.getTableScheme("cities"));
            tableEditor.renameColumn("cities", "name", "city_name");
            System.out.println(tableEditor.getTableScheme("cities"));
            tableEditor.dropColumn("cities", "city_name");
            System.out.println(tableEditor.getTableScheme("cities"));
            tableEditor.dropTable("cities");
        }
    }
}