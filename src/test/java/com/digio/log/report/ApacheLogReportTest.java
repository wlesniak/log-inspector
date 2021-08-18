package com.digio.log.report;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URISyntaxException;

class ApacheLogReportTest {

    @Test
    public void generateReport() throws IOException, URISyntaxException {
        ApacheLogReport report = new ApacheLogReport("src/test/resources/programming-task-example-data.log");
        String reportOutput = report.generate();
        String[] reportOutputLines = reportOutput.split(System.lineSeparator());
        assertThat(reportOutputLines[0]).isEqualTo("11 unique ip addresses: (168.41.191.34,168.41.191.40,168.41.191.41,168.41.191.43,168.41.191.9,177.71.128.21,50.112.00.11,50.112.00.28,72.44.32.10,72.44.32.11,79.125.00.21)");
        assertThat(reportOutputLines[1]).isEqualTo("top 3 most active IP addresses: 168.41.191.40,50.112.00.11,177.71.128.21");
        assertThat(reportOutputLines[2]).contains("top 3 most visited URLs: /docs/manage-websites/,");
    }

}