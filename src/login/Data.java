package login;

public class Data {

	
	public String State, todoTask,date, Email;

	public String getDate() {
		return date;
	}
	public String getEmail() {
		return Email;
	}
	
	public String setEmail(String Email) {
		Email = this.Email;
		return Email;
	}


	public String setDate(String date) {
		date = this.date;
		return date;
	}

	public String getTask() {
		return todoTask;
	}

	public String setTask(String todoTask) {
		todoTask = this.todoTask;
		return todoTask;
	}
	
	public String getState()
	{
		return State;
	}
	
	public String setString(String State)
	{
		State=this.State;
		return State;
	}
	
}
