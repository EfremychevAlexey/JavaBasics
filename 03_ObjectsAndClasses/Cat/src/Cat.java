import java.awt.Color;

public class Cat {

    private double originWeight;
    private double weight;
    private double foodCount = 0; // количество съеденой еды
    private Color color = Color.WHITE; //переменная в которой хранится инф об окрасе

    public static final int EYES_COUNT = 2; // константа количество глаз
    public static final int MAX_WEIGHT = 9000; // константа максимальный вес
    public static final int MIN_WEIGHT = 1000; // константа минимальный вес
    public static int count = 0; // количество созданных объектов
    public static int totalWeight = 0; // общий вес кошек


    // Конструктор кошки
    public Cat()
    {
        weight = 1500.0 + 3000.0 * Math.random();
        originWeight = weight;
        totalWeight +=weight;
        count++;
    }

    // конструктор создания кошки с заданным весом
    public Cat(double weight)
    {
        this.weight = weight;
        originWeight = weight;
        totalWeight += weight;
        if(weight >= MIN_WEIGHT){
            count++;
        }
    }


    // конструктор создания копии кошки
    public Cat(Cat cat)
    {
        setWeight(cat.getWeight());
        setOriginWeight(cat.getWeight());
        setColor(getColor());
        totalWeight += this.weight;
        if(weight >= MIN_WEIGHT){
            count++;
        }

    }


    // Жива ли кошка
    public boolean isAlive()
    {
        if(weight >= MIN_WEIGHT && weight <= MAX_WEIGHT)
        {
            return true;
        }
        else return false;
    }



// гет сет вес
    public Double getWeight()
    {
        return weight;
    }

// новый метод возвращает количество созданных кошек
    public void setWeight(double weight)
    {
        this.weight = weight;
    }

// Количество еды
    public Double qFood()
    {
        return foodCount;
    }

// гет начальный вес
    public Double getOriginWeight()
    {
        return originWeight;
    }

// сет начальный вес
    public void setOriginWeight(double originWeight)
    {
        this.originWeight = originWeight;
    }

// метод возвращающий данные об окрасе
    public Color getColor()
    {
        return color;
    }

// метод меняющий данные об окрасе
    public void setColor(Color color)
    {
        this.color = color;
    }

// новый метод возвращает количество созданных кошек
    public static int getCount()
    {
        return count;
    }

// Заставить кошку мяукнуть
    public void meow()
    {
        System.out.print("meow!");
        if(isAlive()) {
            weight -=1;
            if(!isAlive()) count--;
        }
    }

// Прием пищи
    public void feed(Double amount)
    {
        if(isAlive()) {
            weight = weight + amount;
            if(!isAlive()) count--;
        }
    }

// Пить
    public void drink(Double amount)
    {
        if(isAlive()) {
            weight = weight + amount;
            if(!isAlive()) count--;
        }
    }

// метод "Сходить в туалет"
    public void pee()
    {
        if(isAlive()) {
            weight -= 10;
            if(!isAlive()) count--;
        }
    }

// состояние кошки
    public String getStatus()
    {
        if(weight < MIN_WEIGHT) {
            return "Dead";
        }
        else if(weight > MAX_WEIGHT) {
            return "Exploded";
        }
        else if(weight > originWeight) {
            return "Sleeping";
        }
        else {
            return "Playing";
        }
    }

}