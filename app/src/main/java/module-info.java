module com.bbd.mvc {
    requires java.sql;
    requires spring.context;
    requires spring.boot.autoconfigure;
    requires spring.boot;
    requires modules;
    requires coffee;

    exports com.bbd.mvc;
    opens com.bbd.mvc;
}

//The equivalent Classpath is:
// app\target\classes;
// maven\repository\org\springframework\boot\spring-boot-autoconfigure\2.0.3.RELEASE\spring-boot-autoconfigure-2.0.3.RELEASE.jar;
// maven\repository\org\springframework\boot\spring-boot\2.0.3.RELEASE\spring-boot-2.0.3.RELEASE.jar;
// maven\repository\org\springframework\spring-core\5.0.7.RELEASE\spring-core-5.0.7.RELEASE.jar;
// maven\repository\org\springframework\spring-jcl\5.0.7.RELEASE\spring-jcl-5.0.7.RELEASE.jar;
// maven\repository\org\springframework\spring-context\5.0.7.RELEASE\spring-context-5.0.7.RELEASE.jar;
// maven\repository\org\springframework\spring-aop\5.0.7.RELEASE\spring-aop-5.0.7.RELEASE.jar;
// maven\repository\org\springframework\spring-beans\5.0.7.RELEASE\spring-beans-5.0.7.RELEASE.jar;
// maven\repository\org\springframework\spring-expression\5.0.7.RELEASE\spring-expression-5.0.7.RELEASE.jar;
// modules\target\classes;
// maven\repository\org\springframework\boot\spring-boot-starter-web\2.0.3.RELEASE\spring-boot-starter-web-2.0.3.RELEASE.jar;
// maven\repository\org\springframework\boot\spring-boot-starter\2.0.3.RELEASE\spring-boot-starter-2.0.3.RELEASE.jar;
// maven\repository\org\springframework\boot\spring-boot-starter-logging\2.0.3.RELEASE\spring-boot-starter-logging-2.0.3.RELEASE.jar;
// maven\repository\ch\qos\logback\logback-classic\1.2.3\logback-classic-1.2.3.jar;
// maven\repository\ch\qos\logback\logback-core\1.2.3\logback-core-1.2.3.jar;
// maven\repository\org\slf4j\slf4j-api\1.7.25\slf4j-api-1.7.25.jar;
// maven\repository\org\apache\logging\log4j\log4j-to-slf4j\2.10.0\log4j-to-slf4j-2.10.0.jar;
// maven\repository\org\apache\logging\log4j\log4j-api\2.10.0\log4j-api-2.10.0.jar;
// maven\repository\org\slf4j\jul-to-slf4j\1.7.25\jul-to-slf4j-1.7.25.jar;
// maven\repository\javax\annotation\javax.annotation-api\1.3.2\javax.annotation-api-1.3.2.jar;
// maven\repository\org\yaml\snakeyaml\1.19\snakeyaml-1.19.jar;
// maven\repository\org\springframework\boot\spring-boot-starter-json\2.0.3.RELEASE\spring-boot-starter-json-2.0.3.RELEASE.jar;
// maven\repository\com\fasterxml\jackson\core\jackson-databind\2.9.6\jackson-databind-2.9.6.jar;
// maven\repository\com\fasterxml\jackson\core\jackson-annotations\2.9.0\jackson-annotations-2.9.0.jar;
// maven\repository\com\fasterxml\jackson\core\jackson-core\2.9.6\jackson-core-2.9.6.jar;
// maven\repository\com\fasterxml\jackson\datatype\jackson-datatype-jdk8\2.9.6\jackson-datatype-jdk8-2.9.6.jar;
// maven\repository\com\fasterxml\jackson\datatype\jackson-datatype-jsr310\2.9.6\jackson-datatype-jsr310-2.9.6.jar;
// maven\repository\com\fasterxml\jackson\module\jackson-module-parameter-names\2.9.6\jackson-module-parameter-names-2.9.6.jar;
// maven\repository\org\springframework\boot\spring-boot-starter-tomcat\2.0.3.RELEASE\spring-boot-starter-tomcat-2.0.3.RELEASE.jar;
// maven\repository\org\apache\tomcat\embed\tomcat-embed-core\8.5.31\tomcat-embed-core-8.5.31.jar;
// maven\repository\org\apache\tomcat\embed\tomcat-embed-el\8.5.31\tomcat-embed-el-8.5.31.jar;
// maven\repository\org\apache\tomcat\embed\tomcat-embed-websocket\8.5.31\tomcat-embed-websocket-8.5.31.jar;
// maven\repository\org\hibernate\validator\hibernate-validator\6.0.10.Final\hibernate-validator-6.0.10.Final.jar;
// maven\repository\javax\validation\validation-api\2.0.1.Final\validation-api-2.0.1.Final.jar;
// maven\repository\org\jboss\logging\jboss-logging\3.3.2.Final\jboss-logging-3.3.2.Final.jar;
// maven\repository\com\fasterxml\classmate\1.3.4\classmate-1.3.4.jar;
// maven\repository\org\springframework\spring-web\5.0.7.RELEASE\spring-web-5.0.7.RELEASE.jar;
// maven\repository\org\springframework\spring-webmvc\5.0.7.RELEASE\spring-webmvc-5.0.7.RELEASE.jar;
// coffee\target\classes
