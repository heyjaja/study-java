package modern.apple;

public class Orange implements Fruit {
    private int weight;
    private Color color;

    public Orange() {
    }

    public Orange(int weight, Color color) {
        this.weight = weight;
        this.color = color;
    }

    public Orange(int weight) {
        this.weight = weight;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Orange{" +
                "weight=" + weight +
                ", color=" + color +
                '}';
    }
}
