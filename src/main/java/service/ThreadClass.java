package service;

public class ThreadClass extends Thread {


    private final double initialSumFromClientClever;
    private final double initialSumToClientNextBank;
    private final double value;
    private final int idClientFirst;
    private final int idClientSecond;
    private final TransactionNextBank transactionNextBank;


    public ThreadClass
            (double initialSumFromClientClever, double initialSumToClientNextBank, double value, int idClientFirst, int idClientSecond, TransactionNextBank transactionNextBank) {
        this.initialSumFromClientClever = initialSumFromClientClever;
        this.initialSumToClientNextBank = initialSumToClientNextBank;
        this.value = value;
        this.idClientFirst = idClientFirst;
        this.idClientSecond = idClientSecond;
        this.transactionNextBank = transactionNextBank;

    }

    @Override
    public void run() {
        TransactionNextBank.fromCleverToNextBank
                (initialSumFromClientClever, initialSumToClientNextBank, value, idClientFirst, idClientSecond);
    }
}