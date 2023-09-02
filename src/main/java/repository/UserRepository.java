package repository;

import entity.User;
import entity.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class UserRepository {
    public Optional<User> read(int id) {
        try (Connection connection = JDBCConnector.createConnection()) {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("select * from users  where user_id = ?");//inner join banks on users.bank_id = banks.bank_id как реализовать?
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            return convertResult(result);
        } catch (SQLException e) {
            System.out.println("Error during getting user by id");
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public void create(String client, String account, String sum, String bankId ) {
        try (Connection connection = JDBCConnector.createConnection()) {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("insert into users (client_name, account, sum, bank_id) " +
                    "values (?,  ?, ?, ?)");
            preparedStatement.setString(1, client);
            preparedStatement.setInt(2, Integer.parseInt(account));
            preparedStatement.setDouble(3, Double.parseDouble(sum));
            preparedStatement.setInt(4, Integer.parseInt(bankId));
            preparedStatement.execute();
        } catch (Exception e) {
            System.out.println("Something went wrong during creation table");
            e.printStackTrace();
        }
    }

    public void delete(int userId) {
        try (Connection connection = JDBCConnector.createConnection()) {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("delete * from users  where user_id = ?");
            preparedStatement.setInt(1, userId);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Error during deleting user by userId");
            e.printStackTrace();
        }
    }

    public void update(int userId, UserDto userDto) {
        try (Connection connection = JDBCConnector.createConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("update users set client_name = ?, " +
                    "account = ?, " +
                    "sum = ?, " +
                    "bank_id = ? where user_id = ?");
            fillPrepareStatement(preparedStatement, userId, userDto);
            preparedStatement.execute();
        } catch (SQLException e) {
            System.out.println("Something went wrong during updating table");
            e.printStackTrace();
        }
    }


    public void updateSum(double result, int userId) {
        try (Connection connection = JDBCConnector.createConnection()) {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("update users set sum = ? where user_id = ?");
            preparedStatement.setDouble(1, result);
            preparedStatement.setDouble(2, userId);
            preparedStatement.execute();
        } catch (Exception e) {
            System.out.println("Something went wrong during updating table");
            e.printStackTrace();
        }
    }

    public void addPercentage(double addPercentageSum, int userId) {
        try (Connection connection = JDBCConnector.createConnection()) {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("update users set sum = ? where user_id = ?");
            preparedStatement.setDouble(1, addPercentageSum);
            preparedStatement.setDouble(2, userId);
            preparedStatement.execute();
        } catch (Exception e) {
            System.out.println("Something went wrong during adding percentage");
            e.printStackTrace();
        }
    }

    private Optional<User> convertResult(ResultSet result) throws SQLException {
        if (result.next()) {
            return Optional.of(new User(result.getInt(1),
                    result.getString(2),
                    result.getInt(3),
                    result.getDouble(4),
                    result.getInt(5)));
        } else {
            return Optional.empty();
        }
    }

    public boolean isExistUser(String clientName) {
        try (Connection connection = JDBCConnector.createConnection()) {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("select * from users where client = ?");
            preparedStatement.setString(1, clientName);
            ResultSet resultSet = preparedStatement.executeQuery();
            return isExistResult(resultSet);
        } catch (SQLException e) {
            System.out.println("error during extracting of user");
        }
        return false;
    }

    public boolean isExistUser(int userId) {
        try (Connection connection = JDBCConnector.createConnection()) {
            PreparedStatement preparedStatement
                    = connection.prepareStatement("select * from users where user_id = ?");
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            return isExistResult(resultSet);
        } catch (SQLException e) {
            System.out.println("error during deleting of user");
        }
        return false;
    }

    private boolean isExistResult(ResultSet resultSet) throws SQLException {
//        if (resultSet.next()) {
//            return true;
//        }
//        return false;

//        return resultSet.next() ? true : false;

        return resultSet.next();//????while?
    }
    private void fillPrepareStatement(PreparedStatement preparedStatement,
                                      int userId,
                                      UserDto userDto) throws SQLException {
        preparedStatement.setString(1, userDto.getClientName().trim());
        preparedStatement.setInt(2, userDto.getAccount());
        preparedStatement.setDouble(3, userDto.getSum());
        preparedStatement.setInt(4, userDto.getBankId());
        preparedStatement.setInt(5, userId);
    }
}
