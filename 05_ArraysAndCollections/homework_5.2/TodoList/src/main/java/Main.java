import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

  private static TodoList todoList = new TodoList();

  public static void main(String[] args) {
    // TODO: написать консольное приложение для работы со списком дел todoList

    System.out.println("Записная книжка открыта");
    while (true) {
      Scanner scanner = new Scanner(System.in);
      String instruction = scanner.nextLine();

      String[] str = instruction.split("\\s+", 2);
      int index = -1;
      String action = "";

      Pattern pattern = Pattern.compile("\\d+");
      Matcher matcher = pattern.matcher(instruction);
      Pattern pnTodo = Pattern.compile("\\s");
      Matcher mTodo = pnTodo.matcher(instruction);
      String argument = instruction;

      if (matcher.find()) {
        int start = matcher.start();
        int end = matcher.end();
        index = Integer.parseInt(instruction.substring(start, end));
        action = instruction.substring(end).trim();
      }
      else if(mTodo.find()){
        action = instruction.substring(mTodo.end()).trim();
      }

      if (str.length == 0 ){
        argument = instruction;
      }
      if(str.length >= 0){
        argument = str[0];
      }

      if (argument.contains("LIST")) {
        int i = 0;
        for (String s : todoList.getTodos()) {
          System.out.println(i + " - " + s);
          i++;
        }
      }
      if (argument.contains("ADD")) {
        todoList.add(index, action);
      }
      if (argument.contains("EDIT")) {
          todoList.edit(action, index);
      }
      if (argument.contains("DELETE")) {
          todoList.delete(index);
      }
      if (argument.contains("End")) {
        System.out.println("Работа завершена");
        break;
      }
    }
  }
}









        /*LIST — выводит дела с их порядковыми номерами;
ADD — добавляет дело в конец списка или дело на определённое место, сдвигая остальные дела вперёд, если указать номер; если указан несуществующий индекс - добавить в конец списка.
EDIT — заменяет дело с указанным номером; если указан несуществующий индекс - ничего не делать.
DELETE — удаляет; если указан несуществующий индекс - ничего не делать.*/
