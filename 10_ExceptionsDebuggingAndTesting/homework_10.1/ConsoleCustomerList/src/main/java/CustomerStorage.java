import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");

        Pattern prNumber = Pattern.compile("^\\+79\\d{9}$");
        Matcher isTrueNumber = prNumber.matcher(components[INDEX_PHONE]);
        Pattern prMail = Pattern.compile("^.+@\\w+\\.[a-z]+$");
        Matcher isTrueMail = prMail.matcher(components[INDEX_EMAIL]);


        if (components.length != 4){
            throw new IllegalArgumentException("Неверный формат ввода.");
        }

        if (!isTrueNumber.find()){
            throw new IllegalArgumentException("Invalid phone number format. Available format: \n" +
                    "+79215637722");
        }

        if(!isTrueMail.find()) {
            throw new IllegalArgumentException("Invalid email format. Available format: \n" +
                    "vasily.petrov@gmail.com");
        }
        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}