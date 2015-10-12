/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sparql;

/**
 *
 * 
 */
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.rdf.model.ResIterator.*;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.*;


import java.io.*;

/** Tutorial 5 - read RDF XML from a file and write it to standard out
 *
 * @author  bwm - updated by kers/Daniel
 * @version Release='$Name:  $' Revision='$Revision: 1.3 $' Date='$Date: 2005/10/06 17:49:05 $'
 */
public class read extends Object {

    /**
        NOTE that the file is loaded from the class-path and so requires that
        the data-directory, as well as the directory containing the compiled
        class, must be added to the class-path when running this and
        subsequent examples.
    */    
    static final String inputFileName  = "eg.rdf";
     //static final String uri ="http://www.w3.org/2001/vcard-rdf/3.0#";                         
    public static void main (String args[]) {
        // create an empty model
        //  String personURI    = "http://JS";
        Model model = ModelFactory.createDefaultModel();
        //Property P = model.createProperty(uri,"FN");
        
        InputStream in = FileManager.get().open( inputFileName );
        if (in == null) {
            throw new IllegalArgumentException( "File: " + inputFileName + " not found");
        }
        
        // read the RDF/XML file
        model.read(new InputStreamReader(in), "");
        
       
                    
        // write it to standard out
        
        model.write(System.out);            
        
        /* TESTING to access a particular node value
        
            Resource vcard= model.getResource("http://JS");
            String  name=vcard.getProperty(VCARD.FN).getString();
        
            //String name1 =name.getProperty(VCARD.Family).getString();
            //System.out.println(name1.toString());
        
            System.out.println("The names of  are:" + name);
            
                // list the valeues having same property
                /*StmtIterator iter = name.listProperties(VCARD.FN);
                    while (iter.hasNext()) {
                    System.out.println(" " + iter.nextStatement().getObject().toString() );
                }*/ 

// to list different vales of same file in form of different 

ResIterator iter1 = model.listSubjectsWithProperty(VCARD.FN);
if (iter1.hasNext()) {

   System.out.println("The database contains vcards for:");

 while (iter1.hasNext())
{
 
    Resource r1=iter1.nextResource();
    // To show Full name
    //System.out.println(" " + r1.getProperty(VCARD.FN).getString());
    
    // to show the values of properties of inner node 'N'
    Resource r11=(Resource)r1.getProperty(VCARD.N).getObject();
    System.out.print(" " + r11.getProperty(VCARD.Given).getString());
    System.out.println(" " + r11.getProperty(VCARD.Family).getString());


}
}
    
    
    }}