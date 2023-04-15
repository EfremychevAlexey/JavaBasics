public class BankAccount {

  //доступ к переменной ограничивается пределами одного класса и классами наследниками.
  //переменная относится к объекту класса и объектам всех классов наследников
  private double balance;

  // метод доступен в классах данного пакета и во всех классах наследниках
  protected double getAmount() {
    return (double) Math.round(balance * 100) / 100;
  }

  // метод доступен в классах данного пакета и во всех классах наследниках
  protected void put(double amountToPut) {
    if (amountToPut > 0) {
      balance = balance + amountToPut;
    }
  }

  // метод доступен в классах данного пакета и во всех классах наследниках
  protected void take(double amountToTake) {
    if (balance >= amountToTake) {
      balance = balance - amountToTake;
    }
  }

  // метод доступен в классах данного пакета и во всех классах наследниках
  protected boolean send(BankAccount receiver, double amount) {

    //балансы счета списания и счета назначения
    double thisBalance = this.getAmount();
    double receiverBalance = receiver.getAmount();

    //производим списание средств со счета списания
    this.take(amount);

    //если средства списались, производим пополнение счета назначения, если нет возвращаем false
    if ((thisBalance > this.getAmount())) {
      receiver.put(amount);
    } else {
      return false;
    }

    //если средства зачислены возвращаем true, если нет, возвращаем средства на счет списания и возвращаем false
    if (receiver.getAmount() > receiverBalance) {
      return true;
    } else {
      this.balance = thisBalance;
      return false;
    }
  }
}