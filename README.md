# Transaction Manager
The goal of the system is to display statistic information about processed financial transactions. 
Once initialised, the system should report:
1.	The total number of transactions.
2.	The average transaction value for a specific merchant in a specific date range.
3.	An additional requirement is that, if a transaction record has a REVERSAL transaction, 
then it should not be included in the computed statistics, even if the reversing transaction 
is outside of the requested date range.

                                                                                                  
### Technologies: 
> JUnit, Maven, Apache Commons CSV
### Java version: 14

### Start the app with console UI:
> com.github.zhalabkevich.transaction_manager.Runner 
### Start the app with test data:
> com.github.zhalabkevich.transaction_manager.controller.TransactionControllerTest
## Main Settings
### App:
> src/main/resources/prop.properties
### Input CSV
> src/main/resources/data.csv

## Test Settings
### App:
> src/test/resources/prop.properties
> src/main/resources/data.csv

