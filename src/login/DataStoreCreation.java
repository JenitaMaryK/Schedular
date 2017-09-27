package login;

import login.Login;
import login.Data;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class DataStoreCreation extends HttpServlet {
	Logger log = Logger.getLogger(DataStoreCreation.class.getName());
	public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
try{
		//String task = req.getParameter("task");
		//System.out.println(task);
		String date = req.getParameter("Date");
		//HttpSession session = req.getSession();
		//session.setAttribute("date", date);
		String Task = req.getParameter("value");
		//session.setAttribute("ToDo", ToDo);
		String ID = req.getParameter("id");
		String Status = req.getParameter("Status");
		//session.setAttribute("Doing", Doing);
		//String[] Done = req.getParameterValues("Date");
		//session.setAttribute("Done", Done);

		//log.info("value of todo ::"+ToDo);
		//log.info("value of doing ::"+Doing);
		//log.info("value of done ::"+Done);
		//String state = req.getParameter("state");
		HttpSession session1 = req.getSession(false);
		String mailID = (String) session1.getAttribute("mail");

		res.setContentType("text/html");
		Entity e = new Entity("Schedular");
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		//Query q = new Query("Schedular").setFilter(new FilterPredicate("Tasks", FilterOperator.EQUAL, ));
/*
		Query q = new Query("Schedular").setFilter(new FilterPredicate("Status", FilterOperator.EQUAL, "todo"));
		PreparedQuery pq = datastore.prepare(q);
		Entity result = pq.asSingleEntity();
		Key k=result.getKey();
		Entity en=datastore.get(k);
		en.setProperty(Status, "todo");
		datastore.put(en);
		//String Status;
	*/	
		e.setProperty("Email", mailID);
		e.setProperty("Date", date);
		e.setProperty("Task", Task);
		e.setProperty("Status", Status);
		//e.setProperty("Done", Done);
		datastore.put(e);
		/*
		Query q = new Query("TaskTable").setFilter(new FilterPredicate("Tasks", FilterOperator.EQUAL, listvalue));
		PreparedQuery pq = datastore.prepare(q);
		Entity result = pq.asSingleEntity();
		Key k=result.getKey();
		Entity emp = datastore.get(k);
		emp.setProperty("Status", "DO");
		datastore.put(emp);
		*/
		//req.getRequestDispatcher("/Home.html").forward(req, res);
}
catch(Exception e)
{
	log.warning("Exceptione"+e.getMessage());
	for(StackTraceElement se:e.getStackTrace())
		System.out.println(se.toString());
	
}

	}

}