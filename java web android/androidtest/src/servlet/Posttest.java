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
		// 获取POST请求参数		
		// 肯定有人会发现，这不是和GET方法中获取参数的方法一样嘛！说实话，看起来是一样的，**这个问题先留下，我们后边还会提到**		
		account = request.getParameter("account");		
		password = request.getParameter("password");
	
		response.getWriter().append("你提交的账号为: ").append(account).append("\n密码：").append(password);
	}

}
