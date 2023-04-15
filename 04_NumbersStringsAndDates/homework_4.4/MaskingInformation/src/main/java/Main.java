public class Main {

    public static void main(String[] args) {

        String text = "Пин код <6160>";
        String placeholder = "+++";
        System.out.println(searchAndReplaceDiamonds(text, placeholder));
    }

    public static String searchAndReplaceDiamonds(String text, String placeholder) {
        // TODO: реализовать метод, если в строке нет <> - вернуть строку без изменений

        String str = "";

        for (int i = 0; i < text.length(); i++){

            if(text.charAt(i) == '<'){
                int j = i;
                while (j < text.length()){
                    if(text.charAt(j) == '>'){
                        str += placeholder;
                        i = j + 1;
                        break;
                    }
                    j++;
                }
            }
            if(i == text.length()) break;
            str += text.charAt(i);

        }
        return str;
    }
}

