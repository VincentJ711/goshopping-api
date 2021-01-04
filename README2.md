This is a document with more information that doesn't belong in the README.md.

### some non-exhaustive EC2 instructions

Make sure you have git, Java 8, Maven, Tomcat 8+, and Jenkins installed on an Amazon Linux instance. We installed Tomcat through `yum` and moved a Jenkins war file to `/usr/share/tomcat/webapps` to host Jenkins. Then make sure you paste the environment {var}={value} lines from the `README.md` getting started section to the end of `/usr/share/tomcat/conf/tomcat.conf` and then do `sudo service tomcat restart`. Note that whenever you change this file, you will have to restart Tomcat.

### truncating the tables in the db

There is a java application @ `com.revature.goshopping.utility.DbCleanser` you may run to delete all the rows from all the tables. make sure the following env vars are set:

```
export DB_USERNAME=postgres
export DB_URL=jdbc:postgresql://localhost:5432/goshopping
export DB_PASSWORD=1234
```

### generating openapi/swagger documentation

I went into `src/main/webapp/` and did

``` 
curl https://codeload.github.com/swagger-api/swagger-ui/tar.gz/master | tar -xz --strip=1 swagger-ui-master/dist && mv dist docs
```

to create a static `src/main/webapp/docs/` directory for our api documentation. I then modified the ui.url in `docs/index.html` to be `./api.yaml`. I then wrote the OpenAPI docs in `docs/api.yaml`.
