package modern.stream;

import modern.apple.Dish;

import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static modern.apple.Data.menu;

public class NumberStream {
    public static void main(String[] args) {

        // 숫자형 스트림: IntStream, DoubleStream, LongStream
        int calories = menu.stream()
                .mapToInt(Dish::calories)
                .sum(); // 스트림이 비어있는 경우 0 반환
        System.out.println("calories = " + calories);

        // 객체 스트림으로 복원 - 정수가 아닌 다른 값으로 반환하고 싶을 때
        IntStream intStream = menu.stream().mapToInt(Dish::calories);
        Stream<Integer> stream = intStream.boxed();

        // OptionalInt
        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::calories)
                .max();
        int max = maxCalories.orElse(1);
        System.out.println("max = " + max);

        // 숫자 범위: range(시작, 종료값 포함x), rangeClosed(시작, 종료값 포함o)
        IntStream evenNumbers = IntStream.rangeClosed(1, 100) // 1~100 범위
                .filter(n -> n % 2 == 0);
        System.out.println("evenNumbers.count() = " + evenNumbers.count());

        // 피타고라스 수: a*a + b*b = c*c -> 직각 삼각형
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100).boxed() // a: 1~100
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100) // b: a~100
                                .filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
                                .mapToObj(b -> new int[]{a, b, (int)Math.sqrt(a*a + b*b)})
                );

        pythagoreanTriples.limit(5)
                .forEach(t -> System.out.println(t[0]+", "+ t[1] + ", " + t[2]));

        // 제곱근을 2번 계산하는 것을 개선
        Stream<double[]> pythagoreanTriples2 = IntStream.rangeClosed(1, 100).boxed() // a: 1~100
                .flatMap(a ->
                        IntStream.rangeClosed(a, 100) // b: a~100
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a*a + b*b)}) // 3개의 수를 먼저 만들고
                                .filter(t -> t[2] % 1 == 0) // 계산이 성립하는 수 필터링
                );

        pythagoreanTriples2.limit(5)
                .forEach(t -> System.out.println((int)t[0] + ", " + (int)t[1] + ", " + (int)t[2]));
    }
}
