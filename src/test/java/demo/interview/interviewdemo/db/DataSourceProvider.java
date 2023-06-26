package demo.interview.interviewdemo.db;

import com.p6spy.engine.spy.P6DataSource;

import demo.interview.interviewdemo.config.Config;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum DataSourceProvider {

    INSTANCE;

    private final Map<ServiceDB, DataSource> dataSources = new ConcurrentHashMap<>();

    public DataSource getDataSource(ServiceDB service) {
        return dataSources.computeIfAbsent(service, serviceDB -> {
            PGSimpleDataSource sds = new PGSimpleDataSource();
            sds.setURL(serviceDB.getJdbcUrl());
            sds.setUser(Config.getConfig().getDBLogin());
            sds.setPassword(Config.getConfig().getDBPassword());
//            return sds;
            return new P6DataSource(sds);
//            return new TransactionAwareDataSourceProxy(new P6DataSource(sds));
        });

    }

}
