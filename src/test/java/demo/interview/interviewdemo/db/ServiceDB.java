package demo.interview.interviewdemo.db;

import demo.interview.interviewdemo.config.Config;
import org.apache.commons.lang3.StringUtils;

public enum ServiceDB {

    DB_AUTH("jdbc:postgresql://%s:%d/niffler-auth"),
    DB_SPEND("jdbc:postgresql://%s:%d/niffler-spend"),
    DB_USERDATA("jdbc:postgresql://%s:%d/niffler-userdata"),
    DB_CURRENCY("jdbc:postgresql://%s:%d/niffler-currency");

    private final String jdbcUrl;

    ServiceDB(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }

    public String getJdbcUrl() {
        return String.format(jdbcUrl,
                Config.getConfig().getDBHost(),
                Config.getConfig().getDBPort()
        );
    }

    public String p6SpyUrl() {
        String baseUrl = getJdbcUrl();
        return "jdbc:p6spy:" + StringUtils.substringAfter(baseUrl, "jdbc:");
    }
}
