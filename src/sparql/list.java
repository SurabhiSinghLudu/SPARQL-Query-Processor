/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sparql;

/**
 *
 * 
 */
//import sparql;
import java.io.*;
public class list
    {
        node head;
        node iter;
        public int cnt;
        
        public list()
        {
            head=null;
            cnt=0;
        }
        
      public void insert(String s)
        {
           if(head==null)
           {
               
               head=new node(s);
               iter=head;
           }
           else
           {
             node n=new node(s);
             node temp=head;
            while(temp.next!=null){
                temp=temp.next;
            }
           temp.next=n;
           //n.next=null;
           // head=n;
           }
            cnt++;
        }
       
       public void inser_val(node n,String p,String o){
          
           n.putList(p, o);

       }
       public void show()
       {
           
                       try
        {
                           
                                Writer output = null;
                                File file = new File("output.txt");

                                output = new BufferedWriter(new FileWriter(file));

           node current=head;
           System.out.println("*************************"+head.data);
           while(current!=null)
           {
               
               //output.write(current.data);
               SmallNode sc=current.sl.small_head;
                   
               //      output.append(current.data+"\n");
               while(sc!=null){
               sparql.file.outputstring+=current.data + "\n";
               sparql.file.outputstring+=sc.predicate + "\t"+sc.predicate+"\n";
               //System.out.println("--||---"+current.data);
               System.out.println("-----"+sc.predicate+"~~~~~~"+sc.object);
               sc=current.sl.iterator();
               }
               System.out.println("*************************");

               current=current.next;
           }
           //System.out.println(cnt);
           
           
    output.close();
    System.out.println("Your file has been written");    
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
           
       }
       
             
       public void reset()
       {
           head=null;
       }
       
       public void getUnique()
       {
           node current=head;
           String s;
           while(current!=null)
           {
               
               node curr_inner=current.next;
               node prev=current;
               s=current.data;
               
               while(curr_inner!=null)
               {
                   
                   //s1=curr_inner.data;
                  // System.out.println(current.data +"\t"+ curr_inner.data);
                   if(current.data.equals(curr_inner.data))
                   {
                //       System.out.println("equal"+curr_inner.data);
                       prev.next=curr_inner.next;
                   }
                   prev=curr_inner;
                   curr_inner=curr_inner.next;
                   
               }
              // System.out.println("________________");
              current=current.next; 
           }
       }
       
       public String iterator(){
        node temp;
        temp=iter;
       
        if(temp!=null){
        iter=iter.next;
            return temp.data;
         
        }
        else{
            iter=head;
            return null;
        }
    }
    }