package Zadanie1change;

import java.io.FileNotFoundException;

public interface data {
    String loadData(String path)throws FileNotFoundException;
    String validate(String str);
    String show(String str);
    String search(int number);
    void parse(String str);
}
