package modern.stream;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MakeStream {
    public static void main(String[] args) {
        // 값으로 스트림 만들기
        Stream<String> stream = Stream.of("Modern", "Java", "In", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);

        // 스트림 비우기
        Stream<String> emptyStream = Stream.empty();

        // null이 될 수 있는 객체 스트림
        Stream<String> homeValueStream = Stream.ofNullable(System.getProperty("home"));
        Stream<String> values = Stream.of("config", "home", "user")
                .flatMap(key -> Stream.ofNullable(System.getProperty(key)));

        // 배열로 스트림 만들기
        int[] numbers = {2, 3, 5, 7, 11, 13};
        int sum = Arrays.stream(numbers).sum();
        System.out.println("sum = " + sum);

        // 파일로 스트림 만들기
        long uniqueWord = 0;
        try(Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
            uniqueWord = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("uniqueWord = " + uniqueWord);

        // 함수로 무한 스트림(infinite stream, unbounded stream) 만들기
        // 1. iterate 메서드
        Stream.iterate(0, n-> n+2) // (초깃값, 람다)
                .limit(10)
                .forEach(System.out::println);

        // 피보나치수열 집합
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println(t[0] + "," + t[1]));

        // predicate 적용
        IntStream.iterate(0, n -> n < 100, n -> n+4) // 100 미만
                .forEach(System.out::println);

        // 2. generate 메서드 -> 연속 계산이 아닌 새로운 값 생산
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

    }
}
