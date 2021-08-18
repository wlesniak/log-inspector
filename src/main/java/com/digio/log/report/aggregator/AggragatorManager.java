package com.digio.log.report.aggregator;

import com.digio.log.report.ApacheLogLine;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AggragatorManager implements Aggregator {

    private final List<Aggregator> aggregators = new ArrayList<>();

    public void addAggregator(Aggregator aggregator) {
        this.aggregators.add(aggregator);
    }

    public void process(ApacheLogLine row) {
        aggregators.forEach(aggregator -> aggregator.process(row));
    }

    @Override
    public String report() {
        return aggregators.stream()
                .map(Aggregator::report)
                .collect(Collectors.joining(System.lineSeparator()));
    }

}
