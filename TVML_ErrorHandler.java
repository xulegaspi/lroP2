import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

class TVML_ErrorHandler extends DefaultHandler {
    public TVML_ErrorHandler () {}
    public void warning(SAXParseException spe) {
        System.out.println("Warning: " + spe.toString());
    }
    public void error(SAXParseException spe) {
        System.out.println("Error: " + spe.toString());
    }
    public void fatalerror(SAXParseException spe) {
        System.out.println("Fatal Error: " + spe.toString());
    }
}