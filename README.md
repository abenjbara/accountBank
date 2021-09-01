# Bank Account Kata

## technologies utilisées
* JAVA 8  - Spring boot
* JUnit 5
* base de données H2


## REST API
dans cette API REST on trouve un service par US (3 services au total), et un test par service

### Service 1:
 ```bash
In order to save money
As a bank client
I want to make a deposit in my account
```


* @POST : http://localhost:8080/bank/deposit
* exemple du body : {
			"clientId" : 1,
			"accountId" : 4,
			"amount" : 50
		 }
		 
### Service 2:
 ```bash
In order to retrieve some or all of my savings
As a bank client
I want to make a withdrawal from my account
```

* @POST : http://localhost:8080/bank/withdrawal
* exemple du body : {
			"clientId" : 1,
			"accountId" : 4,
			"amount" : 50
		 }


### Service 3:
 ```bash
In order to check my operations
As a bank client
I want to see the history (operation, date, amount, balance) of my operations
```

* http://localhost:8080/customers/{customerId}/accounts/{accountId}/history
* exemple http://localhost:8080/customers/1/accounts/4/history


## Jeu de données
En lancant l'application , 3 clients et 4 comptes sont crées.

liste des custumer
Customer[id=1, firstName='jean', lastName='pierre']
Customer[id=2, firstName='adam', lastName='benjbara']
Customer[id=3, firstName='celine', lastName='barreau']
liste des compte
Account[id=4, solde='256,500000']
Account[id=5, solde='1000,500000']
Account[id=6, solde='0,000000']
Account[id=7, solde='79999,600000']

* Le client id 1 possède le compte id 4
* Le client id 2 possède le compte id 5
* Le client id 3 possède le compte id 6 et 7

## Pour lancer l'application 
mvn spring-boot:run

## Pour lancer les tests et generer le jar
mvn clean package
