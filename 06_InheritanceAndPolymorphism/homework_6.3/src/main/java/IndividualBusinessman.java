public class IndividualBusinessman extends Client {

  @Override
  public void put(double amountToPut) {
    if (amountToPut < 1000.0) {
      amountToPut *= 0.99;
    } else {
      amountToPut *= 0.995;
    }
    super.put(amountToPut);
  }

}
