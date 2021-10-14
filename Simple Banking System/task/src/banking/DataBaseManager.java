package banking;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DataBaseManager {
    Connection conn = null;


    public void makeDBConnection() {

        String createTableSql = "CREATE TABLE IF NOT EXISTS card (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    number text NOT NULL UNIQUE,\n"
                + "    pin text NOT NULL,\n"
                + "    balance integer\n"
                + ");";

        try {
            conn = DriverManager.getConnection("jdbc:sqlite:card.s3db");
            Statement stmt = conn.createStatement();
            // create a new table if it doesn't exist
            stmt.execute(createTableSql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addAccount(Account a) {
        String sql = "INSERT INTO card(number, pin, balance) VALUES(?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, a.getCardNumber());
            pstmt.setString(2, a.getCardPin());
            pstmt.setInt(3, a.getBalance());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean isCardInDatabase(String number, String pin) {
        String sql = "SELECT number, pin FROM card";
        try {
            Connection conn = this.conn;
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                if (number.equals(resultSet.getString("number")) &&
                        pin.equals(resultSet.getString("pin"))) {
                    return true;
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean isCardInDatabase(String number) {
        String sql = "SELECT number, pin FROM card";
        try {
            Connection conn = this.conn;
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                if (number.equals(resultSet.getString("number")))
                    return true;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public int returnBalance(String number) {
        String sql = "SELECT number, balance FROM card";
        try {
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                if (number.equals(resultSet.getString("number"))) {
                    return resultSet.getInt("balance");
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public void updateBalance(String number, int InputtedIncome) {
        String sql = "UPDATE card SET " +
                "balance = ?" +
                "WHERE number = ?";
        int balance = returnBalance(number);
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, balance + InputtedIncome);
            statement.setString(2, number);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
    }

    public void deleteAccount(Account a) {
        String sql = "DELETE FROM card WHERE number = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, a.getCardNumber());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void closeDBConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}