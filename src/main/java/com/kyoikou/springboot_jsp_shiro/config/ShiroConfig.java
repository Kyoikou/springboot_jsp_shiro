package com.kyoikou.springboot_jsp_shiro.config;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @Program springboot_jsp_shiro
 * @Description shiro相关配置
 * @Author bingqianli
 * @Create 2022-05-07
 */
@Configuration
public class ShiroConfig {

    /**
     * 1.创建shiroFilter ---> 负责拦截所有请求
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 给filter 注入 securityManager
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        HashMap<String, String> filterChainDefinitionMap = new HashMap<>();
        // 配置系统受限资源
        // authc 请求这个资源需要认证和授权
        //filterChainDefinitionMap.put("/index.jsp", "authc");

        // 所有url都必须认证通过才可以访问
        filterChainDefinitionMap.put("/**", "user");

        // 配置系统公共资源

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        // 默认认证界面路径
        shiroFilterFactoryBean.setLoginUrl("/login.jsp");

        return shiroFilterFactoryBean;
    }

    /**
     * 2.创建安全管理器
     */
    @Bean
    public DefaultWebSecurityManager getDefaultSecurityManager(ShiroRealm shiroRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        // 给安全器注入realm
        defaultWebSecurityManager.setRealm(shiroRealm);



        return defaultWebSecurityManager;
    }

    /**
     * 3.创建自定义的realm
     */
    @Bean
    public ShiroRealm getShiroRealm(){
        return new ShiroRealm();
    }
}