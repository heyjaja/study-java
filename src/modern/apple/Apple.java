package modern.apple;

import java.util.ArrayList;
import java.util.List;

import static modern.apple.Color.*;

public class Apple implements Fruit {

    private int weight;
    private Color color;

    public Apple(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public Apple() {

    }

    public Apple(Integer weight) {
        this.weight = weight;
    }

    public static List<Apple> createInventory() {
        return new ArrayList<>(List.of(
                new Apple(80, GREEN),
                new Apple(155, RED),
                new Apple(124, BLUE)
        ));
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "weight=" + weight +
                ", color='" + color + '\'' +
                '}';
    }
}