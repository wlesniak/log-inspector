package com.digio.log.report;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
class ApacheLogParserTest {

    private static final String LOG_LINE = "177.71.128.21 - - [10/Jul/2018:22:21:28 +0200] \"GET /intranet-analytics/ HTTP/1.1\" 200 3574";
    private static final String LOG_LINE_WITH_JUNK = "72.44.32.10 - - [09/Jul/2018:15:48:07 +0200] \"GET / HTTP/1.1\" 200 3574 \"-\" \"Mozilla/5.0 (compatible; MSIE 10.6; Windows NT 6.1; Trident/5.0; InfoPath.2; SLCC1; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; .NET CLR 2.0.50727) 3gpp-gba UNTRUSTED/1.0\" junk extra";

    @Test
    public void parse() {
        ApacheLogParser parser = new ApacheLogParser();
        Optional<ApacheLogLine> logLine = parser.parse(LOG_LINE);
        assertThat(logLine)
                .isPresent()
                .get()
                    .extracting(ApacheLogLine::getIp,ApacheLogLine::getUrl)
                        .contains("177.71.128.21","/intranet-analytics/");
    }

    @Test
    public void parseWithJunkRecord() {
        ApacheLogParser parser = new ApacheLogParser();
        Optional<ApacheLogLine> logLine = parser.parse(LOG_LINE_WITH_JUNK);
        assertThat(logLine).isEmpty();
    }

}