package entity;

public class UserDto {
    private String clientName;
    private int account;
    private double sum;
    private int bankId;

    public UserDto(String clientName, int account, double sum, int bankId) {
        this.clientName = clientName;
        this.account = account;
        this.sum = sum;
        this.bankId = bankId;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public int getBankId() {
        return bankId;
    }

    public void setBankId(int bankId) {
        this.bankId = bankId;
    }
}
