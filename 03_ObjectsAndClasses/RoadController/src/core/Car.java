package core;

public class Car
{
    public String number;
    public int height;
    public double weight;
    public boolean hasVehicle;
    public boolean isSpecial;

    public String toString()
    {
        String special = isSpecial ? "СПЕЦТРАНСПОРТ " : "";
        return "\n=========================================\n" +
            special + "Автомобиль с номером " + number +
            ":\n\tВысота: " + height + " мм\n\tМасса: " + weight + " кг";
    }

    // гет, сет номерной знак
    public String getNumber() { return number; }
    public void setNumber(String number) { this.number = number; }

    // гет, сетвысота
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }

    // гет, сет номерной знак
    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = weight; }

    // гет, сет наличие прицепа
    public boolean getHasVehicle() { return hasVehicle; }
    public void setHasVehicle(boolean hasVehicle) { this.hasVehicle = hasVehicle; }

    // гет, сет назначение транспорта
    public boolean getIsSpecial() { return isSpecial; }
    public void setIsSpecial(boolean isSpecial) { this.isSpecial = isSpecial; }

}