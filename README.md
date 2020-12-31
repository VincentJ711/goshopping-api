![Build Status](http://54.151.78.250:8080/jenkins/buildStatus/icon?job=goshopping-api/dev&subject=%5Bdev%5D%20took%20$%7Bduration%7D%20about%20$%7BstartTime%7D%20ago)

## goshopping-api

This api is hosted on ec2 @ `http://54.151.78.250:8080/goshopping-api`. To be more specific, a tomcat process is actually serving the api.war file generated during the Jenkins build. This unzipped war file is our application. You can find the documentation for this api [here](http://54.151.78.250:8080/goshopping-api/docs/index.html).

### ci/cd setup

There is a jenkins application hosted on the same server @ `:8080/jenkins` which contains a multibranch pipeline that's configured to start whenever a push occurs to any branch of this repo. A deployment will occur if and only if a change to the dev branch is made.

### running

make sure the database you refer to in the DB_URL below exists and ensure either the following jvm opts are set for the tomcat process

```
-DDB_URL=jdbc:postgresql://.....:5432/goshopping \
    -DHBM2_DDL_AUTO=update \
    -DDB_USERNAME=postgres \ 
    -DDB_PASSWORD=password \
    -DSTRIPE_SK=sk_test...
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

Finally, one way to run is by doing something like

``` 
DB_USERNAME=postgres DB_PASSWORD=password HBM2_DDL_AUTO=create \
    DB_URL="jdbc:postgresql://localhost:5432/goshopping" \
    STRIPE_SK="sk_test..." \
    mvn clean package tomee:run
```

### initializing a db with data

There is a java application @ `com.revature.goshopping.utility.DbInitializer` you may run to initialize the database with data. make sure the following env vars are set:

```
export ADMIN_JWT=eyJhbGci... 
export API_URL=http://localhost:4321/api
export NEW_USER_PASSWORD=1234
```

### truncating the tables in the db

There is a java application @ `com.revature.goshopping.utility.DbCleanser` you may run to delete all the rows from all the tables. make sure the following env vars are set:

```
export DB_USERNAME=postgres
export DB_URL=jdbc:postgresql://localhost:5432/goshopping
export DB_PASSWORD=1234
```

### generating openapi/swagger documentation

went into the webapp folder and did

``` 
curl https://codeload.github.com/swagger-api/swagger-ui/tar.gz/master | tar -xz --strip=1 swagger-ui-master/dist && mv dist docs
```

then modified the ui.url in docs/index.html to be ./api.yaml. then wrote the openspec api docs in the docs/api.yaml file.
