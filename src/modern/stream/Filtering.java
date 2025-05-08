package modern.stream;

import modern.apple.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static modern.apple.Data.menu;
import static modern.apple.Data.specialMenu;

public class Filtering {
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
    }
}
