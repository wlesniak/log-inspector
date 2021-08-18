package com.digio.log.report.aggregator;

import com.digio.log.report.ApacheLogLine;
import java.util.*;
import java.util.stream.Collectors;

public class TopNMostVisitedUrls implements Aggregator {

    private final int n;
    private final Map<String,Integer> countByUrlMap = new HashMap<>();

    public TopNMostVisitedUrls(int n) {
        this.n=n;
    }

    @Override
    public void process(ApacheLogLine line) {
        if(countByUrlMap.containsKey(line.getUrl())) {
            Integer currentCount = countByUrlMap.get(line.getUrl());
            countByUrlMap.put(line.getUrl(),++currentCount);
        } else {
            countByUrlMap.put(line.getUrl(),1);
        }
    }

    @Override
    public String report() {
        List<Map.Entry<String,Integer>> urlMapEntries = new ArrayList<>(countByUrlMap.entrySet());
        urlMapEntries.sort(compareByUrlCountInDecendingOrder());
        String top3MostActiveUrls = extractTopNUrlsCommaSeperated(urlMapEntries);
        return String.format("top %d most visited URLs: %s",n,top3MostActiveUrls);
    }

    private String extractTopNUrlsCommaSeperated(List<Map.Entry<String, Integer>> urlMapEntries) {
        return urlMapEntries.stream()
                .limit(n)
                .map(Map.Entry::getKey).collect(Collectors.joining(","));
    }

    private Comparator<Map.Entry<String, Integer>> compareByUrlCountInDecendingOrder() {
        return (m1, m2) -> m2.getValue().compareTo(m1.getValue());
    }

}
