public class LegalPerson extends Client {

  private static final double DELTA = 0.01;

  @Override
  public void take(double amountToTake) {
    double sum = amountToTake + amountToTake * DELTA;
    super.take(sum);
  }
}
