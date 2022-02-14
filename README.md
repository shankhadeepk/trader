# Trader
The application provides resources to add and check trades

## Software used
* Java 8
* Spring Boot 2.6.3
* Junit 4
* Maven 3.8.1
* Sl4j
* H2 database

## Features Implemented
* API Endpoints to add and check "trades".
* Exception Handler
* Junit Test cases
* Actuator
* Scheduler to update the Trade Expiry Flag

## Execution
Application can be started by following commands

### Build the project
    mvn clean install

### Run the test cases
    mvn clean test

### Run the project
    mvn spring-boot:run

### Test health using the command
    curl http://localhost:8088/actuator/health
    Response:
        {"status":"UP"}
### Endpoints exposed
    
    POST : http://localhost:8088/trades
    Request:
    {
        "tradeId" : "T1",
        "version":"2",
        "counterPartyId":"CP-1",
        "bookId":"B1",
        "maturityDate":"20/05/2021",
        "expired":"N"
    }
    Response:
    {
    "message": "Trade added/updated successfully"
    }

    GET : http://localhost:8088/trades
    Response:
    [
        {
            "tradeId": "T1",
            "version": 2,
            "counterPartyId": "CP-1",
            "bookId": "B1",
            "maturityDate": "2022-05-20",
            "createdDate": "2022-02-13",
            "expired": "N"
        }
    ]
### Scheduler is configured to run every day by 11:50 PM to update the Expiry Flag
    
    applicatin.properties
    trade.expiring.interval=0 50 23 1/1 * ?

## Test Case Class Created

    com.trade.trader.resource.TraderResourceTest
    com.trade.trader.resource.TraderScheduledJobTest