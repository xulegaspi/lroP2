package default

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class P2 extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
	                  HttpServletResponse response)
	          throws ServletException, IOException
	{
	    // Set response content type
	    response.setContentType("text/html");
	    // Actual logic goes here.
	    PrintWriter out = response.getWriter();
    	out.println("<h1>" + "helo world" + "</h1>");
	}
}
