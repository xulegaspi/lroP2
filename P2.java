import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class P2 extends HttpServlet {
	
	TvmlReader TvGuide;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
    	TvGuide = new TvGuide();
    	
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><head><title>Servicio de TV</title>");
        out.println("</head><body>");
        out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
        out.println("<h2>Bienvenido a este servicio</h2>");
        out.println("<h3>Selecciona lo que quieres buscar:</h3>");
        out.println("<form method='POST' action='?step=1'>");
        out.println("<input type='radio' name='query' value='series' checked> Consulta Series<br>");
       	out.println("<input type='radio' name='query' value='movies'> Consulta Pel&iacute;culas<br>");
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
    		if(query.equals("movies")){
    			out.println("<html><head><title>Servicio TV</title>");
    		    out.println("</head><body>");
    		    out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
    		    out.println("<h2>Pel&iacute;culas</h2>");
    		    out.println("<h3>Selecciona un d&iacute;a:</h3>");
    		    out.println("<form method='POST' action='?step=2'>");
    		    out.println("<input type='hidden' name='query' value='movies'>");
    		    String[] days = TvGuide.getDays();
    		    for(int ii=1; ii<days.lengt; ii++){
    		    	out.println("<input type='radio' name='day' value='" + days[ii] + "' > " + days[ii] + "<BR>");
    		    }
    		    out.println("<input type='radio' name='day' value='05/12/2013' checked> 05/12/2013<BR>");
    		    out.println("<p><input type='submit' value='Enviar'>");
    		    out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].method=\"GET\"'>");
    		    out.println("</form>");
    		    out.println("</body></html>");
    		}
    		else if(query.equals("series")){
    			out.println("<html><head><title>Servicio TV</title>");
   		     	out.println("</head><body>");
   		     	out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
   		     	out.println("<h2>Series</h2>");
   		     	out.println("<h3>Selecciona un idioma:</h3>");
   		     	out.println("<form method='POST' action='?step=2'>");
   		     	out.println("<input type='hidden' name='query' value='series'>");
   		     	out.println("<input type='radio' name='language' value='en' > en<BR>");
   		     	out.println("<input type='radio' name='language' value='fr' > fr<BR>");
   		     	out.println("<input type='radio' name='language' value='it' > it<BR>");
   		     	out.println("<input type='radio' name='language' value='de' > de<BR>");
   		     	out.println("<input type='radio' name='language' value='es' checked> es<BR>");
   		     	out.println("<p><input type='submit' value='Enviar'>");
   		     	out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].method=\"GET\"'>");
   		     	out.println("</form>");
   		     	out.println("</body></html>");
    		}
    		else {
    			//do 404
    		}
    	}
    	else if(step.equals("2")){
    		String query = request.getParameter("query");

    		if(query.equals("movies")){
    			String day = request.getParameter("day");
    			
    		    out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
    		    out.println("<h2>D&iacute;a:" + day + "</h2>");
    		    out.println("<h3>Selecciona un canal:</h3>");
    		    out.println("<form method='POST' action='?step=3'>");
    		    out.println("<input type='hidden' name='query' value='movies'>");
    		    out.println("<input type='hidden' name='day' value='" + day + "'>");
    		    out.println("<input type='radio' name='channel' value='TVE' > TVE<BR>");
    		    out.println("<input type='radio' name='channel' value='Antena3' > Antena3<BR>");
    		    out.println("<input type='radio' name='channel' value='Telecinco' > Telecinco<BR>");
    		    out.println("<input type='radio' name='channel' value='todos' checked> Todos los canales<BR>");
    		    out.println("<p><input type='submit' value='Enviar'>");
    		    out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].action=\"?step=1\"'>");
    		    out.println("<input type='submit' value='Inicio' onClick='document.forms[0].method=\"GET\"'>");
    		    out.println("</form>");
    		    out.println("</body></html>");
    		}
    		else if(query.equals("series")){
    			String language = request.getParameter("language");
    			
    			out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
    		    out.println("<h2>Idioma:" + language + "</h2>");
    		    out.println("<h3>Selecciona un d&iacute;a:</h3>");
    		    out.println("<form method='POST' action='?step=3'>");
    		    out.println("<input type='hidden' name='query' value='series'>");
    		    out.println("<input type='hidden' name='language' value='" + language + "'>");
    		    out.println("<input type='radio' name='day' value='01/12/2013' > 01/12/2013<BR>");
    		    out.println("<input type='radio' name='day' value='02/12/2013' > 02/12/2013<BR>");
    		    out.println("<input type='radio' name='day' value='03/12/2013' > 03/12/2013<BR>");
    		    out.println("<input type='radio' name='day' value='04/12/2013' > 04/12/2013<BR>");
    		    out.println("<input type='radio' name='day' value='05/12/2013' checked> 05/12/2013<BR>");
    		    out.println("<p><input type='submit' value='Enviar'>");
    		    out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].action=\"?step=1\"'>");
    		    out.println("<input type='submit' value='Inicio' onClick='document.forms[0].method=\"GET\"'>");
    		    out.println("</form>");
    		    out.println("</body></html>");
    		}
    		else {
    			//do 404
    		}
    	}
    	else if(step.equals("3")){
    		String query = request.getParameter("query");

    		if(query.equals("movies")){
    			String day = request.getParameter("day");
    			String channel = request.getParameter("channel");
    			
    			out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
    		    out.println("<h2>D&iacute;a: " + day + ", canal: " + channel + "</h2>");
    		    out.println("<h3>Estas son las pel&iacute;culas:</h3>");
    		    out.println("<ul>");
    		    out.println("<li> Pel&iacute;cula 1 a las 12:00<BR>");
    		    out.println("Sinopsis de la pel&iacute;cula 1<P>");
    		    out.println("<li> Pel&iacute;cula 2 a las 16:00<BR>");
    		    out.println("Sinopsis de la pel&iacute;cula 2<P>");
    		    out.println("<li> Pel&iacute;cula 3 a las 21:00<BR>");
    		    out.println("Sinopsis de la pel&iacute;cula 3<P>");
    		    out.println("</ul>");
    		    out.println("<form method='POST'>");
    		    out.println("<input type='hidden' name='query' value='movies'>");
    		    out.println("<input type='hidden' name='day' value='" + day + "'>");
    		    out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].action=\"?step=2\"'>");
    		    out.println("<input type='submit' value='Inicio' onClick='document.forms[0].method=\"GET\"'>");
    		    out.println("</form>");
    		    out.println("</body></html>");
    		}
    		else if(query.equals("series")){
    			String day = request.getParameter("day");
    			String language = request.getParameter("language");
    			
    			out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
    		    out.println("<h2>Idioma: " + language + ", d&iacute;a: " + day + "</h2>");
    		    out.println("<h3>Selecciona un canal:</h3>");
    		    out.println("<form method='POST' action='?step=4'>");
    		    out.println("<input type='hidden' name='query' value='series'>");
    		    out.println("<input type='hidden' name='language' value='" + language + "'>");
    		    out.println("<input type='hidden' name='day' value='" + day + "'>");
    		    out.println("<input type='radio' name='channel' value='TVE' > TVE<BR>");
    		    out.println("<input type='radio' name='channel' value='Antena3' > Antena3<BR>");
    		    out.println("<input type='radio' name='channel' value='Telecinco' > Telecinco<BR>");
    		    out.println("<input type='radio' name='channel' value='todos' checked> Todos los canales<BR>");
    		    out.println("<p><input type='submit' value='Enviar'>");
    		    out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].action=\"?step=2\"'>");
    		    out.println("<input type='submit' value='Inicio' onClick='document.forms[0].method=\"GET\"'>");
    		    out.println("</form>");
    		    out.println("</body></html>");
    		}
    		else {
    			//do 404
    		}
    	}
    	else if(step.equals("4")){
    		String query = request.getParameter("query");
    		
    		if(query.equals("series")){
    			String day = request.getParameter("day");
    			String language = request.getParameter("language");
    			String channel = request.getParameter("channel");
    			
    			out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
    		    out.println("<h2>Idioma: " + language + ", d&iacute;a: " + day + " , canal: " + channel + "</h2>");
    		    out.println("<h3><h3>Estos son los programas:</h3></h3>");
    		    out.println("<ul>");
    		    out.println("<li> Programa 1 (edad m&iacute;nima 14)<BR>");
    		    out.println("<li> Programa 2 (edad m&iacute;nima 14)<BR>");
    		    out.println("<li> Programa 3 (edad m&iacute;nima 14)<BR>");
    		    out.println("</ul>");
    		    out.println("<form method='POST'>");
    		    out.println("<input type='hidden' name='query' value='series'>");
    		    out.println("<input type='hidden' name='language' value='" + language + "'>");
    		    out.println("<input type='hidden' name='day' value='" + day + "'>");
    		    out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].action=\"?step=3\"'>");
    		    out.println("<input type='submit' value='Inicio' onClick='document.forms[0].method=\"GET\"'>");
    		    out.println("</form>");
    		    out.println("</body></html>");
    		}
    		else {
    			//do 404
    		}
    	}
    }
}
