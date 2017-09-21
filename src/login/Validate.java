package login;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.apphosting.datastore.EntityV4.Key;

public class Validate extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		String n = req.getParameter("name");
		String p = req.getParameter("pwd");
		String c = req.getParameter("pwd1");
		String e = req.getParameter("mail");
		
		if (!c.equals(p)) {
			out.println("<h3><center>Enter correct password</center></h3>");
			req.getRequestDispatcher("SignUp.html").forward(req, res);
		} else {

			Entity u = new Entity("User", e);
			DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
			Query q = new Query("User").setFilter(new FilterPredicate("userEmail", FilterOperator.EQUAL, e));
			PreparedQuery pq = datastore.prepare(q);
			Entity result = pq.asSingleEntity();
			if (result == null) {
				u.setProperty("userName", n);
				u.setProperty("passWord", p);
				u.setProperty("userEmail", e);

				datastore.put(u);

				u.getKey().getName();
				com.google.appengine.api.datastore.Key k = KeyFactory.createKey("User", e);

				HttpSession s = req.getSession();
				s.setAttribute("user", n);

				out.println("<h3><center>WELCOME </center></h3>");
				req.getRequestDispatcher("/todoList.html").include(req, res);
			} else {
				out.println("<h3><center>Email id is already present. \n </center><h3>");
				out.println("<h3><center>Please login</center><h3>\n\n");
				req.getRequestDispatcher("/index.html").forward(req, res);
			}
		}

		out.close();
	}
}