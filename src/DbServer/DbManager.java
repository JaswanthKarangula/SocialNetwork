package DbServer;


import Model.Comment;
import Model.Interaction;
import Model.Post;
import Model.User;

import java.sql.*;
import java.util.Scanner;

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

        try {
            query = "INSERT INTO users1 (first_name, last_name) VALUES (?,?) ";
            prepStmt  = connection.prepareStatement(query);
            prepStmt.setString(1,user.getFirstName());
            prepStmt.setString(2,user.getLastName());
            prepStmt.executeUpdate();

        }
        catch (Exception e){
            System.out.println("Exception  "+e);
        }

    }


    public void removeUserDB(User user)   {

        try {

            query = "DELETE FROM  users1  WHERE id = ?;";
            prepStmt = connection.prepareStatement(query);
            prepStmt.setInt(1, user.getId());
            prepStmt.executeUpdate();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addPostDB(Post post) {
        try{
            query ="INSERT INTO Post ( person_id, post_name) VALUES (?,?)";
            prepStmt = connection.prepareStatement(query);
            prepStmt.setInt(1,post.getUser().getId());
            prepStmt.setString(2, post.getPostTitle());
        }
        catch (Exception e){

        }
    }

    public void removePostDB(Post post) {
        try {
            query ="DELETE FROM  Post  WHERE post_id = ?;";
            prepStmt = connection.prepareStatement(query);
            prepStmt.setInt(1,post.getPostId1());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void removeInteractionDB(Interaction interaction) {

    }

    public void addInteractionDB(Interaction interaction) {
        try{
            query ="INSERT INTO interaction ( post_id, interaction_type,person_id) VALUES (?,?,?)";
            prepStmt = connection.prepareStatement(query);

            prepStmt.setInt(1, interaction.getPost().getPostId1());
            prepStmt.setInt(2, interaction.getInteractionType());

            prepStmt.setInt(3, interaction.getUser().getId());
            prepStmt.executeUpdate();
            if(interaction.getInteractionType()==3){
                query="update interaction set comment_id=?";
                prepStmt=connection.prepareStatement(query);
                prepStmt.setInt(1,interaction.getReplyTo());
                prepStmt.executeUpdate();

            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void addComment(Comment comment) {
        try {
            query = "INSERT INTO comment (interaction_id,comment_data) VALUES (?,?)";
            prepStmt = connection.prepareStatement(query);
            prepStmt.setInt(1,comment.getCommentId());
            prepStmt.setString(2,comment.getCommentData());
            if(comment.getInteractionType()==4){
                query="update interaction set comment_id=? ";
                prepStmt=connection.prepareStatement(query);
                prepStmt.setInt(1,comment.getReplyTo());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
