package test;

import java.io.IOException;
import java.sql.Connection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.connection.ConnectionProvider;
import member.dao.MemberDao;
import member.model.Member;

/**
 * Servlet implementation class MemberDaoUpdateServlet
 */
@WebServlet("/MemberDaoUpdateServlet")
public class MemberDaoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDaoUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest reqest, HttpServletResponse response) throws ServletException, IOException {
		
		try (Connection conn = ConnectionProvider.getConnection();) {
			Member member = new Member("seoul4", "1", "3", new Date());
			
			MemberDao dao = new MemberDao();
			dao.update(conn, member);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
