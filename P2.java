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
        out.println("<h3>Selecciona lo que quieres buscar:</h3>");
        out.println("<form method='POST' action='?step=1'>");
        out.println("<input type='radio' name='query' value='series' checked> Consulta Series<br>");
       	out.println("<input type='radio' name='query' value='films'> Consulta Pel&iacute;culas<br>");
        out.println("<p><input type='submit' value='Enviar'>");
        out.println("</form>");
        out.println("</body></html>");

    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    	    throws IOException, ServletException
    {
    	String step = request.getParameter("step");
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();
    	
    	if(step.equals("1")){
    		String query = request.getParameter("query");
    		if(query.equals("series")){
    			out.println("<html><head><title>Servicio TV</title>");
    		    out.println("</head><body>");
    		    out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
    		    out.println("<h2>Series</h2>");
    		    out.println("<h3>Selecciona un d&iacute;a:</h3>");
    		    out.println("<form method='POST' action='?step=2'>");
    		    out.println("<input type='hidden' name='query' value='series'>");
    		    out.println("<input type='radio' name='day' value='01/12/2013' checked> 01/12/2013<BR>");
    		    out.println("<input type='radio' name='day' value='02/12/2013' checked> 02/12/2013<BR>");
    		    out.println("<input type='radio' name='day' value='03/12/2013' checked> 03/12/2013<BR>");
    		    out.println("<input type='radio' name='day' value='04/12/2013' checked> 04/12/2013<BR>");
    		    out.println("<input type='radio' name='day' value='05/12/2013' checked> 05/12/2013<BR>");
    		    out.println("<p><input type='submit' value='Enviar'>");
    		    out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].method=\"GET\"'>");
    		    out.println("</form>");
    		    out.println("</body></html>");
    		}
    		else if(query.equals("films")){
    			out.println("<html><head><title>Servicio TV</title>");
   		     	out.println("</head><body>");
   		     	out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
   		     	out.println("<h2>Pel&iacute;culas</h2>");
   		     	out.println("<h3>Selecciona un idioma:</h3>");
   		     	out.println("<form method='POST' action='?step=2'>");
   		     	out.println("<input type='hidden' name='query' value='films'>");
   		     	out.println("<input type='radio' name='idioma' value='en' checked> en<BR>");
   		     	out.println("<input type='radio' name='idioma' value='fr' checked> fr<BR>");
   		     	out.println("<input type='radio' name='idioma' value='it' checked> it<BR>");
   		     	out.println("<input type='radio' name='idioma' value='de' checked> de<BR>");
   		     	out.println("<input type='radio' name='idioma' value='es' checked> es<BR>");
   		     	out.println("<p><input type='submit' value='Enviar'>");
   		     	out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].method=\"GET\"'>");
   		     	out.println("</form>");
   		     	out.println("</body></html>");
    		}
    		else {
    			//do 404
    		}
    	}
    	else if(step.equals(2)){
    		String query = request.getParameter("query");
    		out.println(query);
    		if(query.equals("series")){
    			String day = request.getParameter("day");
    			out.println("<html><head><title>Servicio TV</title>");
    		    out.println("</head><body>");
    		    out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
    		    out.println("<h2>D&iacute;a:" + day + "</h2>");
    		    out.println("<h3>Selecciona un canal:</h3>");
    		    out.println("<form method='POST' action='?step=3'>");
    		    out.println("<input type='hidden' name='query' value='series'>");
    		    out.println("<input type='hidden' name='day' value='" + day + "'>");
    		    out.println("<input type='radio' name='channel' value='TVE' checked> TVE<BR>");
    		    out.println("<input type='radio' name='channel' value='Antena3' checked> Antena3<BR>");
    		    out.println("<input type='radio' name='channel' value='Telecinco' checked> Telecinco<BR>");
    		    out.println("<input type='radio' name='channel' value='todos' checked> Todos los canales<BR>");
    		    out.println("<p><input type='submit' value='Enviar'>");
    		    out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].action=\"?fase=1&consulta=1\"'>");
    		    out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].method=\"GET\"'>");
    		    out.println("</form>");
    		    out.println("</body></html>");
    		}
    	}
    }
}
