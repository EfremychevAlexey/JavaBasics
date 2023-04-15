public class Main {

    public static void main(String[] args) {

        char ch1 = 'A';
        while(ch1 <= 'Z'){
            System.out.println(ch1 + " - " +(int) ch1);
            ch1++;
        }
        System.out.println();

        char ch2 = 'a';
        while(ch2 <= 'z'){
            System.out.println(ch2 + " - " +(int) ch2);
            ch2++;
        }
        System.out.println();

        char ch3 = 'А';
        while(ch3 <= 'я'){
            System.out.println(ch3 + " - " +(int) ch3);
            ch3++;
        }
    }
}
