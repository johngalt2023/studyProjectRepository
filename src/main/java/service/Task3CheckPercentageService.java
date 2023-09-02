package service;

import entity.YAMLValues;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import repository.UserRepository;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;

public class Task3CheckPercentageService {
    
    private UserRepository userRepository;
    private final Timer timer = new Timer();
    private final int userId;
    private double sum;

    public Task3CheckPercentageService(int userId, double sum) {
        this.userId = userId;
        this.sum = sum;
    }

    public void start() {
        int delay = 2000;
        int period = 30000;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                LocalDate date = LocalDate.now();
                if (date.getDayOfMonth() == date.lengthOfMonth()) {
                    try {
                        addPercantage();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                } else {
                    run();
                }
            }
            public void addPercantage() throws FileNotFoundException {
                double percentage = (double) dataYAMLExtract().get("onePercentage");
                double addPercentageSum = percentage * sum + sum;
                userRepository.addPercentage(addPercentageSum, userId);
            }
        }, delay, period);
    }

    private Map<String, Object> dataYAMLExtract() throws FileNotFoundException {
        InputStream inputStream = new FileInputStream("src/main/resources/values.yml");
        //    Yaml yaml = new Yaml(new Constructor(YAMLValues.class));//почему не приводится к типу класса YAMLvalues?
        //    YAMLValues data = yaml.load(inputStream);
        Yaml yaml = new Yaml();
        Map<String, Object> data = yaml.load(inputStream);
        return data;
    }

    //    ResourceBundle resourceBundle = ResourceBundle.getBundle("onepercentage");//2й вариант извлечь значения: из properties
//    private final double onePercentage = Double.parseDouble(resourceBundle.getString("onepercentage"));

}


