package modern.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionDescriptor {
    public static void main(String[] args) {
        List<String> listOfStrings = List.of(
                "apple",
                "banana",
                "orange",
                "watermelon",
                "grape",
                ""
        );
        // java.util.function.Predicate<T> - T 객체를 받아 boolean 반환하는 test 추상 메서드 정의
        Predicate<String> nonEmptyStringPredicate = (s) -> !s.isEmpty();
        List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
        System.out.println("nonEmpty = " + nonEmpty);

        // java.util.function.Consumer<T> - T 객체를 받아 void를 반환하는 accept 추상 메서드 정의
        // T 형식의 객체를 인수로 받아 어떤 동작을 수행하고 싶을 때 사용
        forEach(List.of(1,2,3,4,5), i -> System.out.println(i));

        // java.util.function.Function<T, R> - T를 인수로 받아 R 객체를 반환하는 apply 추상 메서드 정의
        // 입력을 출력으로 매핑하는 람다를 정의할 때 활용
        List<Integer> l = map(
                List.of("lambdas", "in", "action"),
                s -> s.length()
        );
        System.out.println("l = " + l);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if(p.test(t)) {
                results.add(t);
            }
        }

        return results;
    }

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }

        return result;
    }
}
