package DbServer;


import Model.*;

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

    public int getNextUserIdDB(){
        int user_id=1;
        try {
            query = " select * form users1 order by id desc limit 1";
            statement=connection.createStatement();
            resultSet=statement.executeQuery(query);
            if(resultSet.next()){
                user_id=resultSet.getInt("id")+1;
            }

        }
        catch (Exception e){
            System.out.println("Exception  "+e);
        }
        return user_id;
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

    public int getNextPostIdDB(){
        int post_id=1;
        try {
            query = " select * form Posts order by post_id desc limit 1";
            statement=connection.createStatement();
            resultSet=statement.executeQuery(query);
            if(resultSet.next()){
                post_id=resultSet.getInt("post_id")+1;
            }

        }
        catch (Exception e){
            System.out.println("Exception  "+e);
        }
        return post_id;
    }

    public void addPostDB(Post post) {
        try{
            query ="INSERT INTO Post ( person_id, post_name) VALUES (?,?)";
            prepStmt = connection.prepareStatement(query);
            prepStmt.setInt(1,post.getUser().getId());
            prepStmt.setString(2, post.getPostTitle());
        }
        catch (Exception e){
            e.printStackTrace();
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

    public int getNextInteractionIdDB(){
        int interaction_id=1;
        try {
            query = " select * form interaction order by interaction_id desc limit 1";
            statement=connection.createStatement();
            resultSet=statement.executeQuery(query);
            if(resultSet.next()){
                interaction_id=resultSet.getInt("interaction_id")+1;
            }

        }
        catch (Exception e){
            System.out.println("Exception  "+e);
        }
        return interaction_id;
    }

    public void removeInteractionDB(Interaction interaction) {
        try {
            query ="DELETE FROM  interaction  WHERE interaction_id = ?;";
            prepStmt = connection.prepareStatement(query);
            prepStmt.setInt(1,interaction.getInteractionId());
        }
        catch (Exception e){
            e.printStackTrace();
        }
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

    public int getNextCommentIdDB(){
        int comment_id=1;
        try {
            query = " select * form comment order by comment_id desc limit 1";
            statement=connection.createStatement();
            resultSet=statement.executeQuery(query);
            if(resultSet.next()){
                comment_id=resultSet.getInt("interaction_id")+1;
            }

        }
        catch (Exception e){
            System.out.println("Exception  "+e);
        }
        return comment_id;
    }

    public void addCommentDB(Comment comment) {
        try {
            query = "INSERT INTO comment (interaction_id,comment_data) VALUES (?,?)";
            prepStmt = connection.prepareStatement(query);
            prepStmt.setInt(1,comment.getCommentId());
            prepStmt.setString(2,comment.getCommentData());
            prepStmt.executeUpdate();
            if(comment.getInteractionType()==4){
                query="update interaction set comment_id=? ";
                prepStmt=connection.prepareStatement(query);
                prepStmt.setInt(1,comment.getReplyTo());
                prepStmt.executeUpdate();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    void addFriendsDB(Friendship friendship) throws  Exception {
        String query ="INSERT INTO friendship ( p1_id, p2_id) VALUES (?,?)";
        prepStmt = connection.prepareStatement(query);
        prepStmt.setInt(1,friendship.getUser1().getId());
        prepStmt.setInt(2,friendship.getUser2().getId());
        prepStmt.executeUpdate();

    }

    void numberOfLikesForPostDB(int  postId) throws Exception{
        String query ="select count(*) from interaction where interaction_type=1 and post_id=?;";
        prepStmt = connection.prepareStatement(query);
        prepStmt.setInt(1, postId);
        resultSet = prepStmt.executeQuery();
        resultSet.next();
        System.out.println("number of likes is :  " + resultSet.getInt(1));
    }

    void getALLCommentsForPostDB(int postId) throws Exception{
        String query ="select c.comment_data from comment as c where c.interaction_id in (select interaction_id from interaction as i where i.post_id=?);";
        prepStmt = connection.prepareStatement(query);
        prepStmt.setInt(1,postId);
        resultSet = prepStmt.executeQuery();
        System.out.println("The comments area ");
        while(resultSet.next()){
            System.out.println( resultSet.getString(1));
        }
    }

    void getAllPostsOfFriendsDB(int  userId ) throws  Exception {
        System.out.println("Enter the number of post to be displayed in each iteration   ");
        Scanner sc = new Scanner(System.in);
        int offset=0;
        int limit=sc.nextInt();
        String query ="select * from Post as p where p.person_id in (select p1_id from friendship where p2_id= ? union select p2_id from friendship where p1_id= ?) and post_timestaamp< ? order by post_timestaamp desc limit ?,? ;";
        prepStmt = connection.prepareStatement(query);
        Timestamp currentTimestamp=new Timestamp(System.currentTimeMillis());
        while(true) {
            System.out.println("The posts ids      person id        postname    are ");
            prepStmt.setInt(1, userId);
            prepStmt.setInt(2, userId);
            prepStmt.setTimestamp(3,currentTimestamp);
            prepStmt.setInt(4,offset);
            prepStmt.setInt(5,limit);
            resultSet= prepStmt.executeQuery();
            while(resultSet.next()){
                int post_id =resultSet.getInt(2);
                int person_id =resultSet.getInt(1);
                String post_name=resultSet.getString(3);
                System.out.println(post_id+"      "+person_id+"     "+post_name);
            }
            System.out.println("Enter 1 to display next set 0 to exit ");
            int op=sc.nextInt();
            if(op==0  )  break;
            offset+=limit;
        }

    }
}
