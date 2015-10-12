/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sparql;

/**
 *
 * 
 */
import com.hp.hpl.jena.mem.ModelMem;
import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.rdf.model.ResIterator.*;
import com.hp.hpl.jena.vocabulary.*;
import java.io.*;


public class omRevese {
    
  //  static final String inputFileName  = "eg.rdf";
   // static final String uri ="http://www.w3.org/2001/vcard-rdf/3.0#";
    
    
    
    public list omRev(String dp,String criteria,String criteria_val)throws IOException{
        
    File f;
    FileReader fr;
    Model model;
    //String p="Given";
    String main_sub;
    //String property="FN";
    int list_cnt=1;
    boolean subject_flag=true;
    //System.out.println("        "+dp+"\t"+criteria+"\t"+criteria_val);
    // f = new File(eg.rdf);
    fr = new FileReader(sparql.file.inputFileName);
    model = new ModelMem();
    model.read(fr, RDFS.getURI());
   
     
    System.out.println("OMREVERSE");
    StmtIterator iter = model.listStatements();
    list l1=new list();
   // l1.insert("Äshish");
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
                        
                       if(predicate.getLocalName().equals(criteria) && object.toString().equals(criteria_val)){
                            
                            l1.insert(subject.toString());
                            
                               System.out.println(  "Subject =="  + subject.toString() +"\tPredicate =="+ predicate.getLocalName()+                               "\tObject ==" + object.toString() + "\n");
                     
                       }
           
                        
                     
                     
                    }
   //System.out.println("L1");
 //  l1.show();
    
    l2=l1;
   // l4=l1;
                            if(l1.cnt>0)
                            {

                                while(subject_flag==true && list_cnt>0){
                                node subj=l1.head;
                                String s1=subj.data;
                               // System.out.println("---"+s1);
                                list_cnt=0;
                               // System.out.println("Test");
                                //main_sub=l1.iterator();
                                System.out.println("complete");
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
                                         if(subject1.toString().equals(subj.data))
                                         { 
                                             
                                                l1.inser_val(subj,predicate1.getLocalName(),object1.toString());
                                                System.out.println("#####"+subj+"     "+predicate1.getLocalName()+"\t"+object1.toString());
                                                s1=object1.toString();
                                                //System.out.println("node \t " + object1.toString());
                                             //System.out.println("~~~~~~~~~~~~~~~~");
                                             if(predicate1.getLocalName().equals(dp)==false){
                                                  // System.out.println("HUUUDDD");
                                                   l3.insert(object1.toString());
                                                   subject_flag=true;
                                             }
                                                 
                                         }
                                        if(subject1.toString().equals(subj.data)){
                                            list_cnt++;
                                        }
                              
                                    }
                                    subj.data=s1;
                                    subj=subj.next;
                                    //System.out.println("############");
                                    //main_sub=l1.iterator();
                                    
                                        
                             }
                                
                                l2=l3;
                                l3=new list();
                                /*System.out.println("------------------");
                                l2.show();
                                System.out.println("------------------");
                                System.out.println(subject_flag);*/
                                }
                            }
    return l1;

    }
    }
 
