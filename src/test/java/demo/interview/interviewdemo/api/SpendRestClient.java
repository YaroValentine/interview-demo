package demo.interview.interviewdemo.api;


import demo.interview.interviewdemo.config.Config;
import demo.interview.interviewdemo.model.CategoryJson;
import demo.interview.interviewdemo.model.SpendJson;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

public class SpendRestClient extends BaseRestClient {

    private final SpendService spendService = retrofit.create(SpendService.class);

    public SpendRestClient() {
        super(Config.getConfig().getSpendUrl());
    }

    public SpendJson addSpend(SpendJson spend) {
        try {
            return spendService.addSpend(spend).execute().body();
        } catch (IOException e) {
            Assertions.fail("Can't execute api call to niffler-spend: " + e.getMessage());
            return null;
        }
    }

    public CategoryJson addCategory(CategoryJson category) {
        try {
            return spendService.addCategory(category).execute().body();
        } catch (IOException e) {
            Assertions.fail("Can't execute api call to niffler-spend: " + e.getMessage());
            return null;
        }
    }

}
