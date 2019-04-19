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
		String sql = "insert into locatetable values(2,10.3,5.012,'����','')";
;
	
		
		int n = statement.executeUpdate(sql);
		if(n>0) {
			System.out.println("�ɹ�");
		}else {
			System.out.println("ʧ��");
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
		String account = request.getParameter("address"); // �� request �л�ȡ��Ϊ account �Ĳ�����ֵ
		String resCode = "";
		String resMsg = "";
		String userId = "";
		String message = "";
		
		try {
			Connection connect = DBUtil.getConnect();
			System.out.println(connect.getClass().getName());
			java.sql.Statement statement = connect.createStatement(); // Statement�������Ϊ���ݿ����ʵ���������ݿ�����в�����ͨ������ʵ��
			int result;

			//String sqlQuery = "select * from " + DBUtil.TABLE_PASSWORD + " where userAccount='" + account + "'";
			String sql = "insert into locatetable values(2,10.3,5.012,' "+account+"','')";
			// ��ѯ���������һ��ResultSet���ϣ�û�в鵽���ʱResultSet�ĳ���Ϊ0
			result = statement.executeUpdate(sql); // �Ȳ�ѯͬ�����˺ţ������ֻ��ţ��Ƿ����
			if(result>0) {
				message="�ɹ�";
				System.out.println(message);
			}else {
				message="ʧ��";
				System.out.println("ʧ��");
			}
			statement.close();

			
			connect.close();
		} catch (SQLException e) {

			e.printStackTrace();

		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append("message:").append(message);//�������յ����ݿ����״̬
		//��ʱ���Ϊ�յ���λ�õľ��������
	
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
	    
	

		// ��ȡPOST�������
		// �϶����˻ᷢ�֣��ⲻ�Ǻ�GET�����л�ȡ�����ķ���һ���˵ʵ������������һ���ģ�**������������£����Ǻ�߻����ᵽ**
		
		
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
			java.sql.Statement statement = connect.createStatement(); // Statement�������Ϊ���ݿ����ʵ���������ݿ�����в�����ͨ������ʵ��
			int result;

			//String sqlQuery = "select * from " + DBUtil.TABLE_PASSWORD + " where userAccount='" + account + "'";
			String sql = "insert into locatetable values('"+dateStr+"','"+latitude+"','"+longitude+"','"+country+"',' "+shiqu+"','"+site+"')";
			// ��ѯ���������һ��ResultSet���ϣ�û�в鵽���ʱResultSet�ĳ���Ϊ0
			result = statement.executeUpdate(sql); // �Ȳ�ѯͬ�����˺ţ������ֻ��ţ��Ƿ����
			if(result>0) {
				message="�ɹ�";
				System.out.println(message);
			}else {
				message="ʧ��";
				System.out.println("ʧ��");
			}
			statement.close();

			
			connect.close();
		} catch (SQLException e) {

			e.printStackTrace();

		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().append("��ĵ�ַ: ").append(site);
		}
	
	}

