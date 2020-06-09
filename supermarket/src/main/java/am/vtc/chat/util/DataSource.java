package am.vtc.chat.util;
//
//import com.mchange.v2.c3p0.ComboPooledDataSource;
//import com.mchange.v2.c3p0.DataSources;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class DataSource {


    //ConnectinFactory
    private static final HikariDataSource hikariDataSource;
//  private static ComboPooledDataSource cpds;

  static {

//    cpds = new ComboPooledDataSource();
//    cpds.setJdbcUrl(Settings.getInstance().getString("db.url"));
//    cpds.setUser(Settings.getInstance().getString("db.user"));
//    cpds.setPassword(Settings.getInstance().getString("db.password"));
//    try {
//      cpds.setDriverClass(Settings.getInstance().getString("db.driver"));
//    } catch (PropertyVetoException e) {
//      e.printStackTrace();
//    }
//
//    // Optional Settings
//    cpds.setInitialPoolSize(5);
//    cpds.setMinPoolSize(5);
//    cpds.setMaxPoolSize(200);

    HikariConfig config = new HikariConfig();
    config.setJdbcUrl(Settings.getInstance().getString("db.url"));
    config.setDriverClassName(Settings.getInstance().getString("db.driver"));
    config.setUsername(Settings.getInstance().getString("db.user"));
    config.setPassword(Settings.getInstance().getString("db.password"));
    //prepare statementi query-ner@ x qanakutyamb qesh e anum
    config.addDataSourceProperty("cachePrepStmts", "true");
    config.addDataSourceProperty("prepStmtCacheSize", "250");
    config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
    //ete x jamanaki @ntacqum azat connection chtvec SQLException kgci
    config.setConnectionTimeout(5000);
    //max connectionneri qanakutyun@
    config.setMaximumPoolSize(25);
    //qani irakan connection petq e lini
    config.setMinimumIdle(10);
    //connectioni aprelu jamanak@
    config.setMaxLifetime(300000);
    //ete voch mi texic chi ogtagortsvum x jamanak pakel
    config.setIdleTimeout(150000);
    hikariDataSource = new HikariDataSource(config);
  }

  private DataSource() {

  }

  public static Connection getConnection() throws SQLException {
    long start = System.nanoTime();
    Connection connection = hikariDataSource.getConnection();

    long end = System.nanoTime();
    time += (end - start);
    System.out.println((end - start) / 1000000.0);
    return connection;
  }

  public static long time = 0;
}
