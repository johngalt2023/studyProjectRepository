package entity;

public class YAMLValues {
    private String onePercentage;
    private double value;

    public YAMLValues(String onePercentage, double value) {
        this.onePercentage = onePercentage;
        this.value = value;
    }

    public String getOnePercentage() {
        return onePercentage;
    }

    public void setOnePercentage(String onePercentage) {
        this.onePercentage = onePercentage;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
