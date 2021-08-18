package com.digio.log.report.aggregator;

import com.digio.log.report.ApacheLogLine;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static org.assertj.core.api.Assertions.assertThat;

class TopNMostActiveIpAddressesTest {

    @Test
    public void process() {
        TopNMostActiveIpAddresses aggregator = new TopNMostActiveIpAddresses(3);
        aggregator.process(new ApacheLogLine("177.71.128.21","/bla"));
        aggregator.process(new ApacheLogLine("178.71.128.21","/bla2"));
        aggregator.process(new ApacheLogLine("177.71.128.21","/bla2"));
        aggregator.process(new ApacheLogLine("171.71.128.21","/bla1"));
        aggregator.process(new ApacheLogLine("171.71.128.22","/bla1"));
        aggregator.process(new ApacheLogLine("171.71.128.22","/bla"));
        aggregator.process(new ApacheLogLine("171.71.128.22","/bla"));
        aggregator.process(new ApacheLogLine("171.71.129.22","/bla"));
        aggregator.process(new ApacheLogLine("171.71.126.22","/bla"));
        aggregator.process(new ApacheLogLine("171.71.126.22","/bla3"));
        aggregator.process(new ApacheLogLine("171.71.128.22","/bla"));
        aggregator.process(new ApacheLogLine("177.71.128.21","/bla2"));
        assertThat(aggregator.report()).isEqualTo("top 3 most active IP addresses: 171.71.128.22,177.71.128.21,171.71.126.22");
    }

}