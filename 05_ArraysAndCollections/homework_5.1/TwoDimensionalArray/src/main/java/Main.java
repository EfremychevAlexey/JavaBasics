public class Main {
    public static void main(String[] args) {
        //Распечатайте сгенерированный в классе TwoDimensionalArray.java двумерный массив

        char[][] ch = TwoDimensionalArray.getTwoDimensionalArray(51);
       for(int i = 0; i < ch.length; i++){

           for(int j = 0; j < ch.length; j++){
               System.out.print(ch[i][j] + " ");
           }
           System.out.println();
       }
    }
}
