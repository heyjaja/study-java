package modern.ch2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Predicate;

import static modern.ch2.FilteringApples.Color.*;

public class FilteringApples {

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>(List.of(
                new Apple(80, GREEN),
                new Apple(155, RED),
                new Apple(124, BLUE)
        ));
        List<Apple> greenApples = filterGreenApples(inventory);
        System.out.println("greenApples = " + greenApples);
        List<Apple> greenApples2 = filterApplesByColor(inventory, GREEN);
        System.out.println("greenApples2 = " + greenApples2);
        List<Apple> blueApples = filterApplesByColor(inventory, BLUE);
        System.out.println("blueApples = " + blueApples);

        List<Apple> heavyApples = filterApplesByWeight(inventory, 150);
        System.out.println("heavyApples = " + heavyApples);

        // 람다식 사용
        List<Apple> redAndHeavyApples =
                filterApples(inventory,
                        apple -> RED.equals(apple.getColor()) && apple.getWeight() > 150);
        System.out.println("redAndHeavyApples = " + redAndHeavyApples);

        // 예쁘게 프린트하기
        prettyPrintApple(inventory, apple -> "An apple of " + apple.getWeight() + "g");

        // 리스트 형식으로 추상화
        List<Apple> redApples = filter(inventory, apple -> RED.equals(apple.getColor()));
        System.out.println("redApples = " + redApples);

        // 정렬
        inventory.sort(Comparator.comparingInt(Apple::getWeight));

        // 스레드
        Thread t = new Thread(() -> System.out.println("Hello world"));
        t.start();
        // Callable
        Future<String> threadName;
        try (ExecutorService executorService = Executors.newCachedThreadPool()) {
            threadName = executorService.submit(() -> Thread.currentThread().getName());
            System.out.println("threadName = " + threadName);
        }
    }

    public static List<Apple> filterGreenApples(List<Apple>  inventory) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if(GREEN.equals(apple.getColor())) {
                result.add(apple);
            }
        }

        return result;
    }

    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if(apple.getColor().equals(color)) {
                result.add(apple);
            }
        }

        return result;
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory, int weight) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if(apple.getWeight() > weight) {
                result.add(apple);
            }
        }

        return result;
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if(p.test(apple)) { // 프레디케이트 객체로 사과 검사 조건을 캡슐화
                result.add(apple);
            }
        }

        return result;
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for (Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if(p.test(e)) {
                result.add(e);
            }
        }

        return result;
    }

    public enum Color {
        GREEN,
        RED,
        BLUE
    }

    public interface ApplePredicate { // 선택 조건을 결정하는 인터페이스
        boolean test(Apple apple);
    }

    public interface AppleFormatter {
        String accept(Apple a);
    }

    public static class Apple {
        private int weight;
        private Color color;

        public Apple(int weight, Color color) {
            this.weight = weight;
            this.color = color;
        }

        public int getWeight() {
            return weight;
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

}
