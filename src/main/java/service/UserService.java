package service;

import entity.User;
import entity.UserDto;
import repository.UserRepository;

public class UserService {

    private UserRepository userRepository;
    private Task5CheckService task5CheckService;


    public User findUserById(int id) {
        return userRepository.read(id).orElse(null);
    }

    public String createUser(String clientName, String account, String sum, String bankId) {
        if (isExistUser(clientName)) {
            return "Such client is exist";
        }
        if (isValidUserInfo(clientName, account, sum, bankId)) {
            userRepository.create(clientName.trim(), account.trim(), sum.trim(), bankId.trim());
            return null;
        } else {
            return "Error";
        }
    }

    public String deleteUser(int userId) {
        if (isExistUser(userId)) {
            userRepository.delete(userId);
            return null;
        } else {
            return "Error";
        }
    }

    public String userUpdate(int userId, UserDto userDto) {
        if (isExistUser(userId)) {
            userRepository.update(userId, userDto);
            return null;
        } else {
            return "Such client is not exist";
        }
    }

    public String changeAccountSum(double initialValue, double value, String operation, int userId) {
        double resultSum = 0;
        switch (operation) {
            case "+":
                resultSum = initialValue + value;
                break;
            case "-":
                if (initialValue < value) {
                    return "Value is greater than account has, paste new one";
                } else if (initialValue >= value) {
                    resultSum = initialValue - value;
                }
                break;
        }
        userRepository.updateSum(resultSum, userId);
        return null;
    }

    public String transactionToCleverBankClient(double initialSumFromClient, double initialSumToClient, double value, int idClientFirst, int idClientSecond) {
        double resultSumFromClient;
        double resultSumToClient;
        if (initialSumFromClient >= value) {
        resultSumFromClient = initialSumFromClient - value;
        resultSumToClient = initialSumToClient + value;
        userRepository.updateSum(resultSumFromClient, idClientFirst);
        userRepository.updateSum(resultSumToClient, idClientSecond);
        return null;
        } else {
            return "Value is greater than first account has, paste new one";
        }
    }

    private boolean isExistUser(String clientName) {
        return userRepository.isExistUser(clientName);
    }

    private boolean isExistUser(int userId) {
        return userRepository.isExistUser(userId);
    }

    private boolean isValidUserInfo(String client, String account, String sum, String bankId) {
        return !client.isEmpty() && !account.isEmpty() && !sum.isEmpty() && !bankId.isEmpty();
    }

}
