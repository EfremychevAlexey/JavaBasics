import java.text.DecimalFormat;

public class Hospital {

  public static float[] generatePatientsTemperatures(int patientsCount) {
    //TODO: напишите метод генерации массива температур пациентов
    float[] temper = new float[patientsCount];
    for (int i = 0; i < patientsCount; i++) {
      int n = (int)(320F + ((float) Math.random() * 100F) * 0.8F);
      temper[i] = ((float) n / 10F);
    }
    return temper;
  }

  public static String getReport(float[] temperatureData) {
        /*
        TODO: Напишите код, который выводит среднюю температуру по больнице,количество здоровых пациентов,
            а также температуры всех пациентов.
        */
    float midTemp = 0F;
    int countHealthy = 0;
    String report =
        "Температуры пациентов:";
    for (float f : temperatureData) {
      report += " " + f;
      midTemp += f;
      if (f >= 36.2F && f <= 36.9F) {
        ++countHealthy;
      }
    }
    report += "\nСредняя температура: " + ((float)Math.round(midTemp * 100F / temperatureData.length)) / 100F +
        System.lineSeparator() + "Количество здоровых: " + countHealthy;
    return report;
  }
}
