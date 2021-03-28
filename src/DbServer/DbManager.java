package DbServer;


import Model.Comment;
import Model.Interaction;
import Model.Post;
import Model.User;

import java.sql.*;

public class DbManager {
    private Connection connection;
    private Statement statement;
    private PreparedStatement prepStmt;
    private ResultSet resultSet,resultSet1;
    private String query;

    public DbManager(){
        initDB();
        //loadDb();
    }
    public void initDB()  {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/newdb", "social", "jaswanth");
            System.out.println("Connection established to Social  database...");
            statement =connection.createStatement();
            //execute  alll create table queries;
        }
        catch (Exception e){
            System.out.println("Exception   "+e);
        }
    }

    public void addUserDB(User user){

    }


    public void removeUserDB(User user) {

    }

    public void addPostDB(Post post) {

    }

    public void removePostDB(Post post) {

    }

    public void removeInteractionDB(Interaction interaction) {

    }

    public void addInteractionDB(Interaction interaction) {
    }

    public void addComment(Comment comment) {
    }
}
