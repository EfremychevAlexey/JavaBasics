public class TwoDimensionalArray {
    public static char symbol = 'X';

    public static char[][] getTwoDimensionalArray(int size) {

        //TODO: Написать метод, который создаст двумерный массив char заданного размера.
        // массив должен содержать символ symbol по диагоналям, пример для size = 3
        // [X,  , X]
        // [ , X,  ]
        // [X,  , X]

        char[][] ch = new char[size][size];

        for (int i = 0; i < size; i ++){
            for (int j = 0; j < size; j++){
                ch[i][j] = ' ';
                ch[i][0 + i] = 'X';
                ch[i][size - 1 - i] = 'X';
            }
        }
        return ch;
    }
}
