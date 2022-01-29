package chap02.refactoring.export;

import chap02.refactoring.domain.SummaryStatistics;

public interface Exporter
{
    String export(SummaryStatistics summaryStatistics);
}
