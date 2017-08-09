

Add FreeTTS dependencies
------------------------
<!-- https://mvnrepository.com/artifact/net.sf.sociaal/freetts -->
<dependency>
    <groupId>net.sf.sociaal</groupId>
    <artifactId>freetts</artifactId>
    <version>1.2.2</version>
</dependency>

In command line :
mvn install:install-file -Dfile=freetts.jar -DgroupId=net.sf.sociaal -DartifactId=freetts -Dversion=1.2.2 -Dpackaging=jar