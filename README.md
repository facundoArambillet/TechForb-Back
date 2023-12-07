## Install dependencies

    mvn clean install
## Local Doc
Swagger Local can be found here:

http://localhost:8080/techforb/swagger-ui/index.html

## Production Doc
Swagger Production can be found here:
https://tech-forb.onrender.com/techforb/swagger-ui/index.html

## Production Load account
To load data that is shown in the history and in the graph, follow the following steps:
1) Go to swagger documentation in production
2) Register two users using /register endpoint in user-controller the idUserDocumentType for DNI is 1
3) Deposit using /transaction/deposit endpoint in transaction-controller to make transfers
4) Make transactions using /transaction endpoint in transaction-controller with different dates(between current day and 7 days ago)