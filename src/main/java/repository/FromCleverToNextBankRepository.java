package repository;

import java.sql.*;
import java.util.ResourceBundle;

public class FromCleverToNextBankRepository {

    private static Connection getConnection() throws SQLException, ClassNotFoundException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
        String dbUrl = resourceBundle.getString("dbUrl");
        String dbUser = resourceBundle.getString("dbUser");
        String dbPass = resourceBundle.getString("dbPass");
        String dbDriver = resourceBundle.getString("dbDriver");
        Class.forName(dbDriver);//? автоматом?
        return DriverManager.getConnection(dbUrl, dbUser, dbPass);
    }

    public void update (double result, int userId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
//        Savepoint savepoint = connection.setSavepoint("Savepoint");//?
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            preparedStatement
                    = connection.prepareStatement("update users set sum = ? where user_id = ?");
            fillPrepareStamenent(preparedStatement, result, userId);

            preparedStatement.execute();
            connection.commit();

        } catch (Exception e) {
            try {
                assert connection != null;
                connection.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            System.out.println("Something went wrong during creation table");
            e.printStackTrace();
        }
        finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void fillPrepareStamenent(PreparedStatement preparedStatement,
                                      double result, int userId) throws SQLException {
        preparedStatement.setDouble(1, result);
        preparedStatement.setInt(2, userId);
    }

}
