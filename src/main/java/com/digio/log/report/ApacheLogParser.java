package com.digio.log.report;

import org.apache.log4j.Logger;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApacheLogParser {

    private static final String regex = "^(\\S+) (\\S+) (\\S+) \\[([\\w:/]+\\s[+\\-]\\d{4})] \"(\\S+)\\s?(\\S+)?\\s?(\\S+)?\" (\\d{3}|-) (\\d+|-)\\s?\"?([^\"]*)\"?\\s?\"?([^\"]*)?\"?$";
    private final Logger logger = Logger.getLogger(ApacheLogParser.class);

    Optional<ApacheLogLine> parse(String line) {
        Pattern p = Pattern.compile(regex);
        logger.info("Apache log input line: " + line);
        Matcher matcher = p.matcher(line);
        if (matcher.find()) {
            String ip = matcher.group(1);
            String url = matcher.group(6);
            return Optional.of(new ApacheLogLine(ip,url));
        }
        logger.error("Unable to parse log line: "+line);
        return Optional.empty();
    }

}
