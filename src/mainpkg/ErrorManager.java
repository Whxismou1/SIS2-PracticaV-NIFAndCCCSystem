package mainpkg;

import Entities.Contribuyente;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author maxim
 */
public class ErrorManager {
    public void ErrorManagerCCC(List<Contribuyente> lista) {
        try {
            Element cuentas = new Element("Cuentas");
            Document doc = new Document(cuentas);
            
            for (int i = 0; i < lista.size(); i++) {
                Contribuyente contr = lista.get(i);
                //falta añadir id de la Cuenta
                Element cuenta = new Element("Cuenta");
                Element nombre = new Element(contr.getNombre());
                Element apellidos = new Element(contr.getApellido1() + " " + contr.getApellido2());
                Element nif = new Element(contr.getNIFNIE());
                Element ccc = new Element(contr.getCCC());
                Element iban = new Element(contr.getIBAN());

                nombre.setText("Nombre");
                apellidos.setText("Apellidos");
                nif.setText("NIFNIE");
                ccc.setText("CCCErroneo");
                iban.setText("IBANCorrecto");

                cuenta.addContent(nombre);
                cuenta.addContent(apellidos);
                cuenta.addContent(nif);
                cuenta.addContent(ccc);
                cuenta.addContent(iban);
                cuentas.addContent(cuenta);
            }
            XMLOutputter xml = new XMLOutputter();
            xml.setFormat(Format.getPrettyFormat());
            xml.output(doc, new FileWriter("ErroresCCC.xml"));
        } catch (IOException ex) {
            Logger.getLogger(ErrorManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ErrorManagerNIF(List<Contribuyente> lista) {
        try {
            Element contribuyentes = new Element("Contribuyentes");
            Document doc = new Document(contribuyentes);
            
            for (int i = 0; i < lista.size(); i++) {
                Contribuyente contr = lista.get(i);
                //falta añadir id del Contribuyente
                Element contribuyente = new Element("Contribuyente");
                Element nombre = new Element(contr.getNombre());
                Element apellido1 = new Element(contr.getApellido1());
                Element apellido2 = new Element(contr.getApellido2());
                Element nif = new Element(contr.getNIFNIE());

                nif.setText("NIF_NIE");
                nombre.setText("Nombre");
                apellido1.setText("PrimerApellido");
                apellido2.setText("SegundoApellido");

                contribuyente.addContent(nif);
                contribuyente.addContent(nombre);
                contribuyente.addContent(apellido1);
                contribuyente.addContent(apellido2);
                contribuyentes.addContent(contribuyente);
            }

            XMLOutputter xml = new XMLOutputter();
            xml.setFormat(Format.getPrettyFormat());
            xml.output(doc, new FileWriter("ErroresNifNie.xml"));
        } catch (IOException ex) {
            Logger.getLogger(ErrorManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
