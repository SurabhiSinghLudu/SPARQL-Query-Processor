/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * 



 */
//import com.sun.org.apache.xpath.internal.operations.Equals;
package sparql;


import java.io.*;
import java.util.*;



class  InvalidQueryException extends Exception{

    public InvalidQueryException(String s) {
    super(s);
    }
    
}


public class ExecuteQuery {
    
    public void ExecQuery()
    {
        
        String strLine="";
        sparql.file.outputstring=""    ;
        try
        {
    
            FileInputStream fstream = new FileInputStream("queryfile.txt");
            DataInputStream din = new DataInputStream(fstream);
            BufferedReader br = new BufferedReader(new InputStreamReader(din));
    
            String val[]=new String[30];
            String src_file;
            String where_var[][]=new String[10][3];
            int i=0,j=0,s_frm_cnt=0;
            boolean s_frm_flag=true,criteria_flag=false;
            
            while ((strLine = br.readLine()) != null)
            {
    
    
                StringTokenizer st= new StringTokenizer(strLine," ")       ;
                while(st.hasMoreTokens())
                {

                        val[i] = st.nextToken();
                        
                        if (val[i].charAt(0)== '?' && s_frm_flag==true )
                        {
                            s_frm_cnt++;
                           // System.out.println("----Variable "+s_frm_cnt);

                            //s_frm_flag=true;
                            
                        }
                        else if(s_frm_cnt>1 && val[i].charAt(0)== '?' && s_frm_flag==true){
                             s_frm_cnt++;
                            }
                        else if(s_frm_cnt>0 && s_frm_flag==true){
                            s_frm_flag=false;
                        }
                        i++;
                       // System.out.println("Token : "+  val[i++]);
                        
                        
                }
                int name_cnt=0,x=0;
                s_frm_flag=false;
                for(int i1=0;i1<i;i1++){
                   //System.out.println("----------"+val[i1]+"-----"+val[i1].indexOf("\""));
                   if(val[i1].startsWith("\"")&& val[i1].endsWith("\"")){
                       criteria_flag=true;
                       
                   } 
                   else if ( (val[i1].indexOf("\"") >= 0) && (name_cnt>0)) 
                    {
                         val[x]=val[x].concat(" ");
                        val[x]=val[x].concat(val[i1]);
                        for(int i2=i1;i2<i;i2++){
                            val[i2]=val[i2+1];
                        }
                        i--;
                        i1--;
                        name_cnt=0;
                    }
                    else if(val[i1].indexOf("\"")>=0){
                        name_cnt++;
                        x=i1;
                        s_frm_flag=true; 
                        criteria_flag=true; 
                        
                    }
                    else if(name_cnt>0 && s_frm_flag==true){
                        name_cnt++;
                        val[x]=val[x].concat(" ");
                        val[x]=val[x].concat(val[i1]);
                         for(int i2=i1;i2<i;i2++){
                            val[i2]=val[i2+1];
                        }
                        i--;
                        i1--;
                    }
                    
                }
                /*for(int i1=0;i1<i;i1++){
                    System.out.println("Token After : "+  val[i1]);
                }*/
                //System.out.println("s_frm_cnt" + s_frm_cnt);
                //System.out.println(val[0]);
                if(val[0].equals("select") == false&&val[0].equals("SELECT") == false  )
                {
                
                    throw new InvalidQueryException("Select keyword incorrect!!");
                }
                
               
                    String identifier_arr[]=new String[s_frm_cnt];//array to store the displaying varaibles
                    
                    int k=1;
                    i=0;
                while(k<=s_frm_cnt)
                {
                    identifier_arr[i]=val[k];
                    k++;
                    i++;
                    //System.out.println("----Variable "+identifier_arr[i++]);
                }
                  //System.out.println("----Variable "+s_frm_cnt);
                    if(val[s_frm_cnt+1].equals("from")==false && val[s_frm_cnt+1].equals("FROM")==false){
                       throw new InvalidQueryException("From keyword incorrect!!");
                }
                sparql.file.inputFileName=val[s_frm_cnt+2];
                 //System.out.println("----file name "+sparql.file.inputFileName);
                if(val[s_frm_cnt+3].equals("where")==false && val[s_frm_cnt+3].equals("WHERE")==false){
                       throw new InvalidQueryException("keyword incorrect!!");
                }
                               
                 int cnt_var=s_frm_cnt+4;
                 int iterat_column=0,iterat_row=0;
                 if(val[s_frm_cnt+4].equals("{")){
                     
                     while(val[cnt_var++].equals("}")==false){
                         
                         if(val[cnt_var].equals(".")){
                             cnt_var++;
                             iterat_row++;
                             iterat_column=0;
                              
                         }
                         
                        where_var[iterat_row][iterat_column++]=val[cnt_var];
                                              
                     }
                 }
                 for(int i1=0;i1<iterat_row;i1++){
                    where_var[i1][1]=where_var[i1][1].split(":")[1];
                    /* for(int j1=0;j1<3;j1++){
                        
                         System.out.print(where_var[i1][j1]+"\t");
                   }
                     System.out.println();*/
                 }
                 System.out.println("----------"+criteria_flag);
                 list l1=new list();
                 if(criteria_flag==false){
                     for(int i1=0;i1<iterat_row;i1++){
                        sparql.forAll n2=new sparql.forAll();
                        System.out.println("*****"+ where_var[i1][1]);
                        l1=n2.getForAll(where_var[i1][1]);
                        System.out.println("------------------"+"\n"+where_var[i1][1]+"\n"+"------------------");
                        sparql.file.outputstring+="------------------"+"\n"+where_var[i1][1]+"\n"+"------------------\n";
                        l1.show();
                     }
                     
                 }
                 else {
                    
                    
                  sparql.om n=new sparql.om();
                  l1=n.getOM(where_var[0][1], where_var[1][1],where_var[1][2]);
                  //l1.show();
                  if(l1.cnt==0){
                   sparql.omRevese n1=new sparql.omRevese();
               //System.out.println(where_var[0][1]+"\t"+where_var[1][1]+"\t"+where_var[1][2]);
                   l1=n1.omRev(where_var[0][1], where_var[1][1],where_var[1][2]);
                   System.out.println("---");
                   
                   System.out.println("---");
                   
                  }
                     l1.show();
                 }
    
    } // end of try
    
    
    din.close();
    }
        
        catch (Exception e)
        {//Catch exception if any
                System.out.println("Error: " + e.getMessage());
         }
    
        }
    
    }
    