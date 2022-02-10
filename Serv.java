import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class Serv extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String requestUrl = request.getRequestURI();
		String name = requestUrl.substring("/movements/".length());
		
		Movement movement = Data.getInstance().GetMovement(name).getString("name");
		
		if(movement != null){
			String json = "{\n";
			json += "\"name\": " + JSONObject.quote(movement.getName()) + ",\n";
			json += "\"about\": " + JSONObject.quote(movement.getAbout()) + ",\n";
			json += "\"birthYear\": " + movement.getBirthYear() + "\n";
			json += "}";
			response.getOutputStream().println(json);
		}
		else{
			//That  wasn't found, so return an empty JSON object. We could also return an error.
			response.getOutputStream().println("{}");
		}
	}
	
	

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String name = request.getParameter("name");
		String about = request.getParameter("about");
		int birthYear = Integer.parseInt(request.getParameter("birthYear"));
		
		//Data.getInstance().putPerson(new Person(name, about, birthYear, password));
	}
}
