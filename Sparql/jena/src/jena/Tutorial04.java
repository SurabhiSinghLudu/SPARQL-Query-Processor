/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jena;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.*;

/** Tutorial 4 - create a model and write it in XML form to standard out
 *
 * @author  bwm - updated by Kers/Daniel
 * @version Release='$Name:  $' Revision='$Revision: 1.1 $' Date='$Date: 2005/03/12 13:52:28 $'
 */
public class Tutorial04 extends Object {

    // some definitions
    static String tutorialURI  = "http://hostname/rdf/tutorial/";
    static String briansName   = "Brian McBride";
    static String briansEmail1 = "brian_mcbride@hp.com";
    static String briansEmail2 = "brian_mcbride@hpl.hp.com";
    static String title        = "An Introduction to RDF and the Jena API";
    static String date         = "23/01/2001";

    public static void main (String args[]) {

        // some definitions
        String personURI    = "http://somewhere/JohnSmith";
        String givenName    = "John";
        String familyName   = "Smith";
        String fullName     = givenName + " " + familyName;
        // create an empty model
        Model model = ModelFactory.createDefaultModel();

        // create the resource
        //   and add the properties cascading style
        Resource johnSmith
          = model.createResource(personURI)
                 .addProperty(VCARD.FN, fullName)
                 .addProperty(VCARD.N,
                              model.createResource()
                                   .addProperty(VCARD.Given, givenName)
                                   .addProperty(VCARD.Family, familyName));

        // now write the model in XML form to a file
        model.write(System.out);
    }
}