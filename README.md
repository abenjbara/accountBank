# Bank Account Kata

## technologie utilisées
* JAVA 8  - Spring boot
* JUnit 5
* base de données H2


## REST API
dans cette API REST on trouve un service par US (4 services au total), et un test par service

### Service 1 (correspond à l'US1)
* @POST : http://localhost:8080/bank/deposit
* exemple du body : {
			"accountId" : 4,
			"amount" : 50
		 }
		 
### Service 2 (correspond à l'US2)
* @POST : http://localhost:8080/bank/deposit
* exemple du body : {
			"accountId" : 4,
			"amount" : 50
		 }

### Service 3 : (correspond à l'US3)	
* @GET: http://localhost:8080/bank/balance/{isAccount}
* example : http://localhost:8080/bank/balance/4


### Service 3 : (correspond à l'US4)	
* http://localhost:8080/bank/history/{idAccount}
* exemple http://localhost:8080/bank/history/4


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
