package modern.stream;

import modern.apple.Dish;

import java.util.Arrays;
import java.util.List;

import static modern.apple.Data.menu;

public class Matching {
    public static void main(String[] args) {

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
