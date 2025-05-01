package modern.stream;

import modern.apple.Dish;

import java.util.ArrayList;
import java.util.Iterator;
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

        // 외부 반복
        List<String> names1 = new ArrayList<>();
        for (Dish dish : menu) { // 메뉴 리스트를 명시적으로 순차 반복
            names1.add(dish.name());
        }
        System.out.println("names1 = " + names1);

        // 내부 반복
        List<String> names2 = menu.stream()
                .map(Dish::name) // map 메서드를 getName 메서드로 파라미화하여 요리명 추출
                .collect(Collectors.toList()); // 파이프라인 실행
        System.out.println("names2 = " + names2);

        // 외부 반복을 내부 반복으로
        List<String> highCaloricDishes = new ArrayList<>();
        Iterator<Dish> iterator = menu.iterator();
        while(iterator.hasNext()) {
            Dish dish = iterator.next();
            if(dish.calories() > 300) {
                highCaloricDishes.add(dish.name());
            }
        }

        System.out.println("highCaloricDishes = " + highCaloricDishes);

        // 내부반복으로 바꾸기
        List<String> highCaloricDishes2 = menu.stream()
                .filter(d -> d.calories() > 300)
                .map(Dish::name)
                .collect(Collectors.toList());

        System.out.println("highCaloricDishes2 = " + highCaloricDishes2);

        // 스트림 연산 - lazy
        List<String> names = menu.stream()
                .filter(dish -> {
                    System.out.println("filtering = " + dish.name());
                    return dish.calories() > 300;
                })
                .map(dish -> {
                    System.out.println("mapping = " + dish.name());
                    return dish.name();
                }) // filter와 map은 다른 연산이지만 한 과정으로 병합됨 - 루프 퓨전
                .limit(3)
                .collect(Collectors.toList()); // 최종 연산 - 스트림 파이프라인을 실행하고 결과를 만듦
        System.out.println("names = " + names);
    }
}
