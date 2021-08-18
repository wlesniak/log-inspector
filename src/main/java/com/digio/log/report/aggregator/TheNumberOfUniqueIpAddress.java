package com.digio.log.report.aggregator;

import com.digio.log.report.ApacheLogLine;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class TheNumberOfUniqueIpAddress implements Aggregator {

    private final Set<String> uniqueIpAddresses = new TreeSet<>();

    @Override
    public void process(ApacheLogLine line) {
        uniqueIpAddresses.add(line.getIp());
    }

    @Override
    public String report() {
        String ipAddresses = uniqueIpAddresses.stream().map(Object::toString).sorted().collect(Collectors.joining(","));
        return String.format("%d unique ip addresses: (%s)",uniqueIpAddresses.size(),ipAddresses);
    }

}
