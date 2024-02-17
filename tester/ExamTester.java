package com.tester;

import com.dao.ExamDao;
import com.dbutil.DbConnection;
import com.pojo.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class ExamTester {

    public static void main(String[] args) throws SQLException {
        System.out.println("---Driver Class Get Loaded---");  

        //getAllQuestions
        ExamDao examDao = new ExamDao();
        System.out.println("---Connected With DB---");  

        examDao.getAllQuestions();
        
     // client : Insert
     		try {

     			// Connection
     			Connection con = DbConnection.getDBConnection();
     			

     			Scanner sc = new Scanner(System.in);

     			System.out.println("questionText optionA optionB optionC optionD correctOption");
     			String questionText = sc.next();
     			String optionA = sc.next();
     			String optionB = sc.next();
     			String optionC = sc.next();
     			String optionD = sc.next();
     			String correctOption= sc.next();
     			
     			// Dynamic Sql:query with parameter
     			String strInsert = "insert into questions values(?,?,?,?,?,?)";

     			// when Dynamic Sql use PreparedStament

     			PreparedStatement pstmt = con.prepareStatement(strInsert);
     			// assign values to parameters
     			pstmt.setString(1, questionText);
     			pstmt.setString(2, optionA);
     			pstmt.setString(3, optionB);
     			pstmt.setString(4, optionC);
     			pstmt.setString(5, optionD);
     			pstmt.setString(6, correctOption);

     			// When sql is DML(insert ,update,delete) then use executeUpdate()

     			int i = pstmt.executeUpdate();
     			System.out.println(i + " Row Inserted ");

     		} catch (Exception ex) {
     			ex.printStackTrace();
     		}
     		 // client: Edit
            try {
                Connection con = DbConnection.getDBConnection();
                Scanner sc = new Scanner(System.in);

                System.out.println("Enter question ID to edit: ");
                int questionId = sc.nextInt();

                System.out.println("Enter new question text, options, and correct option (separated by spaces):");
                String questionText = sc.next();
                String optionA = sc.next();
                String optionB = sc.next();
                String optionC = sc.next();
                String optionD = sc.next();
                String correctOption = sc.next();

                String strUpdate = "update questions set question_text=?, option_a=?, option_b=?, option_c=?, option_d=?, correct_option=? where question_id=?";
                PreparedStatement pstmt = con.prepareStatement(strUpdate);
                pstmt.setString(1, questionText);
                pstmt.setString(2, optionA);
                pstmt.setString(3, optionB);
                pstmt.setString(4, optionC);
                pstmt.setString(5, optionD);
                pstmt.setString(6, correctOption);
                pstmt.setInt(7, questionId);

                int i = pstmt.executeUpdate();
                System.out.println(i + " Row Updated");
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            // client: Delete
            try {
                Connection con = DbConnection.getDBConnection();
                Scanner sc = new Scanner(System.in);

                System.out.println("Enter question ID to delete: ");
                int questionId = sc.nextInt();

                String strDelete = "delete from questions where question_id=?";
                PreparedStatement pstmt = con.prepareStatement(strDelete);
                pstmt.setInt(1, questionId);

                int i = pstmt.executeUpdate();
                System.out.println(i + " Row Deleted");
            } catch (Exception ex) {
                ex.printStackTrace();
            }


     	}

     
    }




