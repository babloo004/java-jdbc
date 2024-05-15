// code for mysql

//JDBC contains 7 steps
//1 - importing package (import java.sql.*)

//2 - loading and registering Driver. Each dbms will have different Driver codes
//syntax for loading the Driver is Class.forName("driver-code");
//each dbms will have different driver codes

//3 - creating Connection
//we need to create connection for connecting application to database
//so we need a Connection instance, we will get that by "DriverManager.getConnection(url,username,password)" method
//DriverManager.getConnection() returns Connection object by accepting three parameters, they are url, username,password
//url will contain the dbms name you are using, port number and database name on which you are working
//the standard url will look like this, "jdbc:dbms-name://ipaddress:port-number/database-name"

//4 - creating Statement
//there are two types of statements, they are normal statement 1. Statement , 2. Prepared Statement
//Statement(Normal Statement)
//after establishing connection we need to create Statement instance
//we will create that by using createStatement() method on Connection instance
//it wont take any parameters
//Prepared Statements
//we need to create PreparedStatement instance
//we will get that by applying the prepareStatement() method on Connection instance
//this method will take parameters
//in sql query we will leave question marks(?) as placeholders


//5 - executing Statement
//we will execute the statements
//there are different types of execute methods
//for executing select query ,we will use "executeQuery()" method which returns ResultSet
//if it is normal statement executeStatement() will take sql query as parameter
//if it is PreparedStatement it won't take any parameters

//6 - processing result
//after executing the query we will get the result
//there are each method for each curd operations

//7 - closing the connection
//this is the final and important step
//we should close the connection by close() method

import java.sql.*;
import java.util.Scanner;

class JDBC{
    //instance variable
    String url,uname,password,dbmsname,dbname;
    //constructor
    JDBC()  {
        //taking input
        //creating scanner class instance
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter dbms name : ");
        this.dbmsname = sc.nextLine();
        System.out.println("Enter database name : ");
        this.dbname = sc.nextLine();
        System.out.println("Enter username : ");
        this.uname = sc.nextLine();
        System.out.println("Enter password : ");
        this.password = sc.nextLine();

        //closing the scanner
        sc.close();
        if(this.dbmsname.equals("postgresql")){
            this.url = "jdbc:postgresql://localhost:5432/"+this.dbname;
        }else if(this.dbmsname.equals("mysql")){
            this.url = "jdbc:mysql://localhost:3306/"+this.dbname;
        }
    }

    //load and register driver
    public void landrd() throws Exception{
        if(this.dbmsname.equals("postgres")){
            Class.forName("org.postgresql.Driver");
        }else if(this.dbmsname.equals("mysql")){
            Class.forName("com.mysql.cj.jdbc.Driver");
        }
    }

    //creating connection instance
    public Connection getconnection() throws SQLException {
        return DriverManager.getConnection(this.url, this.uname, this.password);
    }
}

//crud operations
//create
//read
//update
//delete

public class Main {
    public static void main(String[] a)throws Exception{
        JDBC j = new JDBC();
        j.landrd();
        Connection con = j.getconnection();
        if(con!=null)System.out.println("Connection Established");
        //creating statement
        Statement st = con.createStatement();
        //defining query
        String sql = "select * from customers";
        //executing the sql query using "executeQuery()" method
        //executeQuery() method accepts sql query as the parameter
        //it returns "ResultSet" object
        //so we have stored the result in the ResultSet Type variable
        ResultSet rs = st.executeQuery(sql);
        //to check whether the result has generated or not, we use next() method
        //it will give boolean value
        if(rs.next()){
            System.out.println("Success!");
        }else{
            System.out.println("Failed!");
        }

        //Whenever we execute the query, the pointer will always top of the result
        //so when ever we implement next() method on ResultSet it will make the pointer to move down the column
        //to print out the result we will use getString() for string columns and getInt() for integer columns
        //getString() and getInt() will take column number or column name as parameter
        //we should pass only one column number or name
        do{
            System.out.println(rs.getString(2));
        }while(rs.next());
    }
}