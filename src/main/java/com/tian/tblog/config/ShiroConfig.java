package com.tian.tblog.config;

import com.tian.tblog.bean.TblogRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    // 1.创建 shiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean(); // 创建 ShiroFilterFactoryBean
        shiroFilterFactoryBean.setSecurityManager(securityManager); // 设置安全管理器
        shiroFilterFactoryBean.setLoginUrl("/manager/login"); // 设置默认认证路径
        // 配置拦截路径
        Map<String, String> rules =  new HashMap<>();
        rules.put("/**", "anon");
//        rules.put("/manager/login", "anon");
//        rules.put("/manager/register", "anon");
//        rules.put("/manager/checkLogin", "anon");
//        rules.put("/manager/update", "anon");
//        rules.put("/article/addArticle", "anon");
//        rules.put("/article/getArticle", "anon");
//        rules.put("/article/getArticleList", "anon");
//        rules.put("/article/getPublishedArticle", "anon");
//        rules.put("/article/getUnpublishedArticle", "anon");
//        rules.put("/article/getLastArticle", "anon");
//        rules.put("/articleCategory/getCategoryList", "anon");
//        rules.put("/articleCategory/getCategory", "anon");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(rules);
        return shiroFilterFactoryBean;
    }

    // 2.创建 Web 安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    // 3.自定义 Realm
    @Bean
    public Realm getRealm(){
        TblogRealm realm = new TblogRealm();
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher("md5");
        hashedCredentialsMatcher.setHashIterations(1024);
        realm.setCredentialsMatcher(hashedCredentialsMatcher);
        return realm;
    }
}
