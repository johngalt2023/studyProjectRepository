package service;

import repository.UserRepository;



public class TransactionNextBank {
    private static UserRepository userRepository;

    public static synchronized void fromCleverToNextBank(double initialSumFromClient, double initialSumToClient, double value, int idClientFirst, int idClientSecond) {
        double resultSumFromClient = initialSumFromClient - value;
        double resultSumToClient = initialSumToClient + value;
        userRepository.updateSum(resultSumFromClient, idClientFirst);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userRepository.updateSum(resultSumToClient, idClientSecond);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}



