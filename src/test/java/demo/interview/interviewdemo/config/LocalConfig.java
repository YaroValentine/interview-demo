package demo.interview.interviewdemo.config;


public class LocalConfig implements Config {

    @Override
    public String getDBHost() {
        return "localhost";
    }

    @Override
    public String getDBLogin() {
        return "postgres";
    }

    @Override
    public String getDBPassword() {
        return "secret";
    }

    @Override
    public int getDBPort() {
        return 5432;
    }


    @Override
    public String getFrontUrl() {
        return "http://127.0.0.1:3000";
    }

    @Override
    public String getUserdataUrl() {
        return "http://127.0.0.1:8089";
    }

    @Override
    public String getSpendUrl() {
        return "http://127.0.0.1:8093";
    }

    @Override
    public String getRegresUrl() {
        return "https://reqres.in/";
    }
}
