package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class Posttest
 */
@WebServlet("/Posttest")
public class Posttest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Posttest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)			throws ServletException, IOException {		
		String account;		
		String password;				
		// ��ȡPOST�������		
		// �϶����˻ᷢ�֣��ⲻ�Ǻ�GET�����л�ȡ�����ķ���һ���˵ʵ������������һ���ģ�**������������£����Ǻ�߻����ᵽ**		
		account = request.getParameter("account");		
		password = request.getParameter("password");
	
		response.getWriter().append("���ύ���˺�Ϊ: ").append(account).append("\n���룺").append(password);
	}

}
