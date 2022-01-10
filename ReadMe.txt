# Reward-Point-Demo-App
Demo spring boot application for calculating reward point of customers

**Setup: **
Clone this project from git into eclipse or STS using https://github.com/hrishidkd/Reward-Point-Demo-App.git.
Build this project.
Run this project as Spring Boot App.
Sample data is automatically inserted into the H2 database by the data.sql file placed uder resources.

To access H2 db console follow these steps:
        type this URL in browser: http://localhost:8081/h2-console
        URL: jdbc:h2:mem:test
        User: sa
        Leave password field as blank.
        Click on connect.
        
        
To add a Bill, hit this URL from Postman
        http://localhost:8081/AddBill?billId=111&userId=2&userName=Jason&date=2011-12-10&amount=68.5
        Parameters:
        billId: Unique Id of the bill
        userId: User Id of the customer.(If user is present transaction is added against that user Id or else new user is created.)
        userName: Name of the customer.
        date: Date when bill is created.
        amount: Total amount of the bill.
        
        
To view all reward points for a user, hit this URL from Postman
        http://localhost:8081/getAllPointsForUser?userId=2
        Parameters:
        userId: User Id of the user for which we want to view all reward points.

To view all reward points for a user for a month, hit this URL from Postman
        http://localhost:8081/getAllPointsForUserByMonth?userId=2&year=2021&month=11
        Parameters:
        userId: User Id of the user for which we want to view all reward points.
        year: Year for which we want reward point of a user.
        month: Month for which we want reward point of a user.