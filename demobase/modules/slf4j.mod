# DO NOT EDIT - See: https://www.eclipse.org/jetty/documentation/current/startup-modules.html

[description]
Configures for Slf4j

[tags]
logging

[lib]
# The API
lib/slf4j/slf4j-api-*.jar
# Capture Commons Logging and route to SLF4J
lib/slf4j/jcl-over-slf4j-*.jar
# Capture Java Util Logging and route to SLF4J
lib/slf4j/jul-to-slf4j-*.jar
# Capture Log4j 1.x and route to SLF4J
lib/slf4j/log4j-over-slf4j-*.jar

[xml]
etc/JavaUtilLogging.xml
