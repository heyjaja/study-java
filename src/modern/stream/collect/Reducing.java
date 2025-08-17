package modern.stream.collect;

import modern.apple.Dish;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Optional;

import static java.util.stream.Collectors.*;
import static modern.apple.Data.menu;

public class Reducing {
    public static void main(String[] args) {
        // 최댓값, 최솟값 Collectors.maxBy, Collectors.minBy
        // 칼로리가 가장 높은 요리
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::calories);
        Optional<Dish> mostCalorieDish = menu.stream()
                .collect(maxBy(dishCaloriesComparator));
        System.out.println("mostCalorieDish = " + mostCalorieDish);

        // 요약 연산 Collectors.summingInt
        // 총 칼로리 계산
        int totalCalories = menu.stream().collect(summingInt(Dish::calories));
        System.out.println("totalCalories = " + totalCalories);

        // 평균 칼로리
        double avgCalories = menu.stream().collect(averagingDouble(Dish::calories));
        System.out.println("avgCalories = " + avgCalories);

        // 요수 수, 칼로리 합계, 평균, 최대, 최솟값 계산
        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::calories));
        System.out.println("menuStatistics = " + menuStatistics);

        // 문자열 연결
        String shortMenu = menu.stream().map(Dish::name).collect(joining(", "));
        System.out.println("shortMenu = " + shortMenu);

        // 범용 리듀싱 Collectors.reducing
        int totalCalories2 = menu.stream().collect(reducing(0, Dish::calories, (i, j) -> i + j));
        System.out.println("totalCalories2 = " + totalCalories2);

        Optional<Dish> mostCalorieDish2 = menu.stream()
                .collect(reducing((d1, d2) -> d1.calories() > d2.calories() ? d1 : d2));
        System.out.println("mostCalorieDish2 = " + mostCalorieDish2);

        // IntStream 활용
        int totalCalories3 = menu.stream().mapToInt(Dish::calories).sum();
        System.out.println("totalCalories3 = " + totalCalories3);

    }
}
