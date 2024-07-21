TODO
- tests
- security (keycloack image, docker-compose), roles to resources
- refactor to more flexible api scenario (handlers)
- load balancer
- dockerfiles
- clean maven
- fix mapstruct
  
VM options

api-account -> -Dspring.profiles.active=local
api-exchange -> -Dspring.profiles.active=local
config-server -> -Dspring.profiles.active=native
eureka -> -Dspring.profiles.active=local 

Order to run
1. mvn clean install -Plocal
2. Eureka
3. Config-server
4. api-account or api-exchange

example postman

http://localhost:8080/api/exchange-usa
http://localhost:8090/api/get?pesel=97101328412
http://localhost:8080/api/getAccountBalance?pesel=97101328412
http://localhost:8090/api/create 
json -> {
"pesel": "97101328412",
"firstName": "Jan",
"lastName": "Kowalski",
"balance": 1000.55,
"currency": "PLN"
}