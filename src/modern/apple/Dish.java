package modern.apple;

public record Dish (
        String name,
        boolean vegetarian,
        int calories,
        Type type
) {

    public enum Type {
        MEAT,
        FISH,
        OTHER
    }
}
