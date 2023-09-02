package service;

import repository.Task5CheckRepository;

import java.io.File;
import java.io.IOException;

public class Task5CheckService {
    private Task5CheckRepository task5CheckRepository;

    public void createCheck
            (double value, int userFirstAccount, int userSecondAccount, String bankNameFirst, String bankNameSecond) {
        try {
            String pathdir = "D:\\IdeaProjects\\ServletProject\\ServletProject\\check";
            File dir = new File(pathdir);
            if(!dir.exists()) {
                boolean dircreation = dir.mkdir();
            }
            if(dir.isDirectory()){
                String[] content = dir.list();
                if(content != null) {
                   int newchecknumber = content.length + 1;
                   String filename = newchecknumber + ".txt";
                   File file = new File(pathdir, filename);
                   boolean filecreation = file.createNewFile();
                   task5CheckRepository.createCheck(newchecknumber, value, userFirstAccount,
                           userSecondAccount, bankNameFirst, bankNameSecond,pathdir, filename);
                } else {
                    File file = new File(pathdir, "1.txt");
                    boolean filecreation = file.createNewFile();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
