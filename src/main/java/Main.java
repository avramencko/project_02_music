import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.ContentHandler;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException, TikaException, SAXException {
        String path = "D://Музыка/ccc";
        System.out.println(path);
        Cataloger cataloger = new Cataloger(path);
        cataloger.parseDirectories();
    }
}
