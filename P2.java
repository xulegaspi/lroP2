import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class P2 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><head><title>Servicio de TV</title>");
        out.println("</head><body>");
        out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
        out.println("<h2>Bienvenido a este servicio</h2>");
        out.println("<h3>Selecciona una consulta:</h3>");
        out.println("<form method='POST' action='?fase=1'>");
        out.println("<input type='radio' name='consulta' value='1' checked> Consulta 1<br>");
       	out.println("<input type='radio' name='consulta' value='2'> Consulta 2<br>");
        out.println("<p><input type='submit' value='Enviar'>");
        out.println("</form>");
        out.println("</body></html>");

    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response)
    	    throws IOException, ServletException
    {
    	out.println("<h1>POST</h1>");
    }
}
