package com.digio.log.report.aggregator;

import com.digio.log.report.ApacheLogLine;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

class TopNMostVisitedUrlsTest {

    @Test
    public void process() {
        TopNMostVisitedUrls aggregator = new TopNMostVisitedUrls(3);
        aggregator.process(new ApacheLogLine("177.71.128.21","/temp-redirect"));
        aggregator.process(new ApacheLogLine("178.71.128.21","/docs/manage-websites/"));
        aggregator.process(new ApacheLogLine("177.71.128.21","/docs/manage-websites/"));
        aggregator.process(new ApacheLogLine("171.71.128.21","/temp-redirect"));
        aggregator.process(new ApacheLogLine("171.71.128.22","/temp-redirect"));
        aggregator.process(new ApacheLogLine("171.71.128.22","/temp-redirect"));
        aggregator.process(new ApacheLogLine("171.71.128.22","http://example.net/faq/"));
        aggregator.process(new ApacheLogLine("171.71.129.22","/asset.js"));
        aggregator.process(new ApacheLogLine("171.71.126.22","/asset.js"));
        aggregator.process(new ApacheLogLine("171.71.126.22","/asset.js"));
        aggregator.process(new ApacheLogLine("171.71.128.22","/docs/"));
        aggregator.process(new ApacheLogLine("177.71.128.21","/docs/manage-websites/"));
        assertThat(aggregator.report()).isEqualTo("top 3 most visited URLs: /temp-redirect,/docs/manage-websites/,/asset.js");
    }


}