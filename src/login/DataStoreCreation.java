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
//import com.google.appengine.api.datastore.Query.FilterOperator;
//import com.google.appengine.api.datastore.Query.FilterPredicate;

public class DataStoreCreation extends HttpServlet {
	Logger log = Logger.getLogger(DataStoreCreation.class.getName());
	public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
try{
	//ArrayList<String> d = new ArrayList<String>();
	//String[] task = 
			//req.getParameterValues("array");
	//d.add(task);
			
	//System.out.println(d);
	//log.info("Entered into the method to create");
		String task = req.getParameter("task");
		System.out.println(task);
		String date = req.getParameter("myDate");
		HttpSession session = req.getSession();
		session.setAttribute("date", date);
		String[] ToDo = req.getParameterValues("todo");
		session.setAttribute("ToDo", ToDo);
		String[] Doing = req.getParameterValues("doing");
		session.setAttribute("Doing", Doing);
		String[] Done = req.getParameterValues("done");
		session.setAttribute("Done", Done);

		log.info("value of todo ::"+ToDo);
		log.info("value of doing ::"+Doing);
		log.info("value of done ::"+Done);
		//String state = req.getParameter("state");
		HttpSession session1 = req.getSession(false);
		String mailID = (String) session1.getAttribute("mail");

		res.setContentType("text/html");
		Entity e = new Entity("Schedular",mailID);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		/*/Query q = new Query("TO-DO");
		//PreparedQuery pq = datastore.prepare(q);
		//Entity result = pq.asSingleEntity();*/
		
		e.setProperty("Email", mailID);
		e.setProperty("Date", date);
		e.setProperty("ToDo", ToDo);
		e.setProperty("Doing", Doing);
		e.setProperty("Done", Done);
		
		datastore.put(e);
		req.getRequestDispatcher("/index.html").forward(req, res);
}
catch(Exception e)
{
	log.warning("Exceptione"+e.getMessage());
	for(StackTraceElement se:e.getStackTrace())
		System.out.println(se.toString());
	
}

	}

}
