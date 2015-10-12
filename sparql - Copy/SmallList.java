/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sparql;

/**
 *
 * 
 */
public class SmallList {
    SmallNode small_head;
    SmallNode iter;
    public SmallList(){
        small_head=null;
    } 
    public void insert(String p,String o){
        SmallNode n=new SmallNode(p, o);
        SmallNode temp;
        
        

        if(small_head==null)
        {
            small_head=n;
        }
 else
        {
            temp=small_head;
            while(temp.link!=null){
            temp=temp.link;
        }
        temp.link=n;
        }
        //small_head=n;
        iter=small_head;
    }
    public SmallNode iterator(){
        SmallNode temp;
        temp=iter;

        if(temp!=null){
        iter=iter.link;
            return iter;

        }
        else{
            iter=small_head;
            return null;
        }
    }
}
