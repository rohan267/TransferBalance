# TransferBalance

There are two hard coded accounts in the system
Account one details:
accountNumber: 1
balance: 10,000

Account two details:
accountNumber: 2
balance: 20,000

To check the API status
http://localhost:8081/account/index

To initiate Transfer
http://localhost:8081/account/transfer?fromAccount=1&toAccount=2&transferAmount=1000

To check balance
http://localhost:8081/account/checkbalance?accountNumber=1


There are few test cases added to check the balance transfer transaction service
