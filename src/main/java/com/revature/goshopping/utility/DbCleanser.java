package com.revature.goshopping.utility;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

class DbCleanser {
  public static void main(String[] args) throws Exception {
    Connection conn = getConnection();
    clearTable(conn, "users"); // also clears orders and item_order
    clearTable(conn, "item_tag"); // also clears tags
    clearTable(conn, "items");
  }

  private static Connection getConnection() throws SQLException {
    DriverManager.registerDriver(new Driver());
    return DriverManager.getConnection(
        Objects.requireNonNull(System.getenv("DB_URL")),
        Objects.requireNonNull(System.getenv("DB_USERNAME")),
        Objects.requireNonNull(System.getenv("DB_PASSWORD"))
    );
  }

  private static void clearTable(Connection conn, String tableName)
      throws Exception {
    Statement stmt = conn.createStatement();
    stmt.execute("DELETE FROM " + tableName);
  }
}
