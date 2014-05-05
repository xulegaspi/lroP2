/*
//	Xurxo Legaspi Ramos
//	LRO 22
//
//*/
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.servlet.*;
import javax.servlet.http.*;

public class Lro22P2 extends HttpServlet {

    TvmlReader Guia_TV;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        Guia_TV = new TvmlReader();
        String guia = Guia_TV.Read();
    	
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
    	throws IOException, ServletException {

    	String step = request.getParameter("step");
        int step2 = 0;
        if(step.equals("1")) step2 = 1;
        else if(step.equals("2")) step2 = 2;
        else if(step.equals("3")) step2 = 3;
        else if(step.equals("4")) step2 = 4;
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        int cons = 0;
        String consulta = new String();

    	switch (step2) {
    		case 1:
    			consulta = request.getParameter("consulta");
                if(consulta.equals("shows")) cons = 2;
                else cons = 1;

    			switch (cons) {
                    case 1:
                        out.println("<html><head><title>Servicio TV</title>");
                        out.println("</head><body>");
                        out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
                        out.println("<h2>Pel&iacute;culas</h2>");
                        out.println("<h3>Selecciona un d&iacute;a:</h3>");
                        out.println("<form method='POST' action='?step=2'>");
                        out.println("<input type='hidden' name='consulta' value='movies'>");

                        List<String> dias = Guia_TV.getDays();
                        ListIterator<String> it = dias.listIterator();
                        for(int ii=0; ii<dias.size(); ii++){
                            String dia = it.next();
                            if(ii==dias.size()-1){
                                out.println("<input type='radio' name='dia' value='" + dia + "' checked> " + dia + "<BR>");
                            } else {
                                out.println("<input type='radio' name='dia' value='" + dia + "' > " + dia + "<BR>");
                            }
                        }
                        /*
                                out.println("<input type='radio' name='dia' value='01/12/2013'> 01/12/2013<BR>");
                                out.println("<input type='radio' name='dia' value='02/12/2013'> 02/12/2013<BR>");
                                out.println("<input type='radio' name='dia' value='03/12/2013'> 03/12/2013<BR>");
                                out.println("<input type='radio' name='dia' value='04/12/2013'> 04/12/2013<BR>");
                                out.println("<input type='radio' name='dia' value='05/12/2013'  checked> 05/12/2013<BR>");
                        */
                        if(dias.size()!=0)
                            out.println("<p><input type='submit' value='Enviar'>");
                        out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].method=\"GET\"'>");
                        out.println("</form>");
                        out.println("</body></html>");
                        break;
                    case 2:
                        out.println("<html><head><title>Servicio TV</title>");
                        out.println("</head><body>");
                        out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
                        out.println("<h2>shows</h2>");
                        out.println("<h3>Selecciona un idioma:</h3>");
                        out.println("<form method='POST' action='?step=2'>");
                        out.println("<input type='hidden' name='consulta' value='shows'>");

                        List<String> idiomas = Guia_TV.getLanguages();
                        it = idiomas.listIterator();
                        for(int ii=0; ii<idiomas.size(); ii++) {
                            String idioma = it.next();
                            if(ii==idiomas.size()-1) {
                                out.println("<input type='radio' name='idioma' value='" + idioma + "' checked> " + idioma + "<BR>");
                            } else {
                                out.println("<input type='radio' name='idioma' value='" + idioma + "' > " + idioma + "<BR>");
                            }
                        }
                        /*
                            out.println("<input type='radio' name='idioma' value='en'> en<BR>");
                            out.println("<input type='radio' name='idioma' value='fr'> fr<BR>");
                            out.println("<input type='radio' name='idioma' value='it'> it<BR>");
                            out.println("<input type='radio' name='idioma' value='de'> de<BR>");
                            out.println("<input type='radio' name='idioma' value='es'  checked> es<BR>");
                        */
                        if(idiomas.size()!=0)
                            out.println("<p><input type='submit' value='Enviar'>");
                        out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].method=\"GET\"'>");
                        out.println("</form>");
                        out.println("</body></html>");
                        break;
                    default:
                        //404
                        break;
    			}
			break;
    		case 2:
    			consulta = request.getParameter("consulta");
                if(consulta.equals("shows")) cons = 2;
                else cons = 1;
                    switch (cons) {
                        case 1:
                            String dia = request.getParameter("dia");

                            out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
                            out.println("<h2>D&iacute;a:" + dia + "</h2>");
                            out.println("<h3>Selecciona un canal:</h3>");
                            out.println("<form method='POST' action='?step=3'>");
                            out.println("<input type='hidden' name='consulta' value='movies'>");
                            out.println("<input type='hidden' name='dia' value='" + dia + "'>");

                            List<String> canales = Guia_TV.getChannels(dia);
                            ListIterator<String> it = canales.listIterator();
                            for(int ii=0; ii<canales.size(); ii++) {
                                String canal = it.next();
                                out.println("<input type='radio' name='canal' value='" + canal + "' > " + canal + "<BR>");
                                if(ii==canales.size()-1) {
                                    out.println("<input type='radio' name='canal' value='all' checked> Todos<BR>");
                                }
                            }
                            /*
                                out.println("<input type='radio' name='canal' value='TVE'> TVE<BR>");
                                out.println("<input type='radio' name='canal' value='Antena3'> Antena3<BR>");
                                out.println("<input type='radio' name='canal' value='Telecinco'> Telecinco<BR>");
                                out.println("<input type='radio' name='canal' value='todos'  checked> Todos los canales<BR>");
                            */
                            if(canales.size()!=0)
                                out.println("<p><input type='submit' value='Enviar'>");
                            out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].action=\"?step=1\"'>");
                            out.println("<input type='submit' value='Inicio' onClick='document.forms[0].method=\"GET\"'>");
                            out.println("</form>");
                            out.println("</body></html>");
                            break;

                        case 2:
                            String idioma = request.getParameter("idioma");

                            out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
                            out.println("<h2>Idioma:" + idioma + "</h2>");
                            out.println("<h3>Selecciona un d&iacute;a:</h3>");
                            out.println("<form method='POST' action='?step=3'>");
                            out.println("<input type='hidden' name='consulta' value='shows'>");
                            out.println("<input type='hidden' name='idioma' value='" + idioma + "'>");

                            List<String> dias = Guia_TV.getDays();
                            it = dias.listIterator();
                            for(int ii=0; ii<dias.size(); ii++) {
                                dia = it.next();
                                if(ii==dias.size()-1) {
                                    out.println("<input type='radio' name='dia' value='" + dia + "' checked> " + dia + "<BR>");
                                } else {
                                    out.println("<input type='radio' name='dia' value='" + dia + "' > " + dia + "<BR>");
                                }
                            }
                            /*
                                out.println("<input type='radio' name='dia' value='01/12/2013'> 01/12/2013<BR>");
                                out.println("<input type='radio' name='dia' value='02/12/2013'> 02/12/2013<BR>");
                                out.println("<input type='radio' name='dia' value='03/12/2013'> 03/12/2013<BR>");
                                out.println("<input type='radio' name='dia' value='04/12/2013'> 04/12/2013<BR>");
                                out.println("<input type='radio' name='dia' value='05/12/2013'  checked> 05/12/2013<BR>");
                            */
                            if(dias.size()!=0)
                                out.println("<p><input type='submit' value='Enviar'>");
                            out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].action=\"?step=1\"'>");
                            out.println("<input type='submit' value='Inicio' onClick='document.forms[0].method=\"GET\"'>");
                            out.println("</form>");
                            out.println("</body></html>");
                            break;
                        default:
                            // 404
                            break;
        		    }
        		break;
    		case 3:
    			consulta = request.getParameter("consulta");
                if(consulta.equals("shows")) cons = 2;
                else cons = 1;

                    switch (cons) {
                        case 1:
                            String dia = request.getParameter("dia");
                            String canal = request.getParameter("canal");

                            out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
                            out.println("<h2>D&iacute;a: " + dia + ", canal: " + canal + "</h2>");
                            out.println("<h3>Estas son las pel&iacute;culas:</h3>");
                            out.println("<ul>");

                            List<FilmPkg> films = Guia_TV.getFilms(dia, canal);
                            ListIterator<FilmPkg> it2 = films.listIterator();
                            for(int ii=0; ii<films.size(); ii++) {
                                FilmPkg film = it2.next();
                                out.println(" <li>" + film.titulo + " a las " + film.fecha + "<BR>");
                                out.println(film.sinopsis + "<P>");
                            }
                            /*
                                out.println("<li> Pel&iacute;cula 1 a las 12:00<BR>");
                                out.println("Sinopsis de la pel&iacute;cula 1<P>");
                                out.println(" <li> Pel&iacute;cula 2 a las 16:00<BR>");
                                out.println(" Sinopsis de la pel&iacute;cula 2<P>");
                                out.println(" <li> Pel&iacute;cula 3 a las 21:00<BR>");
                                out.println(" Sinopsis de la pel&iacute;cula 3<P>");
                            */
                            out.println("</ul>");
                            out.println("<form method='POST'>");
                            out.println("<input type='hidden' name='consulta' value='movies'>");
                            out.println("<input type='hidden' name='dia' value='" + dia + "'>");
                            out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].action=\"?step=2\"'>");
                            out.println("<input type='submit' value='Inicio' onClick='document.forms[0].method=\"GET\"'>");
                            out.println("</form>");
                            out.println("</body></html>");
                            break;

                        case 2:
                            dia = request.getParameter("dia");
                            String idioma = request.getParameter("idioma");

                            out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
                            out.println("<h2>Idioma: " + idioma + ", d&iacute;a: " + dia + "</h2>");
                            out.println("<h3>Selecciona un canal:</h3>");
                            out.println("<form method='POST' action='?step=4'>");
                            out.println("<input type='hidden' name='consulta' value='shows'>");
                            out.println("<input type='hidden' name='idioma' value='" + idioma + "'>");
                            out.println("<input type='hidden' name='dia' value='" + dia + "'>");

                            List<String> canales = Guia_TV.getChannels(dia, idioma);
                            ListIterator<String> it = canales.listIterator();
                            for(int ii=0; ii<canales.size(); ii++) {
                                canal = it.next();
                                out.println("<input type='radio' name='canal' value='" + canal + "' > " + canal + "<BR>");
                                if(ii==canales.size()-1) {
                                    out.println("<input type='radio' name='canal' value='all' checked> Todos<BR>");
                                }
                            }
                            /*
                                out.println("<input type='radio' name='canal' value='TVE'> TVE<BR>");
                                out.println("<input type='radio' name='canal' value='Antena3'> Antena3<BR>");
                                out.println("<input type='radio' name='canal' value='Telecinco'> Telecinco<BR>");
                                out.println("<input type='radio' name='canal' value='todos'  checked> Todos los canales<BR>");
                            */
                            if(canales.size()!=0)
                                out.println("<p><input type='submit' value='Enviar'>");
                            out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].action=\"?step=2\"'>");
                            out.println("<input type='submit' value='Inicio' onClick='document.forms[0].method=\"GET\"'>");
                            out.println("</form>");
                            out.println("</body></html>");
                            break;
                        default:
                            break;
                        // 404
                    }
    			break;
    		case 4:
    			consulta = request.getParameter("consulta");
                if(consulta.equals("shows")) cons = 2;

                    switch (cons) {
                        case 2:
                            String dia = request.getParameter("dia");
                            String idioma = request.getParameter("idioma");
                            String canal = request.getParameter("canal");

                            out.println("<h1>Servicio de consulta de la programaci&oacute;n</h1>");
                            out.println("<h2>Idioma: " + idioma + ", d&iacute;a: " + dia + " , canal: " + canal + "</h2>");
                            out.println("<h3><h3>Estos son los programas:</h3></h3>");
                            out.println("<ul>");

                            List<ShowPkg> shows = Guia_TV.getShows(dia, canal, idioma);
                            ListIterator<ShowPkg> it = shows.listIterator();
                            for(int ii=0; ii<shows.size(); ii++) {
                                ShowPkg show = it.next();
                                out.println(" <li>" + show.nombre + " a las " + show.fecha + "<BR>");
                                out.println("edad m&iacute;nima " + show.edad + " a&ntildeos. <P>");
                            }
                            /*
                                out.println(" <li> Programa 1 (edad m&iacute;nima 14)<BR>");
                                out.println(" <li> Programa 2 (edad m&iacute;nima 14)<BR>");
                                out.println(" <li> Programa 3 (edad m&iacute;nima 14)<BR>");
                            */
                            out.println("</ul>");
                            out.println("<form method='POST'>");
                            out.println("<input type='hidden' name='consulta' value='shows'>");
                            out.println("<input type='hidden' name='idioma' value='" + idioma + "'>");
                            out.println("<input type='hidden' name='dia' value='" + dia + "'>");
                            out.println("<input type='submit' value='Atr&aacute;s' onClick='document.forms[0].action=\"?step=3\"'>");
                            out.println("<input type='submit' value='Inicio' onClick='document.forms[0].method=\"GET\"'>");
                            out.println("</form>");
                            out.println("</body></html>");
                            break;
                        default:
                        //404
                            break;
                    }
                break;
    		default:
			//404
    			break;
    	}
    }
}
