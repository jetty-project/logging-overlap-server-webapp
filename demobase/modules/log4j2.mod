# DO NOT EDIT - See: https://www.eclipse.org/jetty/documentation/current/startup-modules.html

[description]
Configure jetty logging to use Log4j Logging
SLF4J is used as the core logging mechanism.

[tags]
logging

[depends]
slf4j

[lib]
lib/log4j/disruptor-*.jar
lib/log4j/log4j-api-*.jar
lib/log4j/log4j-core-*.jar
lib/log4j/log4j-slf4j-impl-*.jar

[provides]
logging
