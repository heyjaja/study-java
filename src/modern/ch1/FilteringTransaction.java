package modern.ch1;

import java.util.*;

import static java.util.stream.Collectors.groupingBy;

public class FilteringTransaction {

    public static void main(String[] args) {
    }

    public static Map<Currency, List<Transaction>> filteringAndGroupTransactions(List<Transaction> transactions) {
        // 그룹화된 트랜잭션 map
        Map<Currency, List<Transaction>> transactionsByCurrencies = new HashMap<>();

        for (Transaction transaction : transactions) {
            if(transaction.getPrice() > 1000) { // 고가의 트랜잭션 필터링
                Currency currency = transaction.getCurrency(); // 트랜잭션 통화 추출
                List<Transaction> transactionsForCurrency = transactionsByCurrencies.get(currency);

                if(transactionsForCurrency == null) { // 현재 통화의 맵 항목이 없으면 생성
                    transactionsForCurrency = new ArrayList<>();
                    transactionsByCurrencies.put(currency, transactionsForCurrency);
                }
                transactionsForCurrency.add(transaction); // 현재 탐색된 트랜잭션을 같은 통화의 트랜잭션 리스트에 추가
            }
        }

        return transactionsByCurrencies;
    }

    public static Map<Currency, List<Transaction>> advanceFunction(List<Transaction> transactions) {
        return transactions.stream()
                        .filter(t -> t.getPrice() > 1000) // 고가의 트랜잭션 필터링
                        .collect(groupingBy(Transaction::getCurrency)); // 통화로 그룹화
    }

    public static class Transaction {
        private int price;
        private Currency currency;

        public Transaction(int price, Currency currency) {
            this.price = price;
            this.currency = currency;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public Currency getCurrency() {
            return currency;
        }

        public void setCurrency(Currency currency) {
            this.currency = currency;
        }

        @Override
        public String toString() {
            return "Transaction{" +
                    "price=" + price +
                    ", currency=" + currency +
                    '}';
        }
    }
}
