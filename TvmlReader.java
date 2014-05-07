import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


public class TvmlReader {
    List<Document> DOMList;
    private List<String> idiomas;
    private List<String> dias;

    private String url;

    void TvmlReader(){}

    String Read() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setValidating(true);
            DocumentBuilder db = dbf.newDocumentBuilder();
            db.setErrorHandler(new TVML_ErrorHandler());
            DOMList = new ArrayList<Document>();
            idiomas = new ArrayList<String>();
            dias = new ArrayList<String>();

//            Document doc = db.parse("http://localhost:8022/lro22/tvml-1.xml");
            Document doc = db.parse("http://clave.det.uvigo.es:8080/~lroprof/13-14/tvml14-20-06.xml");
            DOMList.add(doc);

            ListIterator<Document> it = DOMList.listIterator();
            int ii=0;
            for (ii=0; ii < DOMList.size(); ii++) {
                it = DOMList.listIterator(ii);
                doc = it.next();
                NodeList canales = doc.getElementsByTagName("Canal");
                dias.add(doc.getDocumentElement().getElementsByTagName("Fecha").item(0).getTextContent());

                for (int jj = 0; jj < canales.getLength(); jj++) {
                    Element canal = (Element) canales.item(jj);

                    // create languages list
                    String[] langl = canal.getAttribute("lang").toString().split("\\ ");
                    for(int ii=0; ii<langl.length; ii++) {
                        ListIterator<String> it = idiomas.listIterator();
                        boolean included = false;
                        for(int jj=0; jj < idiomas.size(); jj++) {
                            if(it.next().equals(langl[ii])) {
                                included = true;
                            }
                        }
                        if(!included) idiomas.add(langl[ii]);
                    }

                    // search next tvml
                    NodeList nlUrl = canal.getElementsByTagName("UrlTVML");
                    if (nlUrl.getLength() > 0) {
                        url = nlUrl.item(0).getTextContent();
                        try {
                            doc = db.parse(url);
                            DOMList.add(doc);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            url = "no doc found";
                        }
                    }
                }
            }
            return "readed" + ii;

        } catch(Exception e) {
            final StringWriter sw = new StringWriter();
            final PrintWriter pw = new PrintWriter(sw, true);
            e.printStackTrace(pw);
            return sw.getBuffer().toString();
        }
    }

    List<String> getDias() {
        return dias;
    }

    List<String> getIdiomas() {
        return idiomas;
    }

    List<String> getCanales(String dia) {
        List<String> channelList = new ArrayList<String>();
        ListIterator<String> it = dias.listIterator();
        for(int ii=0; ii< dias.size(); ii++) {
            if(it.next().equals(dia)) {
                ListIterator<Document> docIt = DOMList.listIterator(ii);
                NodeList canales = docIt.next().getElementsByTagName("Canal");
                for(int jj=0; jj<canales.getLength(); jj++) {
                    Element eChannel = (Element)canales.item(jj);
                    channelList.add(eChannel.getElementsByTagName("NombreCanal").item(0).getTextContent());
                }
                return channelList;
            }
        }
        return channelList;
    }

    List<String> getCanales(String dia, String lang) {
        List<String> channelList = new ArrayList<String>();
        ListIterator<String> it = dias.listIterator();
        for(int ii=0; ii< dias.size(); ii++) {
            if(it.next().equals(dia)) {
                ListIterator<Document> docIt = DOMList.listIterator(ii);
                NodeList canales = docIt.next().getElementsByTagName("Canal");
                for(int jj=0; jj<canales.getLength(); jj++) {
                    Element eChannel = (Element)canales.item(jj);
                    if(eChannel.getAttribute("lang").equals(lang) || lang.equals("all")) {
                        channelList.add(eChannel.getElementsByTagName("NombreCanal").item(0).getTextContent());
                    }
                }
                return channelList;
            }
        }
        return channelList;
    }

    List<FilmPkg> getFilms(String dia, String canal) {
        List<FilmPkg> filmList = new ArrayList<FilmPkg>();
        ListIterator<String> it = dias.listIterator();
        for(int ii=0; ii< dias.size(); ii++) {
            if(it.next().equals(dia)) {
                ListIterator<Document> docIt = DOMList.listIterator(ii);
                NodeList lChannels = docIt.next().getElementsByTagName("Canal");
                for(int jj=0; jj<lChannels.getLength(); jj++) {
                    Element eChannel = (Element)lChannels.item(jj);
                    String sChannel = eChannel.getElementsByTagName("NombreCanal").item(0).getTextContent();
                    if(sChannel.equals(canal) || canal.equals("all")) {
                        NodeList lPrograms = eChannel.getElementsByTagName("Programa");
                        for(int ij=0; ij<lPrograms.getLength(); ij++) {
                            Element eFilm = (Element)lPrograms.item(ij);
                            String category = eFilm.getElementsByTagName("Categoria").item(0).getTextContent();
                            if(category.equals("Cine")) {
                                FilmPkg film = new FilmPkg();
                                film.titulo = eFilm.getElementsByTagName("NombrePrograma").item(0).getTextContent();
                                Element eIntervalo = (Element)eFilm.getElementsByTagName("Intervalo").item(0);
                                film.fecha = eIntervalo.getElementsByTagName("HoraInicio").item(0).getTextContent();

                                Element eFilmCp = (Element)eFilm.cloneNode(true);
                                eFilmCp.getElementsByTagName("Categoria").item(0).setTextContent("");
                                eFilmCp.getElementsByTagName("NombrePrograma").item(0).setTextContent("");
                                ((Element)eFilmCp.getElementsByTagName("Intervalo").item(0)).setTextContent("");

                                film.sinopsis = eFilmCp.getTextContent();
                                filmList.add(film);
                            }
                        }
                    }
                }
                return filmList;
            }
        }
        return filmList;
    }

    List<ShowPkg> getShows(String dia, String canal, String lang) {
        List<ShowPkg> showList = new ArrayList<ShowPkg>();
        ListIterator<String> it = dias.listIterator();
        for(int ii=0; ii< dias.size(); ii++) {
            if(it.next().equals(dia)) {
                ListIterator<Document> docIt = DOMList.listIterator(ii);
                NodeList lChannels = docIt.next().getElementsByTagName("Canal");
                for(int jj=0; jj<lChannels.getLength(); jj++) {
                    Element eChannel = (Element)lChannels.item(jj);
                    String sChannel = eChannel.getElementsByTagName("NombreCanal").item(0).getTextContent();
                    if((sChannel.equals(canal) || canal.equals("all")) &&
                            (eChannel.getAttribute("lang").equals(lang) || lang.equals("all") )) {
                        NodeList lPrograms = eChannel.getElementsByTagName("Programa");
                        for(int ij=0; ij<lPrograms.getLength(); ij++) {
                            Element eShow = (Element)lPrograms.item(ij);
                            ShowPkg show = new ShowPkg();
                            show.nombre = eShow.getElementsByTagName("NombrePrograma").item(0).getTextContent();
                            Element eIntervalo = (Element)eShow.getElementsByTagName("Intervalo").item(0);
                            show.fecha = eIntervalo.getElementsByTagName("HoraInicio").item(0).getTextContent();
                            show.edad = eShow.getAttribute("edadminima");

                            showList.add(show);
                        }
                    }
                }
                return showList;
            }
        }
        return showList;
    }
}