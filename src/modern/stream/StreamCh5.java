package modern.stream;

import modern.apple.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static modern.apple.Data.menu;
import static modern.apple.Data.specialMenu;

public class StreamCh5 {
    public static void main(String[] args) {

        // 필터링
        List<Dish> vegetarianDishes = menu.stream()
                .filter(Dish::vegetarian)
                .collect(Collectors.toList());
        System.out.println("vegetarianDishes = " + vegetarianDishes);

        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct() // 중복 제거
                .forEach(System.out::println);

        // 슬라이싱 - 요소를 선택하거나 스킵
        List<Dish> filteredMenu = specialMenu.stream()
                .filter(dish -> dish.calories() < 320)
                .collect(Collectors.toList());
        System.out.println("filteredMenu = " + filteredMenu);

        // 정렬이 된 경우 거짓이 되는 시점에 작업 중단 후 필터링한 요소 반환
        List<Dish> sliceMenu1 = specialMenu.stream()
                .takeWhile(dish -> dish.calories() < 320)
                .collect(Collectors.toList());
        System.out.println("sliceMenu1 = " + sliceMenu1);

        // 정렬이 된 경우 처음으로 거짓이 되는 시점에서 작업을 중단하고 남은 모든 요소 반환
        List<Dish> sliceMenu2 = specialMenu.stream()
                .dropWhile(dish -> dish.calories() < 320)
                .collect(Collectors.toList());
        System.out.println("sliceMenu2 = " + sliceMenu2);

        // 스트림 축소 - limit
        List<Dish> dishes = specialMenu.stream()
                .filter(dish -> dish.calories() > 300)
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("dishes = " + dishes);

        // 요소 건너뛰기 - skip
        List<Dish> dishes2 = specialMenu.stream()
                .filter(dish -> dish.calories() > 300)
                .skip(2) // 처음 n개 요소를 제외한 스트림 반환
                .collect(Collectors.toList());
        System.out.println("dishes2 = " + dishes2);

        // 처음 등장하는 두 고기 요리 필터링
        List<Dish> meetMenu = specialMenu.stream()
                .filter(dish -> dish.type() == Dish.Type.MEAT)
                .limit(2)
                .collect(Collectors.toList());
        System.out.println("meetMenu = " + meetMenu);

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

        // 매칭 - match
        // anyMatch: 적어도 한 요소와 일치하는지 확인
        if(menu.stream().anyMatch(Dish::vegetarian)) {
            System.out.println("This menu is (somewhat) vegetarian friendly!");
        }

        // allMatch: 모든 요소와 일치하는지 확인
        boolean isHealthy = menu.stream()
                .allMatch(dish -> dish.calories() < 1000);
        System.out.println("isHealthy = " + isHealthy);

        // noneMatch: 일치하는 요소가 없는지 확인
        boolean isHealthy2 = menu.stream()
                .noneMatch(dish -> dish.calories() >= 1000);
        System.out.println("isHealthy2 = " + isHealthy2);

        // 3 match는 쇼트서킷 기법 => &&, || 같은 연산 사용 -> 전체 스트림을 처리하지 않고 원하는 요소를 찾으면 즉시 결과 반환

        // 검색 - find
        // findAny: 다른 연산과 연결해서 사용 가능 Optional 반환
        menu.stream()
                .filter(Dish::vegetarian)
                .findAny()
                .ifPresent(dish -> System.out.println(dish.name()));

        // findFirst: 첫번째 요소 반환 - 병렬 실행에서는 첫번째 요소를 찾기 어려움
        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        someNumbers.stream()
                .map(n -> n * n)
                .filter(n -> n % 3 == 0)
                .findFirst()
                .ifPresent(firstSquareDivisibleByThree -> System.out.println("firstSquareDivisibleByThree = " + firstSquareDivisibleByThree));
    }
}
