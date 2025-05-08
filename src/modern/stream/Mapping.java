package modern.stream;

import modern.apple.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static modern.apple.Data.menu;

public class Mapping {
    public static void main(String[] args) {

        // 매핑 - map: 인수로 제공된 함수는 각 요소에 적용되어 결과가 새로운 요소로 매핑
        // 스트림의 요리명을 추출하는 코드
        List<String> dishNames = menu.stream()
                .map(Dish::name) // Stream<String>
                .collect(Collectors.toList());
        System.out.println("dishNames = " + dishNames);

        // 각 요리명의 길이
        List<Integer> dishNameLengths = menu.stream()
                .map(Dish::name)
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("dishNameLengths = " + dishNameLengths);

        // 평면화 - map과 Arrays.stream 활용하여 문자열 리스트의 문자들을 중복을 제거하여 추출
        String[] arraysOfWords = {"Goodbye", "World"};
        // 문자열 스트림
        Stream<String> streamOfWords = Arrays.stream(arraysOfWords);

        List<String> words = Arrays.asList("Hello", "World");

        // List<Stream<String>>
        words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream) // Stream<Stream<String>>
                .distinct()
                .collect(Collectors.toList());

        // flatMap
        List<String> uniqueCharacters = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream) // 생성된 스트림을 하나의 스트림으로 평면화
                .distinct()
                .collect(Collectors.toList());

        System.out.println("uniqueCharacters = " + uniqueCharacters);

        // 숫자 리스트가 주어졌을 때 각 숫자의 제곱근으로 이루어진 리스트 반환
        List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> squares = numbers2.stream()
                .map(a -> a * a)
                .collect(Collectors.toList());
        System.out.println("squares = " + squares);

        // 두 개의 숫자 리스트의 모든 숫자 쌍 리스트 반환
        List<Integer> numbers3 = Arrays.asList(1, 2, 3);
        List<Integer> numbers4 = Arrays.asList(3, 4);
        List<int[]> pairs = numbers3.stream()
                .flatMap(i -> numbers4.stream()
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        pairs.forEach(ints -> System.out.println("pairs = " + Arrays.toString(ints)));


        // pairs에서 합이 3으로 나누어 떨어지는 쌍만 반환
        List<int[]> pairs2 = numbers3.stream()
                .flatMap(i -> numbers4.stream()
                        .filter(j -> (i + j) % 3 == 0)
                        .map(j -> new int[]{i, j}))
                .collect(Collectors.toList());
        pairs2.forEach(ints -> System.out.println("pairs2 = " + Arrays.toString(ints)));
    }
}
