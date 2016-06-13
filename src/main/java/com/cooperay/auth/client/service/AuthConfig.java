package com.cooperay.auth.client.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.builder.xml.XMLConfigBuilder;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

/**
 * 配置类
 * @author Administrator
 *
 */
public class AuthConfig implements InitializingBean {
    
    Logger logger = Logger.getLogger(AuthConfig.class);
    
    private static String mapperPath = "com/cooperay/auth/client/mapper/mybatis-mappers.xml";
    
    private DataSource dataSource;
    
    private SqlSessionFactory sqlSessionFactory;
    
    
    
    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
    public DataSource getDataSource() {
        return dataSource;
    }
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    public SqlSession openSession(){
        return sqlSessionFactory.openSession();
    }
    
    protected Configuration initMybatisConfiguration(Environment environment, Reader reader, Properties properties) {
        XMLConfigBuilder parser = new XMLConfigBuilder(reader,"", properties);
        Configuration configuration = parser.parse();
        configuration.setEnvironment(environment);
        
       // initMybatisTypeHandlers(configuration);
       // initCustomMybatisMappers(configuration);
        
      // configuration = parseMybatisConfiguration(configuration, parser);
        return configuration;
    }
    
    private void initSessionFactory(){
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("default", transactionFactory, dataSource);
        Properties properties = new Properties();
        try {
            Reader reader = Resources.getResourceAsReader(mapperPath);
            Configuration configuration = initMybatisConfiguration(environment, reader, properties);
            sqlSessionFactory = new DefaultSqlSessionFactory(configuration);
        } catch (IOException e) {
            logger.error("==>initSessionFactory Error,mapperPath dsnot exsit");
            e.printStackTrace();
        }
    }
    public void afterPropertiesSet() throws Exception {
        initSessionFactory();
    }
    
}
