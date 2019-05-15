package com.istimeless.securitycore.configuration;

import org.beetl.sql.core.ClasspathLoader;
import org.beetl.sql.core.Interceptor;
import org.beetl.sql.core.SQLManager;
import org.beetl.sql.core.UnderlinedNameConversion;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.spring4.BeetlSqlDataSource;
import org.beetl.sql.ext.spring4.SqlManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author lijiayin
 */
@Configuration
public class BeetlSqlConfiguration {
    
    @Bean
    public SQLManager sqlManager(DataSource dataSource) throws Exception {
        BeetlSqlDataSource beetlSqlDataSource = new BeetlSqlDataSource();
        beetlSqlDataSource.setMasterSource(dataSource);
        SqlManagerFactoryBean sqlManagerFactoryBean = new SqlManagerFactoryBean();
        sqlManagerFactoryBean.setNc(new UnderlinedNameConversion());
        sqlManagerFactoryBean.setCs(beetlSqlDataSource);
        sqlManagerFactoryBean.setDbStyle(new MySqlStyle());
        sqlManagerFactoryBean.setSqlLoader(new ClasspathLoader());
        sqlManagerFactoryBean.setInterceptors(new Interceptor[]{new DebugInterceptor()});
        return sqlManagerFactoryBean.getObject();
    }
}
