import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class P2 extends HttpServlet {
	
	TvmlReader TvGuide;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
    	//Guia_TV = new TvmlReader();
    	
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        out.println("<html><head><title>Servicio de TV</title>");
        out.println("</head><body>");
        out.println("<div align='center'>");
        out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
        out.println("<h2>Bienvenido a este servicio</h2>");
        out.println("<h3>Selecciona una consulta:</h3>");
        out.println("<form method='POST' action='?step=1'>");
        out.println("<input type='radio' name='consulta' value='shows' checked> Consulta series<br>");
       	out.println("<input type='radio' name='consulta' value='movies'> Consulta Pel&iacute;culas<br>");
        out.println("<p><input type='submit' value='Enviar'>");
        out.println("</div>");
        out.println("</form>");
        out.println("</body></html>");

    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
    	    throws IOException, ServletException
    {
    	String step = request.getParameter("step");
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();

    	//if(step.equals("1")){
    	switch (step) {
    		case "1":
    			String consulta = request.getParameter("consulta");
    			switch (consulta) {
    				case "shows":
    					out.println("<html><head><title>Servicio TV</title>");
       		     		out.println("</head><body>");
       		     		out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
       		     		out.println("<h2>shows</h2>");
       		     		out.println("<h3>Selecciona un idioma:</h3>");
       		     		out.println("<form method='POST' action='?step=2'>");
       		     		out.println("<input type='hidden' name='query' value='shows'>");
       		     		
       		     		out.println("<input type='radio' name='idioma' value='en'> en<BR>");
       		     		out.println("<input type='radio' name='idioma' value='fr'> fr<BR>");
       		     		out.println("<input type='radio' name='idioma' value='it'> it<BR>");
       		     		out.println("<input type='radio' name='idioma' value='de'> de<BR>");
       		     		out.println("<input type='radio' name='idioma' value='es'  checked> es<BR>");
       		     		
       		     		out.println("<p><input type='submit' value='Enviar'>");
       		     		out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].method=\"GET\"'>");
       		     		out.println("</form>");
       		     		out.println("</body></html>");
       		     		break;
    				case "movies":
    					out.println("<html><head><title>Servicio TV</title>");
    					out.println("</head><body>");
    					out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
    					out.println("<h2>Pel&iacute;culas</h2>");
    					out.println("<h3>Selecciona un d&iacute;a:</h3>");
    					out.println("<form method='POST' action='?step=2'>");
    					out.println("<input type='hidden' name='query' value='movies'>");

    					out.println("<input type='radio' name='dia' value='01/12/2013'> 01/12/2013<BR>");
    	                out.println("<input type='radio' name='dia' value='02/12/2013'> 02/12/2013<BR>");
    	                out.println("<input type='radio' name='dia' value='03/12/2013'> 03/12/2013<BR>");
    	                out.println("<input type='radio' name='dia' value='04/12/2013'> 04/12/2013<BR>");
    	                out.println("<input type='radio' name='dia' value='05/12/2013'  checked> 05/12/2013<BR>");

    					out.println("<p><input type='submit' value='Enviar'>");
    					out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].method=\"GET\"'>");
    					out.println("</form>");
    					out.println("</body></html>");
    					break;
    				default:
    					//404
    					break;
    			}
    		case "2":
    			String consulta = request.getParameter("consulta");

        		//if(query.equals("movies")){
        		switch (consulta) {
        			case "movies":
        				String day = request.getParameter("day");
        			
        				out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
        				out.println("<h2>D&iacute;a:" + day + "</h2>");
        				out.println("<h3>Selecciona un canal:</h3>");
        				out.println("<form method='POST' action='?step=3'>");
        				out.println("<input type='hidden' name='query' value='movies'>");
        				out.println("<input type='hidden' name='day' value='" + day + "'>");
        				String[] channels = TvGuide.getChannels();
       		     		for(int ii=0; ii<channels.length; ii++){
       		     			out.println("<input type='radio' name='channel' value='" + channels[ii] + "' > " + channels[ii] + "<BR>");
       		     			if(ii==channels.length-1){
       		     				out.println("<input type='radio' name='channel' value='all' checked> Todos<BR>");
       		     			}
       		     		}	
       		     		out.println("<p><input type='submit' value='Enviar'>");
       		     		out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].action=\"?step=1\"'>");
       		     		out.println("<input type='submit' value='Inicio' onClick='document.forms[0].method=\"GET\"'>");
       		     		out.println("</form>");
       		     		out.println("</body></html>");
       		     		break;
        				//else if(query.equals("shows")){
        			case "shows":
        				String language = request.getParameter("language");
        			
        				out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
        				out.println("<h2>Idioma:" + language + "</h2>");
        				out.println("<h3>Selecciona un d&iacute;a:</h3>");
        				out.println("<form method='POST' action='?step=3'>");
        				out.println("<input type='hidden' name='query' value='shows'>");
        				out.println("<input type='hidden' name='language' value='" + language + "'>");
        				String[] days = TvGuide.getDays();
        				for(int ii=0; ii<days.length; ii++){
        					if(ii==days.length-1){
        						out.println("<input type='radio' name='day' value='" + days[ii] + "' checked> " + days[ii] + "<BR>");
        					}
        					else{
        						out.println("<input type='radio' name='day' value='" + days[ii] + "' > " + days[ii] + "<BR>");
       		     			}
        				}
        				out.println("<p><input type='submit' value='Enviar'>");
        				out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].action=\"?step=1\"'>");
        				out.println("<input type='submit' value='Inicio' onClick='document.forms[0].method=\"GET\"'>");
        				out.println("</form>");
        				out.println("</body></html>");
        				break;
        			default:
        				//do 404
        				break;
        		}
        		break;
    		case "3":
    			String consulta = request.getParameter("consulta");

        		//if(query.equals("movies")){
    			switch (consulta) {
    				case "movies":
    					String day = request.getParameter("day");
    					String channel = request.getParameter("channel");
        			
    					out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
    					out.println("<h2>D&iacute;a: " + day + ", canal: " + channel + "</h2>");
    					out.println("<h3>Estas son las pel&iacute;culas:</h3>");
    					out.println("<ul>");
    					FilmPkg[] films = TvGuide.getFilms();
    					for(int ii=0; ii<films.length; ii++){
    						if(ii==films.length-1){
    							out.println(" <li>" + films[ii].title + " a las " + films[ii].time + "<BR>");
    							out.println(films[ii].synopsis + "<P>");
    						}
    						else{
    							out.println(" <li>" + films[ii].title + " a las " + films[ii].time + "<BR>");
    							out.println(films[ii].synopsis + "<P>");
       		     			}
    					}
    					out.println("</ul>");
    					out.println("<form method='POST'>");
    					out.println("<input type='hidden' name='query' value='movies'>");
    					out.println("<input type='hidden' name='day' value='" + day + "'>");
    					out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].action=\"?step=2\"'>");
    					out.println("<input type='submit' value='Inicio' onClick='document.forms[0].method=\"GET\"'>");
    					out.println("</form>");
    					out.println("</body></html>");
    					break;
        		//        		else if(query.equals("shows")){
    				case "shows":
    					String day = request.getParameter("day");
    					String language = request.getParameter("language");
        			
    					out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
    					out.println("<h2>Idioma: " + language + ", d&iacute;a: " + day + "</h2>");
    					out.println("<h3>Selecciona un canal:</h3>");
    					out.println("<form method='POST' action='?step=4'>");
    					out.println("<input type='hidden' name='query' value='shows'>");
    					out.println("<input type='hidden' name='language' value='" + language + "'>");
    					out.println("<input type='hidden' name='day' value='" + day + "'>");
    					String[] channels = TvGuide.getChannels();
       		     		for(int ii=0; ii<channels.length; ii++){
       		     			out.println("<input type='radio' name='channel' value='" + channels[ii] + "' > " + channels[ii] + "<BR>");
       		     			if(ii==channels.length-1){
       		     				out.println("<input type='radio' name='channel' value='all' checked> Todos<BR>");
       		     			}
       		     		}
       		     		out.println("<p><input type='submit' value='Enviar'>");
       		     		out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].action=\"?step=2\"'>");
       		     		out.println("<input type='submit' value='Inicio' onClick='document.forms[0].method=\"GET\"'>");
       		     		out.println("</form>");
       		     		out.println("</body></html>");
       		     		break;
    				default:
    					break;
        			//do 404
    			}
    			break;
    		case "4":
    			String consulta = request.getParameter("consulta");
        		
        		//if(query.equals("shows")){
    			switch (consulta) {
    				case "shows":
    					String day = request.getParameter("day");
    					String language = request.getParameter("language");
    					String channel = request.getParameter("channel");
    					
    					out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
    					out.println("<h2>Idioma: " + language + ", d&iacute;a: " + day + " , canal: " + channel + "</h2>");
    					out.println("<h3><h3>Estos son los programas:</h3></h3>");
    					out.println("<ul>");
    					ShowPkg[] shows = TvGuide.getShows();
    					for(int ii=0; ii<shows.length; ii++){
    						if(ii==shows.length-1){
    							out.println(" <li>" + shows[ii].name + " a las " + shows[ii].time + "<BR>");
    							out.println("edad m&iacute;nima " + shows[ii].age + " años. <P>");
    						}
    						else{
    							out.println(" <li>" + shows[ii].name + " a las " + shows[ii].time + "<BR>");
    							out.println("edad m&iacute;nima " + shows[ii].age + " años. <P>");
       		     			}
    					}
    					out.println("</ul>");
    					out.println("<form method='POST'>");
    					out.println("<input type='hidden' name='query' value='shows'>");
    					out.println("<input type='hidden' name='language' value='" + language + "'>");
    					out.println("<input type='hidden' name='day' value='" + day + "'>");
    					out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].action=\"?step=3\"'>");
    					out.println("<input type='submit' value='Inicio' onClick='document.forms[0].method=\"GET\"'>");
    					out.println("</form>");
    					out.println("</body></html>");
    					break;
    					//else {
        			//do 404
        		//}
    			//break;
    				default:
    					break;
    			}
    		default:
    			break;
    	}
    }
}
    		/*if(query.equals("movies")){
    			out.println("<html><head><title>Servicio TV</title>");
    		    out.println("</head><body>");
    		    out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
    		    out.println("<h2>Pel&iacute;culas</h2>");
    		    out.println("<h3>Selecciona un d&iacute;a:</h3>");
    		    out.println("<form method='POST' action='?step=2'>");
    		    out.println("<input type='hidden' name='query' value='movies'>");
    		    String[] days = TvGuide.getDays();
    		    for(int ii=0; ii<days.length; ii++){
    		    	if(ii==days.length-1){
        		    	out.println("<input type='radio' name='day' value='" + days[ii] + "' checked> " + days[ii] + "<BR>");
        		    }
    		    	else{
    		    		out.println("<input type='radio' name='day' value='" + days[ii] + "' > " + days[ii] + "<BR>");
   		     		}
    		    }
    		    out.println("<p><input type='submit' value='Enviar'>");
    		    out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].method=\"GET\"'>");
    		    out.println("</form>");
    		    out.println("</body></html>");
    		}
    		else if(query.equals("shows")){
    			out.println("<html><head><title>Servicio TV</title>");
   		     	out.println("</head><body>");
   		     	out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
   		     	out.println("<h2>shows</h2>");
   		     	out.println("<h3>Selecciona un idioma:</h3>");
   		     	out.println("<form method='POST' action='?step=2'>");
   		     	out.println("<input type='hidden' name='query' value='shows'>");
   		     	String[] languages = TvGuide.getLanguages();
   		     	for(int ii=0; ii<languages.length; ii++){
   		     		if(ii==languages.length-1){
   		     			out.println("<input type='radio' name='language' value='" + languages[ii] + "' checked> " + languages[ii] + "<BR>");
   		     		}
   		     		else{
   		     			out.println("<input type='radio' name='language' value='" + languages[ii] + "' > " + languages[ii] + "<BR>");
   		     		}
   		     	}
   		     	out.println("<p><input type='submit' value='Enviar'>");
   		     	out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].method=\"GET\"'>");
   		     	out.println("</form>");
   		     	out.println("</body></html>");
    		}
    		else {
    			//do 404
    		}
    	//}
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
    		    String[] channels = TvGuide.getChannels();
   		     	for(int ii=0; ii<channels.length; ii++){
   		     		out.println("<input type='radio' name='channel' value='" + channels[ii] + "' > " + channels[ii] + "<BR>");
   		     		if(ii==channels.length-1){
   		     			out.println("<input type='radio' name='channel' value='all' checked> Todos<BR>");
   		     		}
   		     	}
    		    out.println("<p><input type='submit' value='Enviar'>");
    		    out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].action=\"?step=1\"'>");
    		    out.println("<input type='submit' value='Inicio' onClick='document.forms[0].method=\"GET\"'>");
    		    out.println("</form>");
    		    out.println("</body></html>");
    		}
    		else if(query.equals("shows")){
    			String language = request.getParameter("language");
    			
    			out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
    		    out.println("<h2>Idioma:" + language + "</h2>");
    		    out.println("<h3>Selecciona un d&iacute;a:</h3>");
    		    out.println("<form method='POST' action='?step=3'>");
    		    out.println("<input type='hidden' name='query' value='shows'>");
    		    out.println("<input type='hidden' name='language' value='" + language + "'>");
    		    String[] days = TvGuide.getDays();
    		    for(int ii=0; ii<days.length; ii++){
    		    	if(ii==days.length-1){
        		    	out.println("<input type='radio' name='day' value='" + days[ii] + "' checked> " + days[ii] + "<BR>");
        		    }
    		    	else{
    		    		out.println("<input type='radio' name='day' value='" + days[ii] + "' > " + days[ii] + "<BR>");
   		     		}
    		    }
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
    		    FilmPkg[] films = TvGuide.getFilms();
    		    for(int ii=0; ii<films.length; ii++){
    		    	if(ii==films.length-1){
        		    	out.println(" <li>" + films[ii].title + " a las " + films[ii].time + "<BR>");
        		    	out.println(films[ii].synopsis + "<P>");
        		    }
    		    	else{
    		    		out.println(" <li>" + films[ii].title + " a las " + films[ii].time + "<BR>");
        		    	out.println(films[ii].synopsis + "<P>");
   		     		}
    		    }
    		    out.println("</ul>");
    		    out.println("<form method='POST'>");
    		    out.println("<input type='hidden' name='query' value='movies'>");
    		    out.println("<input type='hidden' name='day' value='" + day + "'>");
    		    out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].action=\"?step=2\"'>");
    		    out.println("<input type='submit' value='Inicio' onClick='document.forms[0].method=\"GET\"'>");
    		    out.println("</form>");
    		    out.println("</body></html>");
    		}
    		else if(query.equals("shows")){
    			String day = request.getParameter("day");
    			String language = request.getParameter("language");
    			
    			out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
    		    out.println("<h2>Idioma: " + language + ", d&iacute;a: " + day + "</h2>");
    		    out.println("<h3>Selecciona un canal:</h3>");
    		    out.println("<form method='POST' action='?step=4'>");
    		    out.println("<input type='hidden' name='query' value='shows'>");
    		    out.println("<input type='hidden' name='language' value='" + language + "'>");
    		    out.println("<input type='hidden' name='day' value='" + day + "'>");
    		    String[] channels = TvGuide.getChannels();
   		     	for(int ii=0; ii<channels.length; ii++){
   		     		out.println("<input type='radio' name='channel' value='" + channels[ii] + "' > " + channels[ii] + "<BR>");
   		     		if(ii==channels.length-1){
   		     			out.println("<input type='radio' name='channel' value='all' checked> Todos<BR>");
   		     		}
   		     	}
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
    		
    		if(query.equals("shows")){
    			String day = request.getParameter("day");
    			String language = request.getParameter("language");
    			String channel = request.getParameter("channel");
    			
    			out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
    		    out.println("<h2>Idioma: " + language + ", d&iacute;a: " + day + " , canal: " + channel + "</h2>");
    		    out.println("<h3><h3>Estos son los programas:</h3></h3>");
    		    out.println("<ul>");
    		    ShowPkg[] shows = TvGuide.getShows();
    		    for(int ii=0; ii<shows.length; ii++){
    		    	if(ii==shows.length-1){
        		    	out.println(" <li>" + shows[ii].name + " a las " + shows[ii].time + "<BR>");
        		    	out.println("edad m&iacute;nima " + shows[ii].age + " años. <P>");
        		    }
    		    	else{
    		    		out.println(" <li>" + shows[ii].name + " a las " + shows[ii].time + "<BR>");
        		    	out.println("edad m&iacute;nima " + shows[ii].age + " años. <P>");
   		     		}
    		    }
    		    out.println("</ul>");
    		    out.println("<form method='POST'>");
    		    out.println("<input type='hidden' name='query' value='shows'>");
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
}*/

