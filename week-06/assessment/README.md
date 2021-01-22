# Planning Week 6 Assessment
## High Level Requirement
### DDL

### Step 1

If database exist drop
then create database

> Unless said  otherwise drop each table before creating

### Tables
1. Customer (1 hour)
     * CustomerID - int (auto)
     * firstName - varchar (25)
     * LastName - varchar (25)
     * Phone - varchar (13)
     * Address - varchar (50)
2. Customer_Show Bridge (Ticket info) (1 hour)
     * CustomerID - (fk)
     * ShowID - (fk)
     * TheaterID (fk)
     * Seat - varchar (3)
     * Ticket_price decimal (2,2)
     * Date (Date)
     * Identifier (CustomerId + ShowID + theaterID)
3. Show (1/2 hour)
   * ShowID - int auto
   * ShowName - Varchar (25)
4. Theater_Show Bridge (1/4 hour)
   * showID fk
   * theaterID fk
   * Identifier (showID + theaterID)
5. Theater (1/4 hour)
   * Name - varchar(25)
   * Address - varchar(50) 
   * Phone varchar(10)
   * Email - varchar(25) 
   * TheaterID - int autoincrement 
### Step 2    
### DML

Using Database   

1. Populate Customer table (1 hour)
   with firstname, lastname, email, phone, address
   <strong>From</strong> reservation table.
2. Populate Show table (1/4 hour)   
   with Showname from reservation table
3. Populate theater Table (1/4 hour)   
   with Name,Address,Phone,Email from reservation table.
4. Populate ShowCustomer (1/4 hour)   
   with CustomerID,ShowID,TheaterID,Ticket_price,date   
   through inner join between show and customer
5. Populate Theater_Show (1/4 hour)   
   with showID,theaterID   
   through inner join between show and customer
   
Drop Reservation Table when done.

### Step 3 DQL

1. Using the show_customer select dates between oct 1 - dec 31 (10 min)
2. Using Customer tables select all. (5 min)
3. Using Customer table select email column and look
for not like "%.com" (5 min)
4. Using ticket table order by price and limit by 3 (5 min)
5. from ticket left outer join customer and left outer join with show (5 min)
6. From customer select all where address is null (5 min)  
7. ?? (1 hour)
8. From ticket left outer join with Customer group by customer and count (5 min)
9. From ticket inner join with show group by show and sum of price. (20 min)
10. From ticket/customer_show_bridge inner join with theater group by theater name 
and sum total. (30 min)
12. Inner Join customer with Ticket sum ticket price and find max. (30 min)


### 
* What are the requirements for the project?
    * Do I have to do any research? Yes need to look into inner and outer join more
    * Are there any unknowns? What do I need to do to get clarity?
      None
    * What are my primary tasks?
      * set up DDL
      * set up DML
      * write query
    * How long do I estimate each of those tasks will take? 
      * setting up would take (2 to 3 hours)
      * Populating (should take the longest 3)
      * Query should take less than 2 hours
    
