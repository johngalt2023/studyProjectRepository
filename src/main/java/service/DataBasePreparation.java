package service;

import repository.JDBCConnector;

import java.sql.Connection;
import java.sql.Statement;

public class DataBasePreparation {
    public void dataBaseCreation() {
        try (Connection connection = JDBCConnector.createConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("create table users (" +
                    " user_id int primary key auto_increment," +
                    " client_name varchar(25)," +
                    " account int," +
                    " sum double," +
                    " bank_id int," +
                    " foreign key (bank_id) references banks(bank_id)) ");
            statement.execute("create table banks (" +
                    " bank_id int primary key auto_increment," +
                    " bank_name varchar(25)) ");
            statement.execute("insert into users values(1, 'Lermontov', 1234567890, 130000, 1), " +
                    "(2, 'Pushkin', 1234567891, 50000, 1), " +
                    "(3, 'Turgenev', 2234567890, 20000, 1), " +
                    "(4, 'Kipling', 3234567890, 3000, 1), " +
                    "(5, 'Nekrasov', 4234567890, 10000, 2), " +
                    "(6, 'London', 5234567890, 44000, 3), " +
                    "(7, 'Twen', 6234567890, 54000, 4), " +
                    "(8, 'Gogolj', 7234567890, 93000, 5), " +
                    "(9, 'Dostoevskij', 8234567890, 149000, 2), " +
                    "(10, 'Driser', 9234567890, 110000, 3), " +
                    "(11, 'Chehov', 1234567892, 22000, 4), " +
                    "(12, 'Esenin', 1234567893, 484000, 5), " +
                    "(13, 'Djuma', 1234567894, 12000, 2), " +
                    "(14, 'Kuprin', 1234567895, 3000, 3), " +
                    "(15, 'Baljzak', 1234567896, 39000, 4), " +
                    "(16, 'Twen', 1234567897, 54000, 5), " +
                    "(17, 'Gjugo', 1234567898, 23000, 2), " +
                    "(18, 'Vern', 1234567899, 199000, 3), " +
                    "(19, 'Griboedov', 1111111111, 82000, 4), " +
                    "(20, 'Tolstoy', 2222222222, 200000, 5) ");
            statement.execute("insert into banks values(1, 'Clever-Bank'), " +
                    "(2, 'Tinjkoff'), " +
                    "(3, 'Barclys'), " +
                    "(4, 'Gasprombank'), " +
                    "(5, 'JPMorganChase') ");
        } catch (Exception e) {
            System.out.println("Something went wrong during creation table");
            e.printStackTrace();
        }
    }
}
