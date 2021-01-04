![Build Status](http://54.151.78.250:8080/jenkins/buildStatus/icon?job=goshopping-api/dev&subject=%5Bdev%5D%20took%20$%7Bduration%7D%20about%20$%7BstartTime%7D%20ago)

## goshopping-api

This is the backend api for an ecommerce store. You may find the documentation for it [here](http://54.151.78.250:8080/goshopping-api/docs/index.html). The api is currently being served by a tomcat instance that is running on an EC2 instance.

## technologies used

- PostgreSQL
- Hibernate
- JDBC
- Spring MVC
- JWT tokens
- Stripe
- Log4j
- Swagger/OpenAPI 3.0
- Maven

## features

- account creation/login
- secured api endpoints
- store item management
- viewable order history
- order creation
- payment processing via Stripe
- search/sort items by name/category/price

## getting started

### miminum setup

```
git clone https://github.com/VincentJ711/goshopping-api.git
mvn clean package
```

Next, set up the required environment variables.

```
DB_URL=jdbc:postgresql://.....:5432/goshopping
HBM2_DDL_AUTO={create|update}
DB_USERNAME=postgres
DB_PASSWORD=password
STRIPE_SK=sk_test...
JWT_SIGNING_KEY="this must be a very long sentence"
```

Then turn on your local PostgreSQL instance and make sure the database mentioned at the end of the above `DB_URL` exists.

Finally, you may start the api by doing

``` 
mvn clean package tomee:run
```

### additional, optional setup

You may initialize the PostgreSQL database with sample data by running the application @ `com.revature.goshopping.utility.DbInitializer`. Before you do so, you need to make sure the following environment variables are set

``` 
ADMIN_JWT=eyJhbGci... 
API_URL=http://localhost:4321/api
NEW_USER_PASSWORD=1234
```

To get the above `ADMIN_JWT`, you'll need to create an account, set the admin column for the user in the PostgreSQL database to be true, and then login to recieve the jwt.

## Usage

This is a REST api. You may communicate with it via standard HTTP methods. All the routes and required request data are noted in the [documentation](http://54.151.78.250:8080/goshopping-api/docs/index.html).

## database ERD

![](https://user-images.githubusercontent.com/25497140/103494164-3e6dc780-4dea-11eb-8f94-25e998a56305.png)

## Contributors

- Vincent Sevilla
- Md Abul Kashem
- Nick Barak
- Zack Garner
- Hisham Saymeh
- Erik Terreri

## License

This project is licensed under the terms of the MIT license.