/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jena;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.vocabulary.*;

/** Tutorial 2 resources as property values
 *
 * @author  bwm - updated by kers/Daniel
 * @version Release='$Name:  $' Revision='$Revision: 1.1 $' Date='$Date: 2005/03/12 13:52:28 $'
 */
public class Tutorial2 extends Object {

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
        Resource johnSmith  = model.createResource(personURI)
             .addProperty(VCARD.FN, fullName)
             .addProperty(VCARD.N,
                      model.createResource()
                           .addProperty(VCARD.Given, givenName)
                           .addProperty(VCARD.Family, familyName));

      }
}