package com.ybj.crawler.Learn.ThinkingInJava.Ch14;

import org.testng.annotations.Test;

/**
 * 类加载器
 *
 * @author yuanbaojian
 * @return
 * @date 2020/3/11
 * @time 20:45
 */
public class ClassLoaderDemo {

    @Test
    public void test() throws ClassNotFoundException {
        // 获取系统类加载器的  父类加载器 --> 扩展类加载器
        ClassLoader parent = ClassLoader.getSystemClassLoader();
        System.out.println("parent = " + parent);

        // 获取扩展类加载器的  父类加载器  --> 根加载器(c/c++)
        ClassLoader parent1 = parent.getParent();
        System.out.println("parent1 = " + parent1);

        // 测试当前类 是哪个加载器加载的   是app
        ClassLoader classLoader = Class.forName("com.ybj.crawler.Learn.ThinkingInJava.Ch14.ClassLoaderDemo").getClassLoader();
        System.out.println("当前类使用的加载器   " + classLoader);

        // 测试jdk内置类  是谁加载的。  是null？
        ClassLoader classLoader1 = Class.forName("java.lang.Object").getClassLoader();
        System.out.println("jdk使用的加载器 " + classLoader1);


        // 获得系统加载器可以加载的路径
        System.out.println("加载路径内容 = " + System.getProperty("java.class.path"));

        /**
         *  系统可以加载的路径内容
         *  jdk 下的jre
         *  项目target 的class
         *  maven依赖
         * C:\Program Files\JetBrains\IntelliJ IDEA 2019.3.1\lib\idea_rt.jar;
         * C:\Program Files\JetBrains\IntelliJ IDEA 2019.3.1\plugins\testng\lib\testng-rt.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\charsets.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\deploy.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\access-bridge-64.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\cldrdata.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\dnsns.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\jaccess.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\jfxrt.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\localedata.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\nashorn.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\sunec.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\sunjce_provider.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\sunmscapi.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\sunpkcs11.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\ext\zipfs.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\javaws.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\jce.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\jfr.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\jfxswt.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\jsse.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\management-agent.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\plugin.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\resources.jar;
         * C:\Program Files\Java\jdk1.8.0_131\jre\lib\rt.jar;
         * C:\ideaWorkplace\helloWorldV3\system-crawler\target\test-classes;
         * C:\ideaWorkplace\helloWorldV3\system-crawler\target\classes;
         * I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\aliyun\aliyun-java-sdk-core\4.5.0\aliyun-java-sdk-core-4.5.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\google\code\gson\gson\2.8.6\gson-2.8.6.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\httpcomponents\httpcore\4.4.12\httpcore-4.4.12.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\commons-logging\commons-logging\1.2\commons-logging-1.2.jar;
         * I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\javax\xml\bind\jaxb-api\2.3.1\jaxb-api-2.3.1.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\javax\activation\javax.activation-api\1.2.0\javax.activation-api-1.2.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\jacoco\org.jacoco.agent\0.8.5\org.jacoco.agent-0.8.5-runtime.jar;
         * I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\ini4j\ini4j\0.5.4\ini4j-0.5.4.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\io\opentracing\opentracing-api\0.33.0\opentracing-api-0.33.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\io\opentracing\opentracing-util\0.33.0\opentracing-util-0.33.0.jar;
         * I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\io\opentracing\opentracing-noop\0.33.0\opentracing-noop-0.33.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\aliyun\oss\aliyun-sdk-oss\3.8.1\aliyun-sdk-oss-3.8.1.jar;
         * I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\jdom\jdom\1.1\jdom-1.1.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\codehaus\jettison\jettison\1.1\jettison-1.1.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\stax\stax-api\1.0.1\stax-api-1.0.1.jar;
         * I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\aliyun\aliyun-java-sdk-ram\3.0.0\aliyun-java-sdk-ram-3.0.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\aliyun\aliyun-java-sdk-sts\3.0.0\aliyun-java-sdk-sts-3.0.0.jar;
         * I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\aliyun\aliyun-java-sdk-ecs\4.2.0\aliyun-java-sdk-ecs-4.2.0.jar;C:\ideaWorkplace\helloWorldV3\system-utils\target\classes;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\baomidou\mybatis-plus-generator\3.3.0\mybatis-plus-generator-3.3.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\baomidou\mybatis-plus-extension\3.3.0\mybatis-plus-extension-3.3.0.jar;
         * I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\baomidou\mybatis-plus-core\3.3.0\mybatis-plus-core-3.3.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\baomidou\mybatis-plus-annotation\3.3.0\mybatis-plus-annotation-3.3.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\github\jsqlparser\jsqlparser\3.1\jsqlparser-3.1.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\mybatis\mybatis\3.5.3\mybatis-3.5.3.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\mybatis\mybatis-spring\2.0.3\mybatis-spring-2.0.3.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\velocity\velocity-engine-core\2.1\velocity-engine-core-2.1.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\boot\spring-boot-devtools\2.2.2.RELEASE\spring-boot-devtools-2.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\boot\spring-boot\2.2.2.RELEASE\spring-boot-2.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\spring-context\5.2.2.RELEASE\spring-context-5.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\boot\spring-boot-autoconfigure\2.2.2.RELEASE\spring-boot-autoconfigure-2.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\projectlombok\lombok\1.18.6\lombok-1.18.6.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\json\json\20190722\json-20190722.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\junit\junit\4.12\junit-4.12.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\hamcrest\hamcrest-core\2.1\hamcrest-core-2.1.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\shiro\shiro-core\1.4.2\shiro-core-1.4.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\shiro\shiro-lang\1.4.2\shiro-lang-1.4.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\shiro\shiro-cache\1.4.2\shiro-cache-1.4.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\shiro\shiro-crypto-hash\1.4.2\shiro-crypto-hash-1.4.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\shiro\shiro-crypto-core\1.4.2\shiro-crypto-core-1.4.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\shiro\shiro-crypto-cipher\1.4.2\shiro-crypto-cipher-1.4.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\shiro\shiro-config-core\1.4.2\shiro-config-core-1.4.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\shiro\shiro-config-ogdl\1.4.2\shiro-config-ogdl-1.4.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\shiro\shiro-event\1.4.2\shiro-event-1.4.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\shiro\shiro-web\1.4.2\shiro-web-1.4.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\shiro\shiro-spring\1.4.2\shiro-spring-1.4.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\shiro\shiro-ehcache\1.4.2\shiro-ehcache-1.4.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\shiro\shiro-cas\1.4.2\shiro-cas-1.4.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\jasig\cas\client\cas-client-core\3.2.2\cas-client-core-3.2.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\alibaba\druid\1.1.20\druid-1.1.20.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\alibaba\fastjson\1.2.58\fastjson-1.2.58.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\fasterxml\jackson\core\jackson-core\2.9.4\jackson-core-2.9.4.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\fasterxml\jackson\core\jackson-databind\2.9.4\jackson-databind-2.9.4.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\fasterxml\jackson\core\jackson-annotations\2.9.4\jackson-annotations-2.9.4.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\fasterxml\classmate\1.4.0\classmate-1.4.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\javax\servlet\javax.servlet-api\3.1.0\javax.servlet-api-3.1.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\commons\commons-lang3\3.9\commons-lang3-3.9.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\commons\commons-text\1.8\commons-text-1.8.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\log4j\log4j\1.2.17\log4j-1.2.17.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\slf4j\slf4j-api\1.7.30\slf4j-api-1.7.30.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\slf4j\slf4j-log4j12\1.7.30\slf4j-log4j12-1.7.30.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\redis\clients\jedis\3.1.0\jedis-3.1.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\commons\commons-pool2\2.7.0\commons-pool2-2.7.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\quartz-scheduler\quartz\2.3.2\quartz-2.3.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\mchange\mchange-commons-java\0.2.15\mchange-commons-java-0.2.15.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\ant\ant\1.10.5\ant-1.10.5.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\ant\ant-launcher\1.10.5\ant-launcher-1.10.5.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\poi\poi\4.1.0\poi-4.1.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\commons-codec\commons-codec\1.13\commons-codec-1.13.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\commons\commons-collections4\4.3\commons-collections4-4.3.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\commons\commons-math3\3.6.1\commons-math3-3.6.1.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\poi\poi-ooxml\4.1.0\poi-ooxml-4.1.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\poi\poi-ooxml-schemas\4.1.0\poi-ooxml-schemas-4.1.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\xmlbeans\xmlbeans\3.1.0\xmlbeans-3.1.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\commons\commons-compress\1.18\commons-compress-1.18.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\github\virtuald\curvesapi\1.06\curvesapi-1.06.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\github\junrar\junrar\4.0.0\junrar-4.0.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\commons\commons-vfs2\2.2\commons-vfs2-2.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\httpcomponents\httpclient\4.5.9\httpclient-4.5.9.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\httpcomponents\httpclient-cache\4.5.9\httpclient-cache-4.5.9.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\httpcomponents\httpmime\4.5.9\httpmime-4.5.9.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\jayway\jsonpath\json-path\2.4.0\json-path-2.4.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\net\minidev\json-smart\2.3\json-smart-2.3.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\net\minidev\accessors-smart\1.2\accessors-smart-1.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\ow2\asm\asm\5.0.4\asm-5.0.4.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\commons-fileupload\commons-fileupload\1.4\commons-fileupload-1.4.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\commons-io\commons-io\2.2\commons-io-2.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\commons-collections\commons-collections\3.2.2\commons-collections-3.2.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\net\sf\dozer\dozer\5.3.2\dozer-5.3.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\commons-beanutils\commons-beanutils\1.8.3\commons-beanutils-1.8.3.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\commons-lang\commons-lang\2.5\commons-lang-2.5.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\net\sf\ezmorph\ezmorph\1.0.6\ezmorph-1.0.6.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\jaxen\jaxen\1.2.0\jaxen-1.2.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\dom4j\dom4j\1.6.1\dom4j-1.6.1.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\xml-apis\xml-apis\1.0.b2\xml-apis-1.0.b2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\testng\testng\7.1.0\testng-7.1.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\beust\jcommander\1.72\jcommander-1.72.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\google\inject\guice\4.1.0\guice-4.1.0-no_aop.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\javax\inject\javax.inject\1\javax.inject-1.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\aopalliance\aopalliance\1.0\aopalliance-1.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\yaml\snakeyaml\1.25\snakeyaml-1.25.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\jsoup\jsoup\1.12.1\jsoup-1.12.1.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\boot\spring-boot-starter-data-redis\2.2.4.RELEASE\spring-boot-starter-data-redis-2.2.4.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\data\spring-data-redis\2.2.3.RELEASE\spring-data-redis-2.2.3.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\data\spring-data-keyvalue\2.2.3.RELEASE\spring-data-keyvalue-2.2.3.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\data\spring-data-commons\2.2.3.RELEASE\spring-data-commons-2.2.3.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\spring-tx\5.2.2.RELEASE\spring-tx-5.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\spring-oxm\5.2.2.RELEASE\spring-oxm-5.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\spring-aop\5.2.2.RELEASE\spring-aop-5.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\spring-context-support\5.2.2.RELEASE\spring-context-support-5.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\io\lettuce\lettuce-core\5.2.1.RELEASE\lettuce-core-5.2.1.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\io\netty\netty-common\4.1.43.Final\netty-common-4.1.43.Final.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\io\netty\netty-handler\4.1.43.Final\netty-handler-4.1.43.Final.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\io\netty\netty-buffer\4.1.43.Final\netty-buffer-4.1.43.Final.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\io\netty\netty-codec\4.1.43.Final\netty-codec-4.1.43.Final.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\io\netty\netty-transport\4.1.43.Final\netty-transport-4.1.43.Final.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\io\netty\netty-resolver\4.1.43.Final\netty-resolver-4.1.43.Final.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\io\projectreactor\reactor-core\3.3.1.RELEASE\reactor-core-3.3.1.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\reactivestreams\reactive-streams\1.0.3\reactive-streams-1.0.3.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\net\sf\ehcache\ehcache-core\2.4.5\ehcache-core-2.4.5.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\boot\spring-boot-starter-test\2.2.2.RELEASE\spring-boot-starter-test-2.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\boot\spring-boot-test\2.2.2.RELEASE\spring-boot-test-2.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\boot\spring-boot-test-autoconfigure\2.2.2.RELEASE\spring-boot-test-autoconfigure-2.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\jakarta\xml\bind\jakarta.xml.bind-api\2.3.2\jakarta.xml.bind-api-2.3.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\jakarta\activation\jakarta.activation-api\1.2.1\jakarta.activation-api-1.2.1.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\junit\jupiter\junit-jupiter\5.5.2\junit-jupiter-5.5.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\junit\jupiter\junit-jupiter-api\5.5.2\junit-jupiter-api-5.5.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apiguardian\apiguardian-api\1.1.0\apiguardian-api-1.1.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\opentest4j\opentest4j\1.2.0\opentest4j-1.2.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\junit\platform\junit-platform-commons\1.5.2\junit-platform-commons-1.5.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\junit\jupiter\junit-jupiter-params\5.5.2\junit-jupiter-params-5.5.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\junit\jupiter\junit-jupiter-engine\5.5.2\junit-jupiter-engine-5.5.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\junit\platform\junit-platform-engine\1.5.2\junit-platform-engine-1.5.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\mockito\mockito-junit-jupiter\3.1.0\mockito-junit-jupiter-3.1.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\assertj\assertj-core\3.13.2\assertj-core-3.13.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\hamcrest\hamcrest\2.1\hamcrest-2.1.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\mockito\mockito-core\3.1.0\mockito-core-3.1.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\net\bytebuddy\byte-buddy\1.10.4\byte-buddy-1.10.4.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\net\bytebuddy\byte-buddy-agent\1.10.4\byte-buddy-agent-1.10.4.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\objenesis\objenesis\2.6\objenesis-2.6.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\skyscreamer\jsonassert\1.5.0\jsonassert-1.5.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\vaadin\external\google\android-json\0.0.20131108.vaadin1\android-json-0.0.20131108.vaadin1.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\spring-core\5.2.2.RELEASE\spring-core-5.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\spring-jcl\5.2.2.RELEASE\spring-jcl-5.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\spring-test\5.2.2.RELEASE\spring-test-5.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\xmlunit\xmlunit-core\2.6.3\xmlunit-core-2.6.3.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\boot\spring-boot-starter\2.2.2.RELEASE\spring-boot-starter-2.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\jakarta\annotation\jakarta.annotation-api\1.3.5\jakarta.annotation-api-1.3.5.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\baomidou\mybatis-plus-boot-starter\3.3.0\mybatis-plus-boot-starter-3.3.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\baomidou\mybatis-plus\3.3.0\mybatis-plus-3.3.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\boot\spring-boot-starter-jdbc\2.2.2.RELEASE\spring-boot-starter-jdbc-2.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\zaxxer\HikariCP\3.4.1\HikariCP-3.4.1.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\spring-jdbc\5.2.2.RELEASE\spring-jdbc-5.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\mysql\mysql-connector-java\8.0.18\mysql-connector-java-8.0.18.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\github\xiaoymin\swagger-bootstrap-ui\1.9.3\swagger-bootstrap-ui-1.9.3.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\boot\spring-boot-starter-web\2.2.2.RELEASE\spring-boot-starter-web-2.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\boot\spring-boot-starter-json\2.2.2.RELEASE\spring-boot-starter-json-2.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\fasterxml\jackson\datatype\jackson-datatype-jdk8\2.9.4\jackson-datatype-jdk8-2.9.4.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.9.4\jackson-datatype-jsr310-2.9.4.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\fasterxml\jackson\module\jackson-module-parameter-names\2.9.4\jackson-module-parameter-names-2.9.4.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\boot\spring-boot-starter-validation\2.2.2.RELEASE\spring-boot-starter-validation-2.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\jakarta\validation\jakarta.validation-api\2.0.1\jakarta.validation-api-2.0.1.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\hibernate\validator\hibernate-validator\6.0.18.Final\hibernate-validator-6.0.18.Final.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\jboss\logging\jboss-logging\3.4.1.Final\jboss-logging-3.4.1.Final.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\spring-web\5.2.2.RELEASE\spring-web-5.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\spring-beans\5.2.2.RELEASE\spring-beans-5.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\spring-webmvc\5.2.2.RELEASE\spring-webmvc-5.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\spring-expression\5.2.2.RELEASE\spring-expression-5.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\p6spy\p6spy\3.8.7\p6spy-3.8.7.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\io\springfox\springfox-swagger2\2.9.2\springfox-swagger2-2.9.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\io\swagger\swagger-annotations\1.5.20\swagger-annotations-1.5.20.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\io\swagger\swagger-models\1.5.20\swagger-models-1.5.20.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\io\springfox\springfox-spi\2.9.2\springfox-spi-2.9.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\io\springfox\springfox-core\2.9.2\springfox-core-2.9.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\io\springfox\springfox-schema\2.9.2\springfox-schema-2.9.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\io\springfox\springfox-swagger-common\2.9.2\springfox-swagger-common-2.9.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\io\springfox\springfox-spring-web\2.9.2\springfox-spring-web-2.9.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\com\google\guava\guava\20.0\guava-20.0.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\plugin\spring-plugin-core\1.2.0.RELEASE\spring-plugin-core-1.2.0.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\plugin\spring-plugin-metadata\1.2.0.RELEASE\spring-plugin-metadata-1.2.0.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\mapstruct\mapstruct\1.2.0.Final\mapstruct-1.2.0.Final.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\io\springfox\springfox-swagger-ui\2.9.2\springfox-swagger-ui-2.9.2.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\springframework\boot\spring-boot-starter-tomcat\2.2.2.RELEASE\spring-boot-starter-tomcat-2.2.2.RELEASE.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\tomcat\embed\tomcat-embed-core\9.0.29\tomcat-embed-core-9.0.29.jar;
         * I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\tomcat\embed\tomcat-embed-el\9.0.29\tomcat-embed-el-9.0.29.jar;I:\A001_development\apache-maven-3.6.0-bin\localWarehouse\org\apache\tomcat\embed\tomcat-embed-websocket\9.0.29\tomcat-embed-websocket-9.0.29.jar;C:\Program
         * C:\Program Files\JetBrains\IntelliJ IDEA 2019.3.1\plugins\testng\lib\jcommander-1.27.jar;
         * C:\Program Files\JetBrains\IntelliJ IDEA 2019.3.1\lib\idea_rt.jar
         */
    }
}
