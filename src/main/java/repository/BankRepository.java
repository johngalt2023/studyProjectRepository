package repository;

import entity.Bank;
import entity.User;
import jakarta.servlet.annotation.WebServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


public class BankRepository {
    public Optional<Bank> read(int bankId) {
        try (Connection connection = JDBCConnector.createConnection()) {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("select * from banks  where bank_id = ?");
            preparedStatement.setInt(1, bankId);
            ResultSet result = preparedStatement.executeQuery();
            return convertResult(result);
        } catch (SQLException e) {
            System.out.println("Error during getting bank by id");
            e.printStackTrace();
        }
        return Optional.empty();
    }
    private Optional<Bank> convertResult(ResultSet result) throws SQLException {
        if (result.next()) {
            return Optional.of(new Bank(result.getInt(1),
                    result.getString(2)));
        } else {
            return Optional.empty();
        }
    }
}
