package chap02.refactoring.filter;

import chap02.refactoring.domain.BankTransaction;

@FunctionalInterface
public interface BankTransactionFilter
{
    boolean test(BankTransaction bankTransaction);
}
