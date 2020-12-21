## goshopping-api

### setup

ensure the following env variables are set to something like

```
DB_URL=jdbc:postgresql://localhost:5432/goshopping
DB_USERNAME=postgres
DB_PASSWORD=admin
HBM2_DDL_AUTO=create-only
```

ensure the database you refer to in env.DB_URL exists. Note the possible values for HBM2_DDL_AUTO can be found [here](https://stackoverflow.com/a/43727307/7929314)

### running

one way to run is to use the maven tomee plugin

``` 
mvn package tomee:run
```

### high level relationships

entity objects should be passed to the dao layer which should return entity(s).

models should be created from entities in the service layer. these models should then be passed back to the controller to be sent back to the client as json.

instances of type Auth will should be passed to every service method that requires authentication. it may be nullable. it will be created upon login and returned to the user encoded in a jwt. the user will then pass along this jwt in the Authorization header for any requests that require authentication. The controllers will then parse the jwt from the header into an Auth object and pass this along to the desired service method.

all exceptions including/beneath the service layer should be handled in the service layer and thrown as a ServiceException. the controllers should then handle the exception and return the appropriate response/code. To be continued...

#### models

```
Tag
- id
- name

Item
- id
- price
- description
- name
- tags: Tag[]

OrderItem : Item
- quantity

Order
- id
- date
- userID
- items: OrderItem[]

User
- id
- username
- admin
- password