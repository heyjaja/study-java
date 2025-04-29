package modern.ch4;

import modern.apple.Dish;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.groupingBy;
import static modern.apple.Data.menu;

public class StreamBasic {
    public static void main(String[] args) {
        // 스트림 연산을 연결하여 스트림 파이프라인 형성
        List<String> lowCaloricDishesName = menu.stream()
                .filter(d -> d.calories() < 400)
                .sorted(comparing(Dish::calories))
                .map(Dish::name)
                .toList();
        System.out.println("lowCaloricDishesName = " + lowCaloricDishesName);

        Map<Dish.Type, List<Dish>> dishesByType = menu.stream()
                .collect(groupingBy(Dish::type));
        System.out.println("dishesByType = " + dishesByType);

        List<String> threeHighCaloricDishNames =
                menu.stream()// 메뉴 스트림
                        .filter(dish -> dish.calories() > 300) // 고칼로리 요리 필터링
                        .map(Dish::name) // 요리명 추출
                        .limit(3) // 선착순 3개
                        .collect(Collectors.toList()); // 결과를 다른 리스트로 저장

        System.out.println("threeHighCaloricDishNames = " + threeHighCaloricDishNames);
    }
}
