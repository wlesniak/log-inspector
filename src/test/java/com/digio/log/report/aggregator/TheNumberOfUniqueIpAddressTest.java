package com.digio.log.report.aggregator;

import com.digio.log.report.ApacheLogLine;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;

class TheNumberOfUniqueIpAddressTest {

    @Test
    public void process() {
        TheNumberOfUniqueIpAddress aggregator = new TheNumberOfUniqueIpAddress();
        aggregator.process(new ApacheLogLine("177.71.128.21","/bla"));
        aggregator.process(new ApacheLogLine("178.71.128.21","/bla2"));
        aggregator.process(new ApacheLogLine("177.71.128.21","/bla2"));
        aggregator.process(new ApacheLogLine("171.71.128.21","/bla1"));
        aggregator.process(new ApacheLogLine("171.71.128.22","/bla1"));
        aggregator.process(new ApacheLogLine("171.71.128.22","/bla"));
        assertThat(aggregator.report()).isEqualTo("4 unique ip addresses: (171.71.128.21,171.71.128.22,177.71.128.21,178.71.128.21)");
    }

}