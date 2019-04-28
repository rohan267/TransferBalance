# TransferBalance

There are two hard coded accounts in the system

<b>Account one details</b><br>
accountNumber: 1<br>
balance: 10,000<br>

<b>Account two details</b><br>
accountNumber: 2<br>
balance: 20,000<br>


To check the API status
http://localhost:8081/account/index

To initiate Transfer
http://localhost:8081/account/transfer?fromAccount=1&toAccount=2&transferAmount=1000

To check balance
http://localhost:8081/account/checkbalance?accountNumber=1


There are few test cases added to check the balance transfer transaction service
