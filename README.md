![Build Status](http://54.151.78.250:8080/jenkins/buildStatus/icon?job=goshopping-api/dev&subject=%5Bdev%5D%20took%20$%7Bduration%7D%20about%20$%7BstartTime%7D%20ago)

## goshopping-api

This api is hosted on ec2 @ `http://54.151.78.250:8080/goshopping-api`. To be more specific, a tomcat process is actually serving the api.war file generated during the Jenkins build. This unzipped api.war file is our application. You can find the documentation for this api @ `http://54.151.78.250:8080/goshopping-api/docs/index.html`

### ci/cd setup

There is a jenkins application hosted on the same server @ `:8080/jenkins` which contains a multibranch pipeline that's configured to start whenever a push occurs to any branch of this repo. A deployment will occur if and only if a change to the dev branch is made.

### setup

make sure the database you refer to in the DB_URL below exists and ensure either the following jvm opts are set for the tomcat process

```
-DDB_URL=jdbc:postgresql://.....:5432/goshopping \
    -DHBM2_DDL_AUTO=update \
    -DDB_USERNAME=postgres \ 
    -DDB_PASSWORD=password
```

OR they exist as environment variables

```
export DB_URL="jdbc://..."
export ...
```

Note the jvm opts are required for the tomcat process on the ec2 instance. To make sure they are used, paste the following to the end of `/usr/share/tomcat/conf/tomcat.conf`. Also note you will have to `sudo service tomcat restart` whenever you change this file.

```
JAVA_OPTS="-DDB_URL=... -DDB_USERNAME=postgres ..."
```

### running locally

``` 
DB_USERNAME=postgres DB_PASSWORD=password HBM2_DDL_AUTO=create \
    DB_URL="jdbc:postgresql://localhost:5432/goshopping" \
    mvn clean package tomee:run
```

### generating openapi/swagger documentation

went into the webapp folder and did

``` 
curl https://codeload.github.com/swagger-api/swagger-ui/tar.gz/master | tar -xz --strip=1 swagger-ui-master/dist && mv dist docs
```

then modified the ui.url in docs/index.html to be ./api.yaml. then wrote the openspec api docs in the docs/api.yaml file.
