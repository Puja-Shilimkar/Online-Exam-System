package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.dbutil.DbConnection;
import com.pojo.Question;

public class ExamDao {

    private Connection con;

    public ExamDao() {
        con = DbConnection.getDBConnection();
        if (con != null) {
            System.out.println("-----ExamDAO created-------");
            System.out.println("---connected to DB-----");
        } else {
            System.out.println("Failed to connect to DB");
        }
    }

    public void getAllQuestions() {
        try {
            String str = "select * from questions";
            Statement stmt = con.createStatement();
            ResultSet rset = stmt.executeQuery(str);
            while (rset.next()) {
                System.out.println(rset.getString("question_text") + "    " + rset.getString("option_a") + "    "
                        + rset.getString("option_b") + rset.getString("option_c") + "   " + rset.getString("option_d")+ "   " + rset.getString("correct_option"));
            }
            rset.close();
            stmt.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Add a new question(insert)
    public void addQuestion(Question question) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "INSERT INTO questions (question_text, option_a, option_b, option_c, option_d, correct_option) VALUES (?, ?, ?, ?, ?, ?)")) {
            pstmt.setString(1, question.getQuestionText());
            pstmt.setString(2, question.getOptionA());
            pstmt.setString(3, question.getOptionB());
            pstmt.setString(4, question.getOptionC());
            pstmt.setString(5, question.getOptionD());
            pstmt.setString(6, question.getCorrectOption());
            pstmt.executeUpdate();
        }
    }

    // Edit a question
    public void editQuestion(Question question) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement(
                "UPDATE questions SET question_text = ?, option_a = ?, option_b = ?, option_c = ?, option_d = ?, correct_option = ? WHERE question_id = ?")) {
            pstmt.setString(1, question.getQuestionText());
            pstmt.setString(2, question.getOptionA());
            pstmt.setString(3, question.getOptionB());
            pstmt.setString(4, question.getOptionC());
            pstmt.setString(5, question.getOptionD());
            pstmt.setString(6, question.getCorrectOption());
            pstmt.setInt(7, question.getQuestionId());
            pstmt.executeUpdate();
        }
    }

    // Delete a question
    public void deleteQuestion(int questionId) throws SQLException {
        try (PreparedStatement pstmt = con.prepareStatement("DELETE FROM questions WHERE question_id = ?")) {
            pstmt.setInt(1, questionId);
            pstmt.executeUpdate();
        }
    }

	public void closeConnection() {
		// TODO Auto-generated method stub
		
	}

	public Question getQuestionById(int questionIdToEdit) {
		// TODO Auto-generated method stub
		return null;
	}

    // ... other methods
}
