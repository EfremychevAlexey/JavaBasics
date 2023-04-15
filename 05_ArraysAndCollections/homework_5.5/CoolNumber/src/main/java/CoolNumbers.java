import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;
// lesson done
public class CoolNumbers {

    public static List<String> generateCoolNumbers() {
        char[] chars = {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'};
        ArrayList<String> list = new ArrayList<>();
        int r;
        int number;
        for(int i = 0; i < 2000000; i++){
            StringBuilder sb = new StringBuilder();
            r = ((int)(199 * Math.random()) + 1);
            number = ((int)(8.9 * Math.random()) + 1);
            sb.append(chars[(int)(chars.length * Math.random())])
                .append(number * 111).append(chars[(int)(chars.length * Math.random())])
                .append(chars[(int)(chars.length * Math.random())])
                .append(String.format("%02d",r));
            list.add(sb.toString());
        }

        return list;
    }

    public static boolean bruteForceSearchInList(List<String> list, String number) {
        return list.contains(number);
    }

    public static boolean binarySearchInList(List<String> sortedList, String number) {

        return Collections.binarySearch(sortedList, number) >= 0;
    }

    public static boolean searchInHashSet(HashSet<String> hashSet, String number) {

        return hashSet.contains(number);
    }

    public static boolean searchInTreeSet(TreeSet<String> treeSet, String number) {
        return treeSet.contains(number);
    }

}
