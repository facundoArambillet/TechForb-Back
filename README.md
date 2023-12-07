## Install dependencies

    mvn clean install
## Local Doc
Swagger Local can be found here:

http://localhost:8080/techforb/swagger-ui/index.html

## Production Doc
Swagger Production can be found here:
https://tech-forb.onrender.com/techforb/swagger-ui/index.html

## Production default user
Document Type : DNI
Name : name
Lastname: lastname
Document Number: 12345678
Password: 123456

## Production Without the default user
If you want to use a user that is not the default user, 
you must follow these steps to load the transaction history and the balance graph:
1) Go to swagger documentation in production
2) Register two users using /register endpoint in user-controller the idUserDocumentType for DNI is 1
3) Deposit using /transaction/deposit endpoint in transaction-controller to make transfers
4) Make transactions using /transaction endpoint in transaction-controller with different dates(between current day and 7 days ago)