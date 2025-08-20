package modern.stream.collect;

import modern.apple.Dish;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.*;
import static modern.apple.Data.menu;

public class Grouping {
    public static void main(String[] args) {
        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::type));
        System.out.println("dishesByType = " + dishesByType);

        // 칼로리 기준
        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
                groupingBy(dish -> {
                    if(dish.calories() <= 400) return CaloricLevel.DIET;
                    else if (dish.calories() <= 700) return CaloricLevel.NORMAL;
                    else return CaloricLevel.FAT;
                })
        );
        System.out.println("dishesByCaloricLevel = " + dishesByCaloricLevel);

        // 프레디케이트 필터 적용 -> 비어있는 목록의 key도 포함
        Map<Dish.Type, List<Dish>> caloricDishesByType = menu.stream()
                .collect(groupingBy(Dish::type, filtering(dish -> dish.calories() > 500, toList())));

        System.out.println("caloricDishesByType = " + caloricDishesByType);

        // mapping
        Map<Dish.Type, List<String>> dishNamesByType = menu.stream()
                .collect(groupingBy(Dish::type, mapping(Dish::name, toList())));
        System.out.println("dishNamesByType = " + dishNamesByType);
    }
    public enum CaloricLevel {DIET, NORMAL, FAT}
}
