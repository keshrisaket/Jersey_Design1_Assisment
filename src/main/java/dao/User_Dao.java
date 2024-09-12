package dao;

import beans.User;
import mysqlconnection.ConnectionProvider;
import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User_Dao {

    public static boolean checkUserExist(String email, String pwd){
        try{
           Connection con=ConnectionProvider.getConnection();
            System.out.println(con);
            PreparedStatement ps=con.prepareStatement("SELECT * FROM user  WHERE email = ? AND password = ? ");
            ps.setString(1,email);
            ps.setString(2,pwd);
            ResultSet rs =ps.executeQuery();
            System.out.println("exe");
            while (rs.next()){
                System.out.println("exe 2");
                return true;
            }

            return false;

        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public Response saveUserDetail(User user){
        try{
            Connection con= ConnectionProvider.getConnection();
            System.out.println(con);
            PreparedStatement ps =con.prepareStatement("insert into user (name, email, password) value (?, ?, ?) ");
            ps.setString(1,user.getName());
            ps.setString(2,user.getEmail());
            ps.setString(3,user.getPassword());
            int val=ps.executeUpdate();
            if (val>0){
                Response.ResponseBuilder rb=Response.status(Response.Status.OK);
                rb.entity("Operation Sucessfull");
                return  rb.build();
            }else {
                Response.ResponseBuilder rb=Response.status(Response.Status.UNAUTHORIZED);
                rb.entity("Operation Failed");
                return rb.build();
            }
        }catch (Exception e){
            e.printStackTrace();
            Response.ResponseBuilder rb=Response.status(Response.Status.UNAUTHORIZED);
            rb.entity("Operation Failed");
            return rb.build();
        }

    }




}
