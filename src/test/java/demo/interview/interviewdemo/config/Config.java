package demo.interview.interviewdemo.config;

public interface Config {

    static Config getConfig() {
        if ("staging".equals(System.getProperty("env"))) {
            return null;
        }
        return new LocalConfig();
    }

    String getDBHost();

    String getDBLogin();

    String getDBPassword();

    int getDBPort();

    String getFrontUrl();

    String getUserdataUrl();

    String getSpendUrl();

    String getRegresUrl();
}
