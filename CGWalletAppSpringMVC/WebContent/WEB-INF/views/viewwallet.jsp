<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Wallet</title>
</head>
<body>
<a href="">Log out</a>
<h2>Welcome ${customer.name}</h2>
<h2>Your account number is ${customer.mobileNo}</h2>
<h2>Your Balance is : ${customer.wallet.balance}</h2>

<h5><a href="deposit">Deposit</a></h5>
<h5><a href="withdraw">WithDraw</a></h5>
<h5><a href="fundtransfer">Fund Transfer</a></h5>
<h5><a href="listtransactions/${customer.mobileNo}">List Transactions</a></h5>
<h5><a href="getcustomers">GetCustomers</a></h5>
<h5><a href="getcustomer">GetCustomer_java</a></h5>


</body>
</html>