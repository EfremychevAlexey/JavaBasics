public class Main {
    public static void main(String[] args) {

        System.out.println("Площадь круга с радиусом 5 равна " + GeometryCalculator.getCircleSquare(5));
        System.out.println("Объем сферы с радиусом 5 равен " + GeometryCalculator.getSphereVolume(5));
        System.out.println("Площадь треугольника со сторонами 5, 5, 4 равна " + GeometryCalculator.getTriangleSquare(5, 5, 4));

    }
}
