package modern.stream;

import modern.apple.Trader;
import modern.apple.Transaction;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Practice {
    public static void main(String[] args) {
        List<Transaction> transactions = getTransactions();

        // 1. 2011년에 일어난 모든 트랜잭션을 찾아 오름차순으로 정리
        List<Transaction> transactionAt2011Acs = transactions.stream()
                .filter(transaction -> transaction.year() == 2011)
                .sorted(Comparator.comparing(Transaction::value))
                .toList();

        System.out.println("transactionAt2011Acs = " + transactionAt2011Acs);
        // 2. 거래자가 근무하는 모든 도시를 중복없이 나열
        List<String> cities = transactions.stream()
                .map(transaction -> transaction.trader().city())
                .distinct()
                .toList(); // distinct 대신 toSet() 사용 가능
        System.out.println("cities = " + cities);

        // 3. 케임브리지에서 근무하는 모든 거래자를 찾아서 이름순으로 정렬
        List<Trader> tradersFromCambridge = transactions.stream()
                .map(Transaction::trader)
                .filter(trader -> trader.city().equals("Cambridge"))
                .distinct()
                .sorted(Comparator.comparing(Trader::name))
                .toList();

        System.out.println("tradersFromCambridge = " + tradersFromCambridge);

        // 4. 모든 거래자의 이름을 알파벳순으로 정렬하여 반환
        String traderNames = transactions.stream()
                .map(transaction -> transaction.trader().name())
                .distinct()
                .sorted()
                .reduce("", (n1, n2) -> n1 + n2); // 반복적으로 문자열 생성 -> .collect(joining()) 사용하면 효율 향상

        System.out.println("traderNames = " + traderNames);

        // 5. 밀라노에 거래자가 있는가?
        boolean isAnyMatchMilan = transactions.stream()
                .anyMatch(transaction -> transaction.trader().city().equals("Milan"));
        System.out.println("isAnyMatchMilan = " + isAnyMatchMilan);

        // 6. 케임브리지에 거주하는 거래자의 모든 트랜잭션 값 출력
        transactions.stream()
                .filter(transaction -> transaction.trader().city().equals("Cambridge"))
                .map(Transaction::value)
                .forEach(System.out::println);

        // 7. 전채 트랜잭션 중 최댓값
        transactions.stream()
                .map(Transaction::value)
                .reduce(Integer::max)
                .ifPresent(max -> System.out.println("max = " + max));

        transactions.stream()
                .max(Comparator.comparing(Transaction::value))
                .ifPresent(max -> System.out.println("max = " + max));

        // 8. 전체 트랜잭션 중 최솟값
        transactions.stream()
                .map(Transaction::value)
                .reduce(Integer::min)
                .ifPresent(min -> System.out.println("min = " + min));

        transactions.stream()
                .min(Comparator.comparing(Transaction::value))
                .ifPresent(min -> System.out.println("min = " + min));
    }

    private static List<Transaction> getTransactions() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
        return transactions;
    }
}
