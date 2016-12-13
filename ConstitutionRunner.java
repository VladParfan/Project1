package Zadanie1change;

import java.io.FileNotFoundException;

public class ConstitutionRunner {
    private static String path = "C://konstytucja.txt";

    public static void main(String[] args) throws FileNotFoundException {
        Constitution cn1 = new Constitution();
        Constitution cn2 = new Constitution();

        String strFromFile1 = cn1.loadData(path);
        strFromFile1 = cn1.validate(strFromFile1);
     
        cn1.parse(strFromFile1);
       System.out.println(cn1.search(6));
        
        
        
    }
}
