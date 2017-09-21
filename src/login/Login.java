package login;

import login.Validate;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class Login extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		String user = req.getParameter("user");
		HttpSession session = req.getSession();
		session.setAttribute("name", user);
		String password = req.getParameter("pwd");
		String mailID = req.getParameter("mail");
		HttpSession session1 = req.getSession();
		session.setAttribute("mail", mailID);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Query q = new Query("User").setFilter(new FilterPredicate("userEmail", FilterOperator.EQUAL, mailID));
		PreparedQuery pq = datastore.prepare(q);
		com.google.appengine.api.datastore.Entity regUser = pq.asSingleEntity();
		if(regUser!= null)
		{
		String regPwd = (String) regUser.getProperty("passWord");

		String regUserName = (String) regUser.getProperty("userName");
		// String regUserName = (String) regUser.getProperty("userName");
		HttpSession s = req.getSession();
		s.setAttribute("user", regUserName);
		
		if (regPwd.equals(password)) {
			req.getRequestDispatcher("/todoList.html").include(req, res);
		} else {
			out.print("<h2><center> Enter correct password</center><h2>");
			req.getRequestDispatcher("/index.html").forward(req, res);

		}
		}
		else
		{
			out.print("<h2><center>Enter correct mail ID</center><h2>");
			req.getRequestDispatcher("/index.html").forward(req, res);	
		}

		out.close();
	}

}
