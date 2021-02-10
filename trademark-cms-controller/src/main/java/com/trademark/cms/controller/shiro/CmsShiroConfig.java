package com.trademark.cms.controller.shiro;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.servlet.KaptchaServlet;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/12/11.
 */
@Configuration
@PropertySource("classpath:tk-cms-redis.properties")
public class CmsShiroConfig {

    @Value("${tk.redis.host}")
    private String host;
    @Value("${tk.redis.port}")
    private int port;
    @Value("${tk.redis.timeout}")
    private int timeout;
    @Value("${tk.redis.password}")
    private String password;

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        //注意过滤器配置顺序 不能颠倒
        //配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了，登出后跳转配置的loginUrl
        filterChainDefinitionMap.put("/admin/logout", "logout");
        // 配置不会被拦截的链接 顺序判断
        filterChainDefinitionMap.put("/admin/doLogin", "anon");
        // 验证码
        filterChainDefinitionMap.put(CmsShiroConstant.CAPTCHA_KAPTCHA_PATH, "anon");
        filterChainDefinitionMap.put("/admin/getCaptcha", "anon");
        filterChainDefinitionMap.put("/**", "authc");
        //配置shiro默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/admin/doLogin");
        // 登录成功后要跳转的链接
        // shiroFilterFactoryBean.setSuccessUrl("/index");
        // 未授权界面;
        // shiroFilterFactoryBean.setUnauthorizedUrl("/403");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        //shiroFilterFactoryBean.getFilters().put("captchaValidate", captchaValidate());
        return shiroFilterFactoryBean;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * ）
     *
     * @return
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("md5");//散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashIterations(2);//散列的次数，比如散列两次，相当于 md5(md5(""));
        return hashedCredentialsMatcher;
    }

    @Bean
    public CmsShiroRealm CmsShiroRealm() {
        CmsShiroRealm CmsShiroRealm = new CmsShiroRealm();
        //CmsShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return CmsShiroRealm;
    }


    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(CmsShiroRealm());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        return securityManager;
    }

    //自定义sessionManager
    @Bean
    public SessionManager sessionManager() {
        CmsSessionManager sessionManager = new CmsSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }

    /**
     * 配置shiro redisManager
     * <p>
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host + ":" + port);
        /*redisManager.setPort(port);
        redisManager.setExpire(1800);// 配置缓存过期时间*/
        redisManager.setTimeout(timeout);
        redisManager.setPassword(password);
        return redisManager;
    }

    /**
     * cacheManager 缓存 redis实现
     * <p>
     * 使用的是shiro-redis开源插件
     *
     * @return
     */
    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * <p>
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 注册全局异常处理
     *
     * @return
     */
    @Bean(name = "exceptionHandler")
    public HandlerExceptionResolver handlerExceptionResolver() {
        return null;//new MyExceptionHandler();
    }

    @Bean
    public ServletRegistrationBean<KaptchaServlet> kaptchaServlet() {
        ServletRegistrationBean<KaptchaServlet> registrationBean = new ServletRegistrationBean<>(new KaptchaServlet(), CmsShiroConstant.CAPTCHA_KAPTCHA_PATH);

        registrationBean.addInitParameter(Constants.KAPTCHA_SESSION_CONFIG_KEY, Constants.KAPTCHA_SESSION_KEY);
        //宽度
        registrationBean.addInitParameter(Constants.KAPTCHA_IMAGE_WIDTH, "140");
        //高度
        registrationBean.addInitParameter(Constants.KAPTCHA_IMAGE_HEIGHT, "60");
        //字体大小
        registrationBean.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, "50");
        //无边框
        registrationBean.addInitParameter(Constants.KAPTCHA_BORDER, "no");
        //文字颜色
        registrationBean.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, "blue");
        //噪点
        registrationBean.addInitParameter(Constants.KAPTCHA_NOISE_COLOR, "red");
        //长度
        registrationBean.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, "4");
        //字符间距
        registrationBean.addInitParameter(Constants.KAPTCHA_TEXTPRODUCER_CHAR_SPACE, "6");

        //可以设置很多属性,具体看com.google.code.kaptcha.Constants
//      kaptcha.border  是否有边框  默认为true  我们可以自己设置yes，no
//      kaptcha.border.color   边框颜色   默认为Color.BLACK
//      kaptcha.border.thickness  边框粗细度  默认为1
//      kaptcha.producer.impl   验证码生成器  默认为DefaultKaptcha
//      kaptcha.textproducer.impl   验证码文本生成器  默认为DefaultTextCreator
//      kaptcha.textproducer.char.string   验证码文本字符内容范围  默认为abcde2345678gfynmnpwx
//      kaptcha.textproducer.char.length   验证码文本字符长度  默认为5
//      kaptcha.textproducer.font.names    验证码文本字体样式  默认为new Font("Arial", 1, fontSize), new Font("Courier", 1, fontSize)
//      kaptcha.textproducer.font.size   验证码文本字符大小  默认为40
//      kaptcha.textproducer.font.color  验证码文本字符颜色  默认为Color.BLACK
//      kaptcha.textproducer.char.space  验证码文本字符间距  默认为2
//      kaptcha.noise.impl    验证码噪点生成对象  默认为DefaultNoise
//      kaptcha.noise.color   验证码噪点颜色   默认为Color.BLACK
//      kaptcha.obscurificator.impl   验证码样式引擎  默认为WaterRipple
//      kaptcha.word.impl   验证码文本字符渲染   默认为DefaultWordRenderer
//      kaptcha.background.impl   验证码背景生成器   默认为DefaultBackground
//      kaptcha.background.clear.from   验证码背景颜色渐进   默认为Color.LIGHT_GRAY
//      kaptcha.background.clear.to   验证码背景颜色渐进   默认为Color.WHITE
//      kaptcha.image.width   验证码图片宽度  默认为200
//      kaptcha.image.height  验证码图片高度  默认为50
        return registrationBean;
    }
}