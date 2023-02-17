

### Maven simple archetype command

```
mvn archetype:generate -DgroupId=pt.ua.tqs -DartifactId=EuroMilions -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```



### Jacoco reports

```
mvn clean test jacoco:report
```

This will generate a report under the targe directory, we can see and html file in the target/site/jacoco directory