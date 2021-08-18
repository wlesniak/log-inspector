package com.digio.log.report.aggregator;

import com.digio.log.report.ApacheLogLine;

public interface Aggregator {

    void process(ApacheLogLine row);
    String report();

}
