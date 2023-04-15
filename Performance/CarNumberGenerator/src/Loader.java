import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Loader {
    // 1 Этап оптимизации
    // Скорость с конкатенацией 24117, 22772, 22672 ms

    // Скорость с StringBuilder и PrintWriter 996, 999, 995 ms

    // Скорость 9 регионов с StringBuilder (создаем новый объект на каждый регион)
    // и PrintWriter 5929, 5892, 5882, 6037 ms

    // Скорость 9 регионов с StringBuilder (создается на каждые 1000 номеров)
    // и PrintWriter 5688, 5907, 6175, 5517, 5688, 5614 ms

    // Скорость 49 регионов с StringBuilder (создаем новый объект на каждый регион)
    // и PrintWriter 25174, 25582 ms

    // Скорость 49 регионов с StringBuilder (создается на каждые 1000 номеров)
    // и PrintWriter 23137, 23313 ms

    // Скорость 49 регионов с StringBuilder (создается на каждый номер)
    // и PrintWriter 41615 ms

    // Скорость 49 регионов с StringBuffer (создаем новый объект на каждый регион)
    // и PrintWriter 37921, 38088 ms

    // Скорость 49 регионов с StringBuffer (создается на каждые 1000 номеров)
    // и PrintWriter 34543, 37367 ms
    // Вывод: создавать StringBuffer 1000 раз быстрее, чем использовать один для 1000 записей

    // 2 Этап Оптимизации.
    // 49 регионов запись в 1 файл из нескольких потоков
    // ThreadPoolExecutor. 2 потока. 24178, 23303мс (Регионы записываются не по порядку.)

    // 49 регионов, каждый регион записываем в отдельный файл из
    // 2 потоков: 26170, 25184, 23507, 24405 ms
    // 4 потоков: 23009, 21837, 22711, 22036 ms
    // 8 потоков: 22454, 22070 ms
    // 10 потоков: 23296, 22293, 23163 ms
    // 16 потоков: 26189, 24991 ms
    // 25 потоков: 21782, 23858 ms
    // 49 потоков: 20586, 20568 ms
    // 99 регионов StringBuilder 8 потоков: 39220 ms
    // padNumber() меняем на void, 99 регионов StringBuilder 8 потоков 33986 ms.


    public static int cores = Runtime.getRuntime().availableProcessors();


    public static void main(String[] args) throws Exception {
        long start = System.currentTimeMillis();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(cores * 4);

        char letters[] = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

        for (int regionCode = 1; regionCode < 100; regionCode++) {
            int regCode = regionCode;
            executor.submit(() -> {
                PrintWriter writer = null;
                try {
                    writer = new PrintWriter("res/" + regCode + "_region_numbers.txt");
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
                StringBuilder buffer = new StringBuilder();
                for (int number = 1; number < 1000; number++) {

                    for (char firstLetter : letters) {
                        for (char secondLetter : letters) {
                            for (char thirdLetter : letters) {
                                buffer.append(firstLetter);
                                padNumber(buffer, number, 3);
                                buffer.append(secondLetter);
                                buffer.append(thirdLetter);
                                padNumber(buffer, regCode, 2);
                                buffer.append("\n");
                            }
                        }
                    }
                }
                writer.write(buffer.toString());
                writer.flush();
                writer.close();
            });
        }
        while (true) {
            if (executor.getActiveCount() == 0 && executor.getQueue().size() == 0) {
                executor.shutdown();
                break;
            }
        }
        System.out.println(System.currentTimeMillis() - start + " ms.");
    }

    private static void padNumber(StringBuilder builder, int number, int numberLength) {
        String numberStr = Integer.toString(number);
        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            builder.append('0');
        }
        builder.append(numberStr);
    }
}
