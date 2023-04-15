
public class Concatenation {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        int count = 0;
        String str = "";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 200000; i++) {
            builder.append("some text some text some text");
            System.out.println(++count);
        }

        System.out.println((System.currentTimeMillis() - start) + " ms");
    }
}
