package login;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Profile extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();
		String n = (String) session.getAttribute("user");
		out.println("<h1> WELCOME " + n + "</h1>");
		out.println("<form action='Lgout' method='post'>");
		out.println("<input type='submit' value='LOGOUT'/>");
		out.println("</form>");
		out.close();
	}
}
