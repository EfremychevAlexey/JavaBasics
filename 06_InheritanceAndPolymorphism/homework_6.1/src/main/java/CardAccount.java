public class CardAccount extends BankAccount {

  // доступ к переменной ограничивается пределами одного класса и классами наследниками
  // переменная принадлежит классу, не отностится к объектам класса и классов наследников, не может быть изменена.
  protected static final double DELTA = 0.01;

  // метод доступен в классах данного пакета и во всех классах наследниках
  @Override
  protected void take(double amountToTake) {
    double sum = amountToTake + amountToTake * DELTA;
    super.take(sum);
  }
}
