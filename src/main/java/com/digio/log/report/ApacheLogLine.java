package com.digio.log.report;

public class ApacheLogLine {

    private final String ip;
    private final String url;

    public ApacheLogLine(String ip, String url) {
        this.ip = ip;
        this.url = url;
    }

    public String getIp() {
        return ip;
    }

    public String getUrl() {
        return url;
    }

}
