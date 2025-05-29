package basic.oop;

public class Rectangle {
    int width = 5;
    int height = 8;

    public int calculateArea() {
        int area = width * height;
        System.out.println("Area of Rectangle is: " + area);
        return area;
    }
    public int calculatePerimeter() {
        int perimeter = 2 * (width + height);
        System.out.println("Perimeter of Rectangle is: " + perimeter);
        return perimeter;
    }
    public boolean isSquare() {
        boolean isSquare = width == height;
        System.out.println("isSquare is: " + isSquare);
        return isSquare;
    }
}
