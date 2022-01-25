package chap02.refactoring;

import chap02.refactoring.domain.BankTransaction;

import java.time.Month;
import java.util.List;


/**
 *  3. 결과 처리 (계산)
 */
public class BankStatementProcessor {

    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }

    public double calculateTotalAmount() {
        return bankTransactions.stream().mapToDouble(BankTransaction::getAmount).sum();
    }

    public double calculateTotalInMonth(final Month month) {
        return bankTransactions.stream()
                .filter(bankTransaction -> bankTransaction.getDate().getMonth() == month)
                .mapToDouble(BankTransaction::getAmount)
                .sum();
    }

    public double calculateTotalForCategory(final String category) {
        return bankTransactions.stream()
                .filter(bankTransaction -> bankTransaction.getDescription().equals(category))
                .mapToDouble(BankTransaction::getAmount)
                .sum();
    }


}
