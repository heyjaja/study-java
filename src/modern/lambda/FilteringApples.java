package modern.lambda;

import modern.apple.*;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static modern.apple.Color.*;
import static modern.apple.ListUtil.filter;
import static modern.apple.ListUtil.map;

public class FilteringApples {

    static Map<String, Function<Integer, Fruit>> map = new HashMap<>();
    static {
        map.put("apple", Apple::new);
        map.put("orange", Orange::new);
    }

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>(List.of(
                new Apple(80, GREEN),
                new Apple(155, RED),
                new Apple(124, BLUE)
        ));

        // 메서드 참조 - 가독성 향상, (::) 구분자 사용
        inventory.sort(Comparator.comparing(Apple::getWeight));

        List<String> str = Arrays.asList("ABC", "def", "Qwerty");
        filter(str, FilteringApples::isValidName);
        // str.sort((s1, s2) -> s1.compareToIgnoreCase(s2));
        str.sort(String::compareToIgnoreCase);

        // 생성자 참조
        Supplier<Apple> c1 = Apple::new; // 디폴트 생성자
        Apple a1 = c1.get(); // Supplier의 get 메서드를 호출해 새로운 Apple 객체 생성
        System.out.println("a1 = " + a1);

        Function<Integer, Apple> c2 = Apple::new; // Apple(Integer weight) 생성자
        Apple a2 = c2.apply(110); // apply 메서드에 무게를 인수로 호출하여 새로운 객체 생성
        System.out.println("a2 = " + a2);

        List<Integer> weights = Arrays.asList(7, 3, 4, 10);
        List<Apple> apples = map(weights, Apple::new); // map 메서드로 생성자 참조 전달
        System.out.println("apples = " + apples);

        BiFunction<Integer, Color, Apple> c3 = Apple::new;
        Apple a3 = c3.apply(170, BLUE);
        System.out.println("a3 = " + a3);

        Fruit f1 = giveMeFruit("Orange", 199);
        System.out.println("f1 = " + f1);

    }

    public static boolean isValidName(String string) {
        return Character.isUpperCase(string.charAt(0));
    }

    public static Fruit giveMeFruit(String fruit, Integer weight) {
        return map.get(fruit.toLowerCase())
                .apply(weight);
    }

}
