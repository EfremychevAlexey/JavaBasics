import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.TreeSet;
// lesson done
public class Main {
    /*
    TODO:
     - реализовать методы класса CoolNumbers
     - посчитать время поиска введимого номера в консоль в каждой из структуры данных
     - проанализоровать полученные данные
     */

  public static void main(String[] args) {

    String number = "Р333ТУ29";
    //////////// Поиск ArrayList ////////////
    ArrayList<String> list = new ArrayList<>(CoolNumbers.generateCoolNumbers());
    long start = System.currentTimeMillis();
    boolean resList = CoolNumbers.bruteForceSearchInList(list, number);
    long end = System.currentTimeMillis();
    String s = (resList ? "найден" : "не найден");
    long resTime = end - start;
    System.out.println("Поиск перебором: номер " + s + ", поиск занял " + resTime + "нс");

    //////////// Поиск в сорированном ArrayList ////////////
    Collections.sort(list);
    start = System.currentTimeMillis();
    resList = CoolNumbers.binarySearchInList(list, number);
    end = System.currentTimeMillis();
    s = (resList ? "найден" : "не найден");
    resTime = end - start;
    System.out.println("Бинарный поиск: номер " + s + ", поиск занял " + resTime + "нс");

    //////////// Поиск HashSet ////////////
    HashSet<String> hashS = new HashSet<>(CoolNumbers.generateCoolNumbers());
    start = System.currentTimeMillis();
    resList = CoolNumbers.searchInHashSet(hashS, number);
    end = System.currentTimeMillis();
    s = (resList ? "найден" : "не найден");
    resTime = end - start;
    System.out.println("Поиск в HashSet: номер " + s + ", поиск занял " + resTime + "нс");

    //////////// Поиск TeeSet ////////////
    TreeSet<String> treeS = new TreeSet<>(CoolNumbers.generateCoolNumbers());
    start = System.currentTimeMillis();
    resList = CoolNumbers.searchInTreeSet(treeS, number);
    end = System.currentTimeMillis();
    s = (resList ? "найден" : "не найден");
    resTime = end - start;
    System.out.println("Поиск в TreeSet: номер " + s + ", поиск занял " + resTime + "нс");
  }
}
