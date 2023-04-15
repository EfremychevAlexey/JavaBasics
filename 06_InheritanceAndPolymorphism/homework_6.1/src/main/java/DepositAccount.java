import java.util.Calendar;
import java.util.GregorianCalendar;

public class DepositAccount extends BankAccount {


  // доступ к переменной ограничивается пределами класса
  // переменная относится к объекту класса
  private Calendar lastIncome = new GregorianCalendar();

  // метод доступен в классах данного пакета и во всех классах наследниках
  @Override
  protected void put(double amountToPut) {
    super.put(amountToPut);
    lastIncome = Calendar.getInstance();
  }

  // метод доступен в классах данного пакета и во всех классах наследниках
  @Override
  protected void take(double amountToTake) {
    long msInMounth = (long) 3600000 * 24 * 31;
    if (System.currentTimeMillis() - msInMounth > lastIncome.getTime().getTime()) {
      super.take(amountToTake);
    }
  }
}