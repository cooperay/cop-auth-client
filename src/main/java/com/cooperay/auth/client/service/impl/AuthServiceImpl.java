package com.cooperay.auth.client.service.impl;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.cooperay.auth.client.entity.Account;
import com.cooperay.auth.client.service.AuthConfig;

/**
 * 验证服务类
 * @author Administrator
 *
 */
public class AuthServiceImpl {
    
    Logger logger = Logger.getLogger(AuthServiceImpl.class);
    
    private AuthConfig authConfig;

    public AuthConfig getAuthConfig() {
        return authConfig;
    }

    public void setAuthConfig(AuthConfig authConfig) {
        this.authConfig = authConfig;
    }
    
    public void test() {
        SqlSession session = authConfig.openSession();
        Account account = (Account)session.selectOne("com.cooperay.core.user.dao.impl.AccountDaoImpl.selectByPrimaryKey",new Long(1));
        System.out.println(account.getCname());
        System.out.println(session);
        session.close();
    }
    
}
