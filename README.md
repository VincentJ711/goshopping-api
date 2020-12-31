![Build Status](http://54.151.78.250:8080/jenkins/buildStatus/icon?job=goshopping-api/dev&subject=%5Bdev%5D%20took%20$%7Bduration%7D%20about%20$%7BstartTime%7D%20ago)

## goshopping-api

This api is hosted on ec2 @ `http://54.151.78.250:8080/goshopping-api`. To be more specific, a tomcat process is actually serving the api.war file generated during the Jenkins build. This unzipped war file is our application. You can find the documentation for this api [here](http://54.151.78.250:8080/goshopping-api/docs/index.html).

### ci/cd setup

There is a jenkins application hosted on the same server @ `:8080/jenkins` which contains a multibranch pipeline that's configured to start whenever a push occurs to any branch of this repo. A deployment will occur if and only if a change to the dev branch is made.

### running

make sure the database you refer to in the DB_URL below exists and ensure the following environment variables are set for the tomcat process.

```
DB_URL=jdbc:postgresql://.....:5432/goshopping
HBM2_DDL_AUTO=update
DB_USERNAME=postgres
DB_PASSWORD=password
STRIPE_SK=sk_test...
JWT_SIGNING_KEY="this must be a very long sentence"
```

if running tomcat on the ec2 through systemctl, paste the above lines to the end of `/usr/share/tomcat/conf/tomcat.conf` and then do `sudo service tomcat restart`. note that whenever you change this file, you will have to restart tomcat.

Finally, to run

``` 
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
