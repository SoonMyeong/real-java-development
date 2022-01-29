package chap02.refactoring.biz;

import chap02.refactoring.domain.BankTransaction;

@FunctionalInterface
public interface BankTransactionSummarizer
{
    double summarize(double accumulator, BankTransaction bankTransaction);
}
