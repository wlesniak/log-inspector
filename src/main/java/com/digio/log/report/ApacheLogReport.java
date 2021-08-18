package com.digio.log.report;

import com.digio.log.report.aggregator.*;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ApacheLogReport {

    private final ApacheLogParser logParser;
    private final Aggregator reportAggregator;
    private final String logFilePath;
    private final Logger logger = Logger.getLogger(ApacheLogReport.class);

    public ApacheLogReport(String logFilePath) {
        this.logFilePath=logFilePath;
        this.logParser = new ApacheLogParser();
        AggragatorManager aggragateManager = new AggragatorManager();
        aggragateManager.addAggregator(new TheNumberOfUniqueIpAddress());
        aggragateManager.addAggregator(new TopNMostActiveIpAddresses(3));
        aggragateManager.addAggregator(new TopNMostVisitedUrls(3));
        this.reportAggregator = aggragateManager;
    }

    public String generate() throws IOException {
        Stream<String> logLines = Files.lines(Paths.get(logFilePath));
        logLines.forEach(line -> logParser.parse(line).ifPresent(reportAggregator::process));
        String report = reportAggregator.report();
        logger.info(report);
        return report;
    }

}
