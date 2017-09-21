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
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class ex extends HttpServlet {
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();

		String date = req.getParameter("date");
		HttpSession session = req.getSession();
		session.setAttribute("date", date);
		String task = req.getParameter("task");
		String state = req.getParameter("state");
		// String mailID = req.getParameter("mail");
		HttpSession session1 = req.getSession(false);
		String mailID = (String) session1.getAttribute("mail");

		res.setContentType("text/html");
		Entity e = new Entity("TO-DO");
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		Query q = new Query("TO-DO");
		PreparedQuery pq = datastore.prepare(q);
		Entity result = pq.asSingleEntity();
		e.setProperty("Email", mailID);
		e.setProperty("Date", date);
		e.setProperty("State", state);
		e.setProperty("Task", task);
		datastore.put(e);


	}
}
