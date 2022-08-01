# MOSpring — Spring Service Cli
基于Spring框架及其他热门框架搭建的Web服务快速开发手脚架

**本项目已经整合了Web服务常用的框架，利用本项目可以快速搭建并启动Web服务**

**同时也不用关心大多的配置，本项目已经完成了许多必要的配置，可以把大多数的精力都放在业务开发上**

**对于项目中的每个配置、配置、功能点都有较为详细的注释用以说明，方便查看和学习**

# 优势
- 实现了与用户登陆、注册和删除功能，可以快速复用
- 实现了异常路由、自定义异常类和异常枚举类，可以通过枚举类快速构建异常响应
- 支持响应序列化，对返回的实体对象进行序列化处理，隐藏敏感数据
- 支持全局异常和全局响应的拦截和处理
- 支持多环境配置（开发环境/生产环境）
- Slf4j作为日志门面，Logback作为日志实现
- 整合Swagger3，自动生成API测试文档
- 整合Spring Security，可配置CSRF-Token来防御CSRF攻击

# 搭建环境和推荐工具
- 编码工具：IntelliJ IDEA
- Spring version ： 2.7.1
- JDK version : 1.8.0
- Maven version：2.7.1

**本项目理论上兼容多个版本的环境，但可能需要修改下配置文件，为避免出现难以定位的错误，建议使用推荐的环境和版本来使用本项目**

# 整合框架
依赖管理工具：Maven —— 依赖详见文件Pom.xml
- 安全认证 ： Spring Security
- 视图层：Thymeleaf
- 缓存层：Redis
- 持久层
  - 数据库映射：JPA
  - 数据库连接：JDBC
  - MySQL-Connector-Java
- 工具层
  - JSON解析：alibaba - fastjson
  - 数据构建：lombok
  - API测试：spring fox - Swagger3
  - 日志记录：logging
- 单元测试：spring - test

# 目录结构简介
- config：配置信息层
- controller：路由控制层
- dao：数据访问层
- entity：实体类
- enumer：自定义枚举类
- exception:自定义异常类
- serializer：数据响应序列化
- service：数据服务层
- MoSpringApplication：工程启动类

# 日志记录
- SpringBoot使用的是Slf4j作为日志门面，Logback
- Logback也能实现定制化，SpringBoot推荐将配置文件名称命名为`logback-spring.xml`表示这是SpringBoot下Logback专用的配置
- 在本项目中，已在项目根目录创建`logback-spring.xml`并实现了对Logback的定制化，可以按需修改文件

# 配置文件
- Maven配置：[pom.xml](./pom.xml)
- 全局配置：[application.yml](./src/main/resources/application.yml)
- 开发环境配置：[application-dev.yml](./src/main/resources/application-dev.yml)
- 生产环境配置：[application-prod.yml](./src/main/resources/application-prod.yml)
## 多环境配置
本项目已支持了多环境配置，通过将Maven中的`environment`属性，传递给SpringBoot的配置文件，在构建时替换为对应的值：
```yaml
spring:
  profiles:
    active: '@environment@'  #注意YAML配置文件需要加单引号，否则会报错
```
这样，根据我们Maven环境的切换，SpringBoot的配置文件也会进行对应的切换。
下面主要列举注释的配置内容
```yaml
# resources/application.yml
server:
  # 服务端口
  port: 8080
  servlet:
    # JSON防止中文乱码
    encoding:
      force-response: true
    # 为所有Controller添加统一访问前缀
    context-path: /api
spring:
  # 多环境配置
  profiles:
    # 从Maven配置中读取选择的环境变量（开发环境：dev/生产环境：prod）
    active: '@environment@'  # 注意YAML配置文件需要加单引号，否则会报错
  # 持久层数据源配置
  datasource:
    url: 'jdbc:mysql://localhost:3306/{数据库名称}'
    username: '{数据库用户名}'
    password: {数据库密码}
    driver-class-name: com.mysql.cj.jdbc.Driver
  # 缓存层数据源配置
  redis:
    # Redis服务器地址
    host: localhost
    # 端口
    port: 6379
    # 使用几号数据库
    database: 0
  # JPA 配置
  jpa:
    # 打印SQL语句
    show-sql: true
    # 数据库操作模式
    hibernate:
      ddl-auto: update
    open-in-view: true
  # Spring MVC 配置
  mvc:
    # SpringBoot 2.6以上版本修改了路径匹配规则，但是Swagger3还不支持
    # 这里换回之前的，不然启动会直接报错
    pathmatch:
      matching-strategy: ant_path_matcher
    # 静态资源配置
    static-path-pattern: /static/**
  thymeleaf:
    # 开发阶段，建议关闭thymeleaf的缓存
    cache: false
# Swagger开关
springfox:
  documentation:
    swagger-ui:
      enabled: false
# 解决因为日志等级为DEBUG时，启动项目报"CONDITIONS EVALUATION REPORT"的问题
logging:
  level:
    org:
      springframework:
        boot:
          autoconfigure: error
```
# 打包运行
当SpringBoot项目编写完成之后，只需要点击Maven生命周期中的`package`即可，它会自动将其打包为可直接运行的Jar包，第一次打包可能会花费一些时间下载部分依赖的源码一起打包进Jar文件。

我们发现在打包的过程中还会完整的将项目跑一遍进行测试，如果我们不想测试直接打包，可以手动使用以下命令：
```shell
mvn package  -DskipTests
```
打包后，我们会直接得到一个名为`mospring-0.0.1-SNAPSHOT.jar`的文件，在文件目录打开控制台窗口，输入命令：
```shell
java -jar mospring-0.0.1-SNAPSHOT.jar
```
输入后即可将SpringBoot项目运行在本地运行，如果手动关闭窗口会导致整个项目终止运行。