package repository;

import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class Task5CheckRepository {
    public void createCheck
            (int newchecknumber, double value, int userFirstAccount, int userSecondAccount,
             String bankNameFirst, String bankNameSecond, String pathdir, String filename) {
        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(pathdir + "\\" + filename, true))) {
            String checkN = String.valueOf(newchecknumber);
            LocalDateTime dateTime = LocalDateTime.now();
            LocalDate date = LocalDate.now();
            LocalTime time = LocalTime.now();
//            ZonedDateTime zonedDateTime = dateTime.atZone(ZoneOffset.UTC);
//            String iso8601 = zonedDateTime.toString();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dt = LocalDateTime.of(date, time);
            String formatterDateTime = dt.format(formatter);

            String firstAccount = String.valueOf(userFirstAccount);
            String secondAccount = String.valueOf(userSecondAccount);
            String valueSum = String.valueOf(value);
            String text1 = "----------------------------------------";
            String text2 = "|            Банковский чек            |";
            String text3 = "| Чек:                  " + checkN +" |";
            String text4 = "| "+ formatterDateTime + "            |";
            String text5 = "| Тип транзакции:               Перевод|";
            String text6 = "| Банк отправителя:     " + bankNameFirst +" |";
            String text7 = "| Банк получателя:      " + bankNameSecond +" |";
            String text8 = "| Счет отправителя:     " + firstAccount +" |";
            String text9 = "| Счет получателя:      " + secondAccount +" |";
            String text10 = "| Сумма:               " + valueSum +" |";
            String text11 = "|______________________________________|";
            String[] text = {text1,text2,text3,text4,text5,text6,text7,text8,text9,text10,text11};
            for (String eachtext : text){
                bufferedWriter.write(eachtext + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
