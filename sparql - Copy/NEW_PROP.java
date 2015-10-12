/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sparql;

/*
 *
 *
 * 
 */
import com.hp.hpl.jena.mem.ModelMem;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.rdf.model.ResIterator.*;
import com.hp.hpl.jena.vocabulary.*;
import java.io.*;
public class NEW_PROP extends Object  {
    
    static final String inputFileName  = "eg.rdf";
   // static final String uri ="http://www.w3.org/2001/vcard-rdf/3.0#";
    
    public static void main (String args[]) throws IOException{
      File f;
    FileReader fr;
    Model model;

   // f = new File(eg.rdf);
    fr = new FileReader(inputFileName);
    model = new ModelMem();
    model.read(fr, RDFS.getURI());
   // Property p=new Property("Family");
   
    StmtIterator iter = model.listStatements();
                    while (iter.hasNext()) {

                       Statement stmt = iter.nextStatement();  // get next statement
                        Resource subject = stmt.getSubject();     // get the subject
                        Resource predicate = stmt.getPredicate();   // get the predicate
                        RDFNode object = stmt.getObject();      // get the object
                        //if(predicate.getLocalName().equals("Family")){
                        
                    
                        
                        
                     
                        System.out.println(  "Subject =="  + subject.toString() +"\tPredicate =="+ predicate.getLocalName()+
                               "\tObject ==" + object.toString() + "\n");
                     
                    }

    }

}
