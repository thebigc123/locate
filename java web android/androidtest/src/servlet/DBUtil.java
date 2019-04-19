package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 * Servlet implementation class DBUtil
 */
@WebServlet("/DBUtil")

public class DBUtil {

	// table

	
 

	// connect to MySql database

	public static Connection getConnect() {

		String url = "jdbc:mysql://localhost:3306/user_address?"
				+ "useUnicode=true&characterEncoding=utf8&serverTimezone=GMT&useSSL=false";
		String user = "root";
		String password = "";
		Connection connection=null;

		try {

			Class.forName("com.mysql.jdbc.Driver"); // java∑¥…‰£¨πÃ∂®–¥∑®
			connection = DriverManager.getConnection(url, user, password);
			System.out.println(connection.getClass().getName());

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			

		}

		return connection;

	}

}
