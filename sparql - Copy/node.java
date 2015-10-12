/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sparql;

/**
 *
 * 
 */

public class node
    {
        String data;
        SmallList sl=new SmallList();
        node next=null;
        
        node(String s)
        {
            data=s;
        }
       public void putList(String p,String o){
           sl.insert(p, o);
       }
        
    }
    
    