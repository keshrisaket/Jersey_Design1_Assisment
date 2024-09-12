package dao;

import beans.Employee;
import mysqlconnection.ConnectionProvider;

import javax.ws.rs.core.Response;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class Employee_Dao {

    public Response saveEmpDetail(Employee employee)
    {
        try{
            Connection con= ConnectionProvider.getConnection();
            System.out.println(con);
            PreparedStatement ps =con.prepareStatement("insert into employee (name, age, address, sal)  value( ? , ?, ?,? )");
            ps.setString(1,employee.getName());
            ps.setFloat(2,employee.getAge());
            ps.setString(3,employee.getAddress());
            ps.setDouble(4,employee.getSal());
            int val=ps.executeUpdate();
            if(val>0){
                Response.ResponseBuilder responseBuilder= Response.status(Response.Status.OK);
                responseBuilder.entity("Successfull");
                return  responseBuilder.build();
            }else {
               Response.ResponseBuilder responseBuilder=Response.status(Response.Status.UNAUTHORIZED);
               responseBuilder.entity("Failed");
               return responseBuilder.build();
            }
        }catch (Exception e){
            e.printStackTrace();
            Response.ResponseBuilder responseBuilder=Response.status(Response.Status.PRECONDITION_FAILED);
            responseBuilder.entity("Failed");
            return responseBuilder.build();
        }
    }


}
