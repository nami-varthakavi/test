# BUILDING A SIMPLE API FRAMEWORK USING SPARK JAVA 

## Local Setup 
1. Download/clone this repo
2. Set Java version to 11 
    1. Download the Windows/x64 build of Java 11.0.2 [from here](https://jdk.java.net/11/).
    2. Extract the contents into the location of your other Java installations. Usually found in C:/Program Files/Java
    3. Update your JAVA_HOME environment variable.
    4. Confirm CMD terminal picks up new java version by execting `java -version`
3. Maven 3.6.0 for Windows
   1. Download apache-maven-3.6.0-bin.zip [from here](https://maven.apache.org/download.cgi)
   2. Extract the contents to a location on your machine. You should probably keep your dev tools together on the same drive or folder.
   3. Update your MAVEN_HOME environment variable.
   4. Confirm CMD terminal picks up new version of maven.
   5. Update any personal or system wide maven settings (to pick up archiva or artifactory), or to change local maven repository location.
4. Run the Main.java under `java/com/main` to start the application 
