package study.codingtest.nexon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by kh.jin on 2020. 3. 28.
 */
public class Test4 {

    public static void main (String[] args) throws java.lang.Exception
    {
        BufferedReader br;
        br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        Bank bank = new Bank();
        bank.open(Arrays.stream(input.split(","))
                .map(TransactionRequest::new)
                .collect(Collectors.toList()));

        if(bank.peakRequests <= 1) {
            System.err.println("WARN: Only one thread is used for processing. It can be a reason of low performance issues.");
        }

        System.out.println(bank.getCurrentAccountReport());
    }

    static class TransactionRequest {
        String accountId;
        Integer amount;

        TransactionRequest(String order) {
            String[] tokens = order.split(":", 2);
            this.accountId = tokens[0].trim();
            this.amount = Integer.valueOf(tokens[1].trim());
        }
    }

    static class Account {
        Integer balance = 0;
        Integer transact(Integer amount) {
            balance = balance + amount;
            return balance;
        }
    }

    public static class Bank {
        Map<String, Account> accounts = new HashMap<>();
        int peakRequests = 0;
        synchronized Integer request(TransactionRequest request) {
            AtomicInteger currentRequestCount = new AtomicInteger();
            int newPeak = currentRequestCount.incrementAndGet();
            if(peakRequests < newPeak) {
                peakRequests = newPeak;
            }

            Account account = accounts.get(request.accountId);
            if(account == null) {
                account = new Account();
                accounts.put(request.accountId, account);
            }

            Integer remain = account.transact(request.amount);

            currentRequestCount.decrementAndGet();
            return remain;
        }

        void open(List<TransactionRequest> requests) {
            /* Parallel processing is intended. It's not a reason of the problem. */
            requests.parallelStream().forEach(this::request);
        }

        String getCurrentAccountReport() {
            return accounts.entrySet().stream()
                    .map(e -> String.format("%s:%d", e.getKey(), e.getValue().balance))
                    .collect(Collectors.joining("\n"));
        }
    }
}
