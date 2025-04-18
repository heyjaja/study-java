package modern.ch1;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FilteringApples {
    static String GREEN = "green";
    static String BLUE = "blue";

    public static void main(String[] args) {
        List<Apple> inventory = List.of(
                new Apple(80, GREEN),
                new Apple(155, GREEN),
                new Apple(124, BLUE)
        );
        List<Apple> greenApples = filterApples(inventory, FilteringApples::isGreenApple);
        System.out.println("greenApples = " + greenApples);
        List<Apple> heavyApples = filterApples(inventory, FilteringApples::isHeavyApple);
        System.out.println("heavyApples = " + heavyApples);

        // 메서드 전달에서 람다로
        List<Apple> greenApples2 = filterApples(inventory, (apple -> GREEN.equals(apple.getColor())));
        System.out.println("greenApples2 = " + greenApples2);
        List<Apple> heavyApples2 = filterApples(inventory, (apple -> apple.getWeight() > 150));
        System.out.println("heavyApples2 = " + heavyApples2);

        // 람다가 너무 길어진다면 메서드를 정의하여 메서드 참조를 활용하는 것이 바람직하다.
        List<Apple> lightOrBlueApples = filterApples(inventory,
                apple -> apple.getWeight() < 80 || BLUE.equals(apple.getColor()));
        System.out.println("lightOrBlueApples = " + lightOrBlueApples);

        // 순차처리
        List<Apple> heavyApples3 = inventory.stream().filter(a -> a.getWeight() > 150).toList();
        System.out.println("heavyApples3 = " + heavyApples3);
        // 병렬처리
        List<Apple> heavyApples4 = inventory.parallelStream().filter(a -> a.getWeight() > 150).toList();
        System.out.println("heavyApples4 = " + heavyApples4);

    }

    public static List<Apple> filterGreenApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if(GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }

        return result;
    }

    public static List<Apple> filterHeavyApples(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(apple.getWeight() > 150) {
                result.add(apple);
            }
        }

        return result;
    }

    public static boolean isGreenApple(Apple apple) {
        return GREEN.equals(apple.getColor());
    }

    public static boolean isHeavyApple(Apple apple) {
        return apple.getWeight() > 150;
    }

    static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) { // 메서드가 p라는 이름의 predicate 파라미터로 전달됨
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(p.test(apple)) { // 이 사과는 p가 제시하는 조건에 맞는가?
                result.add(apple);
            }
        }

        return result;
    }

    public static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
        }

        public void setWeight(int weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
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

}
