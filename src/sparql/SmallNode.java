/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sparql;

/**
 *
 * 
 */
public class SmallNode {
    public String predicate;
    public String object;
    public SmallNode link;
    public SmallNode(String p,String o) {
        predicate=p;
        object=o;
        link=null;
    }
    
}

