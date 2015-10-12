/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sparql;

/**
 *
 
 */
import com.hp.hpl.jena.mem.ModelMem;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.rdf.model.ResIterator.*;
import com.hp.hpl.jena.vocabulary.*;
import java.io.*;


public class om {
    
   // static final String inputFileName  = "eg.rdf";
   // static final String uri ="http://www.w3.org/2001/vcard-rdf/3.0#";
    
    
    
    public list getOM(String dp,String criteria,String criteria_val)throws IOException{
      File f;
    FileReader fr;
    Model model;
    //String p="FN";
    String main_sub;
    int list_cnt=1;
    boolean subject_flag=true;

   // f = new File(eg.rdf);
    fr = new FileReader(sparql.file.inputFileName);
    model = new ModelMem();
    model.read(fr, RDFS.getURI());
   //System.out.println("        "+dp+"\t"+criteria+"\t"+criteria_val);
    
   System.out.println("OMOMOMOMOM");
    StmtIterator iter = model.listStatements();
    list l1=new list();
    list l2=new list();
    list l3=new list();
    list l4=new list();
    
    criteria_val=criteria_val.replace("\"", "");
    
   // System.out.println("        "+dp+"\t"+criteria+"\t"+criteria_val);
                    while (iter.hasNext()) {

                        Statement stmt = iter.nextStatement();  // get next statement
                        Resource subject = stmt.getSubject();     // get the subject
                        Resource predicate = stmt.getPredicate();   // get the predicate
                        RDFNode object = stmt.getObject();      // get the object
                        
                     if(predicate.getLocalName().equals(criteria)&& object.toString().equals(criteria_val)){
                            
                           l1.insert(subject.toString());
                            
                               //System.out.println(  "Subject =="  + subject.toString() +"\tPredicate =="+ predicate.getLocalName()+                               "\tObject ==" + object.toString() + "\n");
                     
                       }
           
                        
                     
                     
                   }
    //l1.show();
    l2=l1;
                            if(l1.cnt>0)
                            {
                                while(subject_flag==true && list_cnt>0){
                                node subj=l1.head;
                                list_cnt=0;
                               // System.out.println("Test");
                               main_sub=l2.iterator();
                                 String s1=subj.data;
                                //System.out.println("complete");
                                while(subj!=null)
                                {
                            
                                    StmtIterator iter1 = model.listStatements();
                                    subject_flag=false;
                                    while (iter1.hasNext())
                                    {

                                        Statement stmt1 = iter1.nextStatement();  // get next statement
                                        Resource subject1 = stmt1.getSubject();     // get the subject
                                        Resource predicate1 = stmt1.getPredicate();   // get the predicate
                                        RDFNode object1 = stmt1.getObject();      // get the object
                                       
                                     //System.out.println("~~~~~~~~~~~~~~~~"+subj.data);
                                         if(object1.toString().equals(subj.data)||subject1.toString().equals(subj.data))
                                         { 
                                             //if(predicate1.getLocalName().equals(dp))
                                                //System.out.println("node \t " + object1.toString());
                                               //  l4.insert(object1.toString());
                                                l1.inser_val(subj,predicate1.getLocalName(),object1.toString());
                                                System.out.println("#####"+subj+"     "+predicate1.getLocalName()+"\t"+object1.toString());
                                                s1=object1.toString();
                                             if(subject1.toString().equals(main_sub)==false){
                                                  // System.out.println("HUUUDDD");
                                                   l3.insert(subject1.toString());
                                                   subject_flag=true;
                                             }
                                                 
                                         }
                                        if(object1.toString().equals(subj.data)){
                                            list_cnt++;
                                        }
                                                         
                             }
                                          subj.data=s1;
                                    subj=subj.next;
                                    //System.out.println("############");
                                    main_sub=l2.iterator();
                                l2=l3;
                                l3=new list();
                                /*System.out.println("------------------");
                                l2.show();
                                System.out.println("------------------");
                                System.out.println(subject_flag);*/
                                }
                            }
    System.out.println("----------------");
    l1.getUnique();
  
    

    }
      return l1;
    }
}

    