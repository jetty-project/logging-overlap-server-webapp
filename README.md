# Logging and ErrorHandling with Jetty

This demo project shows a few things that are of use for working with
logging libraries and error handling.

## Prerequisites for this project

You will need

1. Java 8+
2. Maven 3.5+
3. An unpacked `jetty-home` somewhere on your system (**not** within this cloned project!)

### To get a copy of `jetty-home`

Download it and unpack it.

``` shell
$ cd ~/tmp
$ curl -O http://central.maven.org/maven2/org/eclipse/jetty/jetty-home/9.4.25.v20191220/jetty-home-9.4.25.v20191220.tar.gz
$ tar -zxvf jetty-home-9.4.25.v20191220.tar.gz
```

Using the above directories as an example, that means your `$JETTY_HOME` is  
now `$HOME/tmp/jetty-home-9.4.25.v20191220/`

## To compile the project use 

``` shell
$ mvn clean install
```

The result is a webapp in `/webapps/root.war`

## To run webapp

``` shell
$ java -jar $JETTY_HOME/start.jar 
```

## To test webapp

Simply make requests for content against the following URLs

* `http://localhost:8080/` - this will trigger a simple logging event from
the webapp (and `text/plain` response indicating as such)
* `http://localhost:8080/naughty/` - this will trigger an unhandled exception from a webapp,
causing the standard Servlet error handling to kick in

## Files of interest in project

This project is an overlap of a Maven project and a Jetty `jetty.base` configuration.

The maven project builds a war file as normal, but has an additional step to put
that war file into `webapps/root.war`, so that the `jetty.base` configuration 
can use it. 

* `src/main/webapp/WEB-INF/web.xml` - this contains the 3 servlets + error handling definition
* `start.ini` - this contains the `jetty.base` configuration for this instance of jetty
 (it has modules for `http`, `deploy`, and `resources` along with manual lib entries for slf4j and log4j2)
* `lib/slf4j/` - this is the `jetty.base` server libs for slf4j, Using a version newer then what the
 webapp has. 
* `lib/log4j/` - this is the `jetty.base` server libs for log4j, Using a version newer then what the
 webapp has.
* `webapps/root.xml` - this is the configuration used to control the classloader for the
 deployed webapp at the root context (context-path of `/`).
* `resources/log4j2.xml` - this is the server log4j2 configuration
* `src/main/resources/log4j2.xml` - this is the webapp log4j2 configuration
 (also found within the `webapps/root.war` when compiled)

## Two options for controlling slf4j conflicts.

If you don't do anything on a normal Jetty installation and/or webapp, and you are using
slf4j for both the server and the webapp then you will no doubt encounter the following
warning from slf4j.

```
SLF4J: Class path contains multiple SLF4J bindings.
SLF4J: Found binding in [jar:file:/tmp/jetty-0_0_0_0-8080-root_war-_-any-6033438560692293445.dir/webapp/WEB-INF/lib/log4j-slf4j-impl-2.6.2.jar!/org/slf4j/impl/StaticLoggerBinder.class]
SLF4J: Found binding in [jar:file:/home/joakim/code/logging-webapp/lib/log4j/log4j-slf4j-impl-2.11.2.jar!/org/slf4j/impl/StaticLoggerBinder.class]
```

This is telling you that when the webapp's slf4j attempted to initialize it found multiple
copies of the `org.slf4j.impl.StaticLoggerBinder.class`.  This class is used to determine
what logging implementation that slf4j writes its events to.

In the above warning what happened is ...

* the `root.war` is being deployed
* the `org.eclipse.jetty.demos.DemoServlet.init()` is called to initialize the servlet
* the slf4j implementation looks for the class `org.slf4j.impl.StaticLoggerBinder.class`
 and finds two copies.
  * The one in the webapps `WEB-INF/lib/log4j-slf4j-impl-2.6.2.jar`
  * The one in the parent classloader in `${jetty.base}/lib/log4j/log4j-slf4j-impl-2.11.2.jar`
  
To address this, you have 2 choices.

1. Isolate the webapp and server logging classes.
2. Force webapp to use server classes.

### Isolate the webapp and server logging classes

This has the benefit that you can use different logging versions
and even different logging configurations between the server
and the webapp.

To accomplish this the `webapps/root.xml` has the following example configuration.

``` xml
  <!-- to isolate server and webapp logging libs use below (server and webapp have different config) -->
  <Get name="serverClasspathPattern">
    <Call name="add"><Arg>org.slf4j.</Arg></Call>
    <Call name="add"><Arg>org.apache.logging.slf4j.</Arg></Call>
    <Call name="add"><Arg>org.apache.logging.log4j.</Arg></Call>
  </Get>
``` 

### Force webapp to use server classes.

This has the benefit of having only 1 place on the server to configure and/or upgrade
the logging libraries for all events, server and webapp.

This is an alternate configuration found (commented out) in the `webapps/root.xml`

``` xml
  <!-- to force server logging libs in webapp use below (will use server config too!) -->
  <Get name="systemClasspathPattern">
    <Call name="add"><Arg>org.slf4j.</Arg></Call>
    <Call name="add"><Arg>org.apache.logging.</Arg></Call>
  </Get>
```