package chap02.refactoring.biz;

import chap02.refactoring.domain.BankTransaction;

import chap02.refactoring.domain.SummaryStatistics;
import chap02.refactoring.filter.BankTransactionFilter;
import java.time.Month;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;


/**
 *  3. 결과 처리 (계산)
 *
 *
 */
public class BankStatementProcessor {

    private final List<BankTransaction> bankTransactions;

    public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
        this.bankTransactions = bankTransactions;
    }


    public SummaryStatistics summarizeTransactions() {
        DoubleSummaryStatistics doubleSummaryStatistics = bankTransactions.stream()
            .mapToDouble(BankTransaction::getAmount)
            .summaryStatistics();

        return new SummaryStatistics(doubleSummaryStatistics.getSum()
            ,doubleSummaryStatistics.getMax()
            ,doubleSummaryStatistics.getMin()
            ,doubleSummaryStatistics.getAverage());
    }


    public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer) {
        double result = 0;
        for(final BankTransaction bankTransaction : bankTransactions)
        {
            result = bankTransactionSummarizer.summarize(result,bankTransaction);
        }
        return result;
    }



    public double calculateTotalAmount() {
        return summarizeTransactions((acc,bankTransaction) ->
            acc + bankTransaction.getAmount());
    }

    public double calculateTotalInMonth(final Month month) {
        return summarizeTransactions((acc,bankTransaction)->
            bankTransaction.getDate().getMonth() == month ? acc + bankTransaction.getAmount() : acc);
    }

    //이건 functional interface 구현 대신 Stream API 이용해서 구현한 내용/
    public double calculateTotalForCategory(final String category) {
        return bankTransactions.stream()
                .filter(bankTransaction -> bankTransaction.getDescription().equals(category))
                .mapToDouble(BankTransaction::getAmount)
                .sum();
    }

    public List<BankTransaction> findTransaction(final BankTransactionFilter bankTransactionFilter) {
        return bankTransactions.stream()
            .filter(bankTransactionFilter::test)
            .collect(Collectors.toList());
    }

    public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount){
        return findTransaction(bankTransaction -> bankTransaction.getAmount() >= amount);
    }



}
