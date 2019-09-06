To execute this project from command prompt, you need a Tomcat environment, JDK and Maven

1.  Go to Tomcat-Enceladus folder and execute

mvn install:install-file -Dfile=takipi-sdk-0.2.0.jar -DgroupId=com.takipi -DartifactId=takipi-sdk -Dversion=0.2.0 -Dpackaging=jar -DgeneratePom=true

2.  Go to the Tomcat-Enceladus Folder and execute
```
mvn clean compile package
```
3. Deploy the war file from the target directory (Enceladus-1.0.war) to Tomcat
4. Make sure to add Takipi to the tomcat instance.
	```
	-agentlib:TakipiAgent -Dtakipi.application.name=APPID12345 -Dtakipi.deployment.name=Orion:v3.7,Enceladus:v1.8
	```
5. Go to the root of the Enceladus Application
    ```
    http://<TomcatHostName>:<PortNumber>/Enceladus-1.0/
    ```
5. Generate errors in Payment and Common.

Good Luck.