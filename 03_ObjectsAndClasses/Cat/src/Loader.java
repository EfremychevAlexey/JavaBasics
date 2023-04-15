
public class Loader {

//метод по созданию котенка
    private static Cat getCitten()
    {
        return new Cat(1100.0);
    }

    public static void main(String[] args) {

// создаем подопытных кошек
        Cat murka = new Cat();

// создание копии кошки Мурки (андрогин Бублик)
        Cat bublik = new Cat(murka);
        System.out.println(bublik.getWeight());
        System.out.println(murka.getWeight());

// продолжаем создавать подопытных кошек
        Cat garfild = new Cat();
        Cat kapusta = new Cat();
        Cat timofey = new Cat();
        Cat vaska = new Cat();
        Cat aliska = new Cat();
        System.out.println("Мурка весит " + murka.getWeight() + "грамм, в данный момент она " + murka.getStatus() + ". " +
                "\nГарфилд весит " + garfild.getWeight() + "грамм, в данный момент он " + garfild.getStatus() + ". " +
                "\nКапуста весит " + kapusta.getWeight() + "грамм, в данный момент она " + kapusta.getStatus() + ". " +
                "\nТимофей весит " + timofey.getWeight() + "грамм, в данный момент он " + timofey.getStatus() + ". " +
                "\nАлиска весит " + aliska.getWeight() + "грамм, в данный момент она " + aliska.getStatus() + ". " +
                "\nВаська весит " + vaska.getWeight() + "грамм, в данный момент он " + vaska.getStatus() + ". " +
                "\nСейчас мы произведем над Муркой и Гарфилдом некоторые опыты и посмотрим, что из этого получится.\n" +
                "Итак, начнем с Мурки.\n");

// Опыт по замяукиванию кошки
        while (murka.getWeight() > (Cat.MIN_WEIGHT + 1))
        {
            System.out.print("Вес Мурки: " + murka.getWeight() + ", попросим ее сделать ");
            murka.meow();
            System.out.println("\nТеперь ее вес " + murka.getWeight() + ", она " + murka.getStatus());
        }

        System.out.print("Если Мурка еще раз сделает ");
        murka.meow();
        System.out.print(", она ");
        System.out.print(murka.getStatus());
        System.out.println(". Мы не можем с ней так поступить...\n\nТеперь Гарфилд.\n");


// Опыт по закармливанию кота
        while (garfild.getWeight() < (Cat.MAX_WEIGHT - 100.5))
        {
                System.out.println("Вес Гарфилда " + garfild.getWeight() + ". Заставим его поесть..");
                garfild.feed(100.5);
                System.out.println("Его вес " + garfild.getWeight() + ", он " + garfild.getStatus());
        }

        System.out.print("Если Гарфилд поест еще раз, он ");
        garfild.feed(100.5);
        System.out.print(garfild.getStatus());
        System.out.println(". Мы не можем с ним так поступить...");


// Опыты с другими кошками
        vaska.drink(5.1);
        System.out.println("\nВаська попил, теперь он " + vaska.getStatus());

        System.out.print("Капуста сделела ");
        kapusta.meow();
        System.out.println(", сейчас она " + kapusta.getStatus());

        timofey.feed(10.2);
        System.out.println("Тимофей поел и сейчас " + timofey.getStatus());

// Алиска ест и идет в туалет
        aliska.feed(150.0);
        aliska.feed(100.0);
        System.out.println("\nАлиска пошла в туалет...\n");
        System.out.println("Её вес " + aliska.getWeight() + "\n");

        for (int i = 0; i < 5; i++) aliska.pee();
        System.out.println("Её вес " + aliska.getWeight() + "\n");
        System.out.println("Всего Алиска съела " + aliska.qFood() + " грамм корма.\n");


// Проведем веечерюю поверку подопытного кошачего состава
        System.out.println("Всего в лаборатории кошек: " + Cat.getCount() + " \nУпсс...");


// Ночью появляются подопытные котата
        Cat marusya = getCitten();
        Cat ponchik = getCitten();
        Cat jora = getCitten();

        System.out.println(marusya.getWeight());
        System.out.println(ponchik.getWeight());
        System.out.println(jora.getWeight());

    }

}
