# 06-SpringSecurity

## 环境准备

### 1）Maven 依赖

**==pom.xml==**

SpringMVC 相关

- `spring-webmvc`
- `servlet-api`
- `jsp-api`

SpringSecurity 相关

- `spring-security-web`
- `spring-security-config`
- `spring-security-taglibs`

数据库相关

- `druid`
- `mysql-connector-java`
- `spring-orm`

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>4.3.20.RELEASE</version>
</dependency>
<!-- 引入 Servlet 容器中相关依赖 -->
<dependency>
    <groupId>javax.servlet</groupId>
    <artifactId>servlet-api</artifactId>
    <version>2.5</version>
    <scope>provided</scope>
</dependency>
<!-- JSP 页面使用的依赖 -->
<dependency>
    <groupId>javax.servlet.jsp</groupId>
    <artifactId>jsp-api</artifactId>
    <version>2.1.3-b06</version>
    <scope>provided</scope>
</dependency>

<!-- SpringSecurity 对 Web 应用进行权限管理 -->
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-web</artifactId>
    <version>4.2.10.RELEASE</version>
</dependency>
<!-- SpringSecurity 配置 -->
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-config</artifactId>
    <version>4.2.10.RELEASE</version>
</dependency>
<!-- SpringSecurity 标签 -->
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-taglibs</artifactId>
    <version>4.2.10.RELEASE</version>
</dependency>

<!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid</artifactId>
    <version>1.1.12</version>
</dependency>
<!-- mysql 驱动 -->
<!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>5.1.47</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-orm</artifactId>
    <version>4.3.20.RELEASE</version>
</dependency>
```

### 2）web.xml

**==web.xml==**

- `DelegatingFilterProxy`
- `DispatcherServlet`

```xml
<filter>
    <filter-name>springSecurityFilterChain</filter-name>
    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>
<filter-mapping>
    <filter-name>springSecurityFilterChain</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
<servlet>
    <servlet-name>springDispatcherServlet</servlet-name>
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
    <init-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>classpath:spring-mvc.xml</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
</servlet>
<servlet-mapping>
    <servlet-name>springDispatcherServlet</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping>
```

### 3）SpringMVC 配置文件

**==spring-mvc.xml==**

- `<component-scan>`
- `InternalResourceViewResolver`
- `<mvc:annotation-driven/>`
- `<mvc:default-servlet-handler/>`

```xml
<context:component-scan base-package="com.vectorx.springsecurity"></context:component-scan>
<bean id="internalResourceViewResolver" class="org.springframework.web.servlet.view.InternalResourceViewResolver">
    <property name="prefix" value="/WEB-INF/views/"></property>
    <property name="suffix" value=".jsp"></property>
</bean>
<mvc:annotation-driven/>
<mvc:default-servlet-handler/>
```

### 4）配置类

```java
@Configuration
@EnableWebSecurity
public class WebAppSecurityConfig1 extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity security) throws Exception {}
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {}
}
```



## 1、放行首页和静态资源

- `antMatchers`：匹配 ant 风格路径
- `anyRequest`：任意请求
- `permitAll`：放行
- `authenticated`：需认证

```java
security.authorizeRequests()
        .antMatchers("/index.jsp") // 放行首页
        .permitAll()
        .antMatchers("/layui/**")  // 放行静态资源
        .permitAll()
        .and()
        .authorizeRequests()       
        .anyRequest()              // 其他请求需认证
        .authenticated()
```



## 2、用户登录

- `formLogin`：开启表单登录
- `loginPage`：指定登录页面
- `loginProcessingUrl`：指定登录请求 url
- `usernameParameter`：指定登录用户名参数
- `passwordParameter`：指定登录密码参数
- `defaultSuccessUrl`：默认登录成功页面

```java
security
    	// ...
    	.and()
        .formLogin()                           	// 开启表单登录
        .loginPage("/index.jsp")               	// 指定登录页面
        .loginProcessingUrl("/do/login.html"); 	// 指定登录请求url
        .usernameParameter("loginacct")   		// 指定登录用户名参数
        .passwordParameter("credential")  		// 指定登录密码参数
        .defaultSuccessUrl("/main.html") 		// 默认登录成功页面
```

- `withUser`、`password`：指定账号密码
- `roles`、`authorities`：指定角色权限

```java
auth
        .inMemoryAuthentication()
        .withUser("tom").password("1234")  // 指定账号密码
        .roles("ADMIN")                    // 指定角色
        .and()
        .withUser("jerry").password("1234")
        .authorities("SAVE", "EDIT")       // 授权
```



## 3、用户注销

### 1）禁用 CSRF

- `csrf`：启用 CSRF
- `disable`：禁用
- `logout`：启用注销功能
- `logoutUrl`：指定注销请求 url
- `logoutSuccessUrl`：指定注销请求成功后的url

```java
security        
        // ...
        .and()
        .csrf()                          // 禁用CSRF
        .disable()
        .logout()                        // 开启注销功能
        .logoutUrl("/do/logout.html")    // 指定注销请求url
        .logoutSuccessUrl("/index.jsp")  // 指定注销请求成功后的url
```

### 2）启用 CSRF

```java
security    
    	// ...
        .and()
        .logout()                        // 开启注销功能
        .logoutUrl("/do/logout.html")    // 指定注销请求url
        .logoutSuccessUrl("/index.jsp")  // 指定注销请求成功后的url
```



## 4、基于角色或权限进行权限控制

- `hasRole`：要求的角色
- `hasAuthority`：要求的权限

```java
security.authorizeRequests()
        .antMatchers("/layui/**", "/index.jsp")
        .permitAll()
        .antMatchers("/level1/**")  // 学徒才能访问level1下资源
        .hasRole("学徒")
        .antMatchers("/level2/**")  // 大师才能访问level2下资源
        .hasAuthority("SAVE")
        .antMatchers("/level3/**")  // 宗师才能访问level3下资源
        .hasRole("宗师")
        .anyRequest()
        .authenticated()
    	// ...
```

- `roles`：具有的角色
- `authorities`：具有的权限

```java
auth
        .inMemoryAuthentication()
        .withUser("tom").password("1234")
        .roles("ADMIN", "学徒", "宗师")
        .and()
        .withUser("jerry").password("1234")
        .authorities("SAVE", "EDIT")
```



## 5、自定义 403 页面

### 1）异常页面

- `exceptionHandling`：启用异常处理
- `accessDeniedPage`：访问被拒绝时前往的页面

```java
security
    	// ...
    	.and()
        .exceptionHandling()                        // 开启异常处理
        .accessDeniedPage("/to/no/auth/page.html")  // 访问被拒绝时前往的页面
```

### 2）自定义处理

- `exceptionHandling`：启用异常处理
- `accessDeniedHandler`：访问被拒绝时的处理

```java
security       
    	// ...
    	.and()
        .exceptionHandling()
        .accessDeniedHandler(new AccessDeniedHandler(){
            @Override
            public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                               AccessDeniedException e) throws IOException, ServletException {
                httpServletRequest.setAttribute("message", "不要不识好歹");
                httpServletRequest.getRequestDispatcher("/WEB-INF/views/no_auth.jsp")
                    .forward(httpServletRequest, httpServletResponse);
            }
         })
```



## 6、记住我

### 1）内存版

- `rememberMe`：启用“记住我”功能

```java
security  
    	// ...
    	.and()
    	.rememberMe() // 记住我
```

### 2）数据库版

- `tokenRepository`：启用令牌仓库

```java
security  
    	// ...
    	.and()
    	.rememberMe() 									// 记住我
    	.tokenRepository(persistentTokenRepository()) 	// 启用令牌仓库
```

**==persistentTokenRepository()==**

- `JdbcTokenRepositoryImpl`：Jdbc 令牌仓库实现
- `setDataSource`：设置数据源
- `setCreateTableOnStartup`：设置是否首次启动创建表

```java
@Autowired
private DataSource dataSource;

@Bean("jdbcTokenRepository")
public PersistentTokenRepository persistentTokenRepository(){
    JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
    jdbcTokenRepository.setDataSource(dataSource);
    jdbcTokenRepository.setCreateTableOnStartup(true); // 首次启用，自动创建表，后续需注释
    return jdbcTokenRepository;
}
```



## 7、查询数据库完成认证

**==注入 UserDetailsService==**

```java
@Autowired
private UserDetailsService userDetailsService;
```

- `userDetailsService`：启用数据库查询

```java
auth
    .userDetailsService(userDetailsService) // 数据库查询
```

### 1）实现一

**==AppUserDetailsService.java==**

```java
@Service
public class AppUserDetailsService implements UserDetailsService
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String sql = "select id,loginacct,userpswd,username,email,createtime from t_admin where username=?";
        Map<String, Object> map = jdbcTemplate.queryForMap(sql, username);
        String loginacct = map.get("loginacct").toString();
        String userpswd = map.get("userpswd").toString();
        final String preffix = "ROLE_";
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(preffix + "学徒"));
        authorityList.add(new SimpleGrantedAuthority(preffix + "宗师"));
        return new User(loginacct, userpswd, authorityList);
    }
}
```

### 1）实现二

**==AppUserDetailsService2.java==**

```java
@Service
public class AppUserDetailsService2 implements UserDetailsService
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String sql = "select id,loginacct,userpswd,username,email,createtime from t_admin where username=?";
        Admin admin = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Admin.class), username);
        String loginacct = admin.getLoginacct();
        String userpswd = admin.getUserpswd();
        final String preffix = "ROLE_";
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(preffix + "学徒", preffix + "宗师");
        return new User(loginacct, userpswd, authorityList);
    }
}
```



## 8、密码加密

### 1）不加盐

**==MyPasswordEncoder.java==**

```java
@Component
public class MyPasswordEncoder implements PasswordEncoder
{
    @Override
    public String encode(CharSequence rawPassword) {
        return md5(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return Objects.equals(md5(rawPassword), encodedPassword);
    }

    private String md5(CharSequence password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] input = String.valueOf(password).getBytes();
            byte[] output = digest.digest(input);
            String encrypted = new BigInteger(1, output).toString(16);
            return encrypted.toUpperCase();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
```

**==注入 MyPasswordEncoder==**

```java
@Autowired
private MyPasswordEncoder myPasswordEncoder;
```

- `passwordEncoder`：密码加密

```java
auth
    .userDetailsService(userDetailsService)
    .passwordEncoder(myPasswordEncoder) // 不加盐加密
```

### 2）加盐

**==注入 BCryptPasswordEncoder==**

```java
@Configuration
@EnableWebSecurity
@Import(BCryptPasswordEncoder.class)
public class WebAppSecurityConfig11 extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyPasswordEncoder myPasswordEncoder;
    // ...
}
```

- `passwordEncoder`：密码加密

```java
auth
    .userDetailsService(userDetailsService)
    .passwordEncoder(encoder) // 加盐加密
```



## 整体代码

```java
@Configuration
@EnableWebSecurity
@Import(BCryptPasswordEncoder.class)
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Bean("jdbcTokenRepository")
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(HttpSecurity security) throws Exception {
        security.authorizeRequests()
                .antMatchers("/layui/**", "/index.jsp")
                .permitAll()
                .antMatchers("/level1/**")
                .hasRole("学徒")
                .antMatchers("/level2/**")
                .hasRole("大师")
                .antMatchers("/level3/**")
                .hasRole("宗师")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/index.jsp")
                .loginProcessingUrl("/do/login.html")
                .usernameParameter("loginacct")
                .passwordParameter("credential")
                .defaultSuccessUrl("/main.html")
                .and()
                .logout()
                .logoutUrl("/do/logout.html")
                .logoutSuccessUrl("/index.jsp")
                .and()
                .exceptionHandling()
                .accessDeniedHandler(new AccessDeniedHandler()
                {
                    @Override
                    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                            AccessDeniedException e) throws IOException, ServletException {
                        httpServletRequest.setAttribute("message", "不要不识好歹");
                        httpServletRequest.getRequestDispatcher("/WEB-INF/views/no_auth.jsp")
                                .forward(httpServletRequest, httpServletResponse);
                    }
                })
                .and()
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(encoder) // 密码加盐
        ;
    }
}
```

