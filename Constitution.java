package Zadanie1change;

import java.util.ArrayList;
import java.io.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.util.regex.Pattern;

public class Constitution implements data {
    ArrayList<Article> Articles = new ArrayList<Article>();

    @Override
    public String loadData(String path) throws FileNotFoundException {
        StringBuilder str = new StringBuilder();
        File file = new File(path);

        exists(path);

        try {
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    str.append(s);
                    str.append("\n");
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return str.toString();
    }

    @Override
    public String validate(String str) {
        StringBuilder tempStr = new StringBuilder(str);
        String token = "-\n";
        String token2 = " ";
        String replaceToken1 = "Kancelaria Sejmu";
        String replaceToken2 = "2009-11-16";
        char replaceToken3 = 65533;
        char repT3 = 160;
        String result;
        int index = tempStr.indexOf(token);
        int indexNext;
        while (index != -1) {
            tempStr.delete(tempStr.indexOf(token), tempStr.indexOf(token) + 2);
            indexNext = tempStr.indexOf(token2, index);
            tempStr.insert(indexNext, "\n");
            index = tempStr.indexOf(token);
        }

        result = tempStr.toString();
        result = result.replaceAll(replaceToken1, "");
        result = result.replaceAll(replaceToken2, "");
        result = result.replace(replaceToken3, repT3);
        return result;
    }

    @Override
    public String show(String str) {
        return null;
    }

    @Override
    public String search(int number) {
        int objIndex = 0;
        for (Article temp:Articles){
            if(temp.getID()==number){
                objIndex = Articles.indexOf(temp);
                break;
            }
        }
        return Articles.get(objIndex).getText();
    }

    @Override
    public void parse(String str) {

        String cutStr;
        StringBuilder tempStr = new StringBuilder(str);
        String token = "Art. ";
        String tokenLine;

        int startIndex = tempStr.indexOf(token);
        int endIndex;

        
        cutStr = tempStr.substring(startIndex);

        while (cutStr.length() != 0) {
            
            cutStr = cutStr.replaceFirst(token, "");
           
            tempStr.setLength(0);
            tempStr.insert(0, cutStr);
            
            endIndex = cutStr.indexOf(token);
            
            if (endIndex != -1) {
                tokenLine = tempStr.substring(0, endIndex);
            }else{
                tokenLine = tempStr.toString();
            }
            Article tempArt = new Article(0, 0, "");
            String[] item = tokenLine.split("\\.",2);
            tempArt.setID(Integer.parseInt(item[0]));
            tempArt.setText(item[1]);
            Articles.add(tempArt);
            cutStr = cutStr.replaceFirst(Pattern.quote(tokenLine), "");
        }
    }
    
    
    


    private static void exists(String path) throws FileNotFoundException {
        File file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException(file.getName());
        }
    }
}
