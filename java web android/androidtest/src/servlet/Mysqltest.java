/*
package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.mysql.cj.xdevapi.Statement;

public class Mysqltest {

	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {

	// TODO Auto-generated method stub
		String className = "com.mysql.cj.jdbc.Driver";
		Class.forName(className);
		
		
		String url = "jdbc:mysql://localhost:3306/user_address?"
				+ "useUnicode=true&characterEncoding=utf8&serverTimezone=GMT&useSSL=false";
		String user = "root";
		String password = "";
		
		Connection connection = DriverManager.getConnection(url, user, password);
		System.out.println(connection.getClass().getName());
		
		java.sql.Statement statement =  connection.createStatement();
		String sql = "insert into locatetable values(2,10.3,5.012,'北京','')";
;
	
		
		int n = statement.executeUpdate(sql);
		if(n>0) {
			System.out.println("成功");
		}else {
			System.out.println("失败");
		}
		statement.close();

		
		connection.close();
		
	}
	
	
}
*/

package servlet;

import java.util.*;
import java.util.Date;
import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.mysql.cj.xdevapi.Statement;
import java.text.SimpleDateFormat;
@WebServlet("/Mysqltest")
public class Mysqltest extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
    */
    public  Mysqltest() {
        super();
    }
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String account = request.getParameter("address"); // 从 request 中获取名为 account 的参数的值
		String resCode = "";
		String resMsg = "";
		String userId = "";
		String message = "";
		
		try {
			Connection connect = DBUtil.getConnect();
			System.out.println(connect.getClass().getName());
			java.sql.Statement statement = connect.createStatement(); // Statement可以理解为数据库操作实例，对数据库的所有操作都通过它来实现
			int result;

			//String sqlQuery = "select * from " + DBUtil.TABLE_PASSWORD + " where userAccount='" + account + "'";
			String sql = "insert into locatetable values(2,10.3,5.012,' "+account+"','')";
			// 查询类操作返回一个ResultSet集合，没有查到结果时ResultSet的长度为0
			result = statement.executeUpdate(sql); // 先查询同样的账号（比如手机号）是否存在
			if(result>0) {
				message="成功";
				System.out.println(message);
			}else {
				message="失败";
				System.out.println("失败");
			}
			statement.close();

			
			connect.close();
		} catch (SQLException e) {

			e.printStackTrace();

		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append("message:").append(message);//现在是收到数据库插入状态
		//到时候改为收到的位置的具体参数。
	
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String latitude;
		String longitude;
		String country;
		String shiqu;
		String site;
		String message = "";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date = new Date();
	    String dateStr = sdf.format(date);
	    
	

		// 获取POST请求参数
		// 肯定有人会发现，这不是和GET方法中获取参数的方法一样嘛！说实话，看起来是一样的，**这个问题先留下，我们后边还会提到**
		
		
		latitude = request.getParameter("latitudes");
		longitude = request.getParameter("longitudes");
		//country = request.getParameter("countrys");
		country= new String(request.getParameter("countrys").getBytes("ISO-8859-1"),"UTF8"); 
		shiqu = new String(request.getParameter("shiqus").getBytes("ISO-8859-1"),"UTF8"); 
		site = new String(request.getParameter("sites").getBytes("ISO-8859-1"),"UTF8"); 
		System.out.println(dateStr+country);
		response.setContentType("text/html;charset=utf-8");
		try {
			Connection connect = DBUtil.getConnect();
			System.out.println(connect.getClass().getName());
			java.sql.Statement statement = connect.createStatement(); // Statement可以理解为数据库操作实例，对数据库的所有操作都通过它来实现
			int result;

			//String sqlQuery = "select * from " + DBUtil.TABLE_PASSWORD + " where userAccount='" + account + "'";
			String sql = "insert into locatetable values('"+dateStr+"','"+latitude+"','"+longitude+"','"+country+"',' "+shiqu+"','"+site+"')";
			// 查询类操作返回一个ResultSet集合，没有查到结果时ResultSet的长度为0
			result = statement.executeUpdate(sql); // 先查询同样的账号（比如手机号）是否存在
			if(result>0) {
				message="成功";
				System.out.println(message);
			}else {
				message="失败";
				System.out.println("失败");
			}
			statement.close();

			
			connect.close();
		} catch (SQLException e) {

			e.printStackTrace();

		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append("你的地址: ").append(site);
		}
	
	}

