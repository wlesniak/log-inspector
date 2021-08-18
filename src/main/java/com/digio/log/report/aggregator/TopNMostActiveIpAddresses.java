package com.digio.log.report.aggregator;

import com.digio.log.report.ApacheLogLine;
import java.util.*;
import java.util.stream.Collectors;

public class TopNMostActiveIpAddresses implements Aggregator {

    private final Map<String,Integer> countByIpMap = new HashMap<>();
    private final int n;

    public TopNMostActiveIpAddresses(int n) {
        this.n=n;
    }

    @Override
    public void process(ApacheLogLine line) {
        if(countByIpMap.containsKey(line.getIp())) {
            Integer currentCount = countByIpMap.get(line.getIp());
            countByIpMap.put(line.getIp(),++currentCount);
        } else {
            countByIpMap.put(line.getIp(),1);
        }
    }

    @Override
    public String report() {
        List<Map.Entry<String,Integer>> ipAddressMapEntries = new ArrayList<>(countByIpMap.entrySet());
        ipAddressMapEntries.sort(compareByIpCountInDecendingOrder());
        String top3MostActiveIps = extractTopNIPsCommaSeperated(ipAddressMapEntries);
        return String.format("top %d most active IP addresses: %s",n,top3MostActiveIps);
    }

    private String extractTopNIPsCommaSeperated(List<Map.Entry<String, Integer>> ipAddressMapEntries) {
        return ipAddressMapEntries.stream()
                .limit(n)
                .map(Map.Entry::getKey).collect(Collectors.joining(","));
    }

    private Comparator<Map.Entry<String, Integer>> compareByIpCountInDecendingOrder() {
        return (m1, m2) -> m2.getValue().compareTo(m1.getValue());
    }

}
