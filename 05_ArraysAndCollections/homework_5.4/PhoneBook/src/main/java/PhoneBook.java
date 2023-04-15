import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//lesson done
public class PhoneBook {

    TreeMap<String, String> phoneBook = new TreeMap<>();
    public void addContact(String phone, String name) {
        // проверьте корректность формата имени и телефона
        // если такой номер уже есть в списке, то перезаписать имя абонента
        if(hasName(name) && hasNumber(phone)){
            phoneBook.put(phone, name);
            System.out.println("Контакт сохранен!");
        } else System.out.println("Неверный формат ввода");
    }

    public String getNameByPhone(String phone) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найдены - вернуть пустую строку
        if(phoneBook.containsKey(phone)){
            return phoneBook.get(phone) + " - " + phone;
        } else return "";
    }

    public Set<String> getPhonesByName(String name) {
        // формат одного контакта "Имя - Телефон"
        // если контакт не найден - вернуть пустой TreeSet
        TreeSet<String> set = new TreeSet<>();
        if(phoneBook.containsValue(name)){
            StringBuilder str = new StringBuilder(name + " - ");
            for (String s: phoneBook.keySet()){
                if(phoneBook.get(s).contains(name)){
                    str.append(s);
                }
            }
            set.add(str.toString());
        }
        return set;
    }

    public Set<String> getAllContacts() {
        // формат одного контакта "Имя - Телефон"
        // если контактов нет в телефонной книге - вернуть пустой TreeSet
        TreeSet<String> allContacts = new TreeSet<String>();
        if (!phoneBook.isEmpty()) {
            for (Map.Entry e : phoneBook.entrySet()) {
                allContacts.add(e.getValue() + " - " + e.getKey());
            }
        }
        return allContacts;
    }

    public void printNotNum(String number) {
        System.out.println("Такого номера нет в телефонной книге.\n"
            + "Введите имя абонента для номера " + "\"" + number + "\":");
    }

    public void printNotName(String name) {
        System.out.println("Такого имени в телефонной книге нет.\n"
            + "Введите номер телефона для абонента " + "\"" + name + "\":");
    }

    public boolean hasName(String name) {
        Pattern prName = Pattern.compile("^[А-яa-zA-Z]+$");
        Matcher trueName = prName.matcher(name);
        return trueName.find();
    }

    public boolean hasNumber(String phone) {
        Pattern prNumber = Pattern.compile("79\\d{9}");
        Matcher trueNumber = prNumber.matcher(phone);
        return trueNumber.find();
    }

}