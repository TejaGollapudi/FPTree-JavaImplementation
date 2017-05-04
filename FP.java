
import java.io.*;
import java.util.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class FP {
    static String FILENAME = "";
    static final int padding_left=50;
    static final int padding_top=20;

    //method to print tree by taking the root node by recurssion.
    public static void printtree(node n) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
        String x=" ";
        if(n.parent!=null) {
            x=n.parent.name;
        }
        try {
            Display.printtree(n.x*100+padding_left,n.depth*100+padding_top,n.parent.x*100+padding_left,n.parent.depth*100+padding_top,n.name,n.count);
        }
        catch (Exception e){
            Display.printtree(n.x*100+padding_left,n.depth*100+padding_top,0,0,n.name,n.count);
        }
        Thread.sleep(200);
        System.out.println("node " +n.name+" count "+ n.count+"parent"+x+"   x cordinate "+n.x+" y cordinate  "+n.depth);
        ArrayList<node> c=n.getchild();
        if(!c.isEmpty())//checks if the current node has childnodes or not
        {
            Iterator<node> iterator = c.iterator();
            while(iterator.hasNext())
            {
                printtree(iterator.next());//for each childnode call the function.
            }
        }
        else
        {
            System.out.println("end of branch++++++++++++++++++++++++++++++++++");
            return;
        }
    }

    static int xcc;
    public static void depthtree(node n)
    {
        String x=" ";
        if(n.parent!=null)
        { x=n.parent.name;
            n.depth=n.parent.depth+1;
        }
        n.x=xcc;
        ArrayList<node> c=n.getchild();

        if(!c.isEmpty())//checks if the current node has childnodes or not
        {
            Iterator<node> iterator = c.iterator();
            while(iterator.hasNext())
            {

                depthtree(iterator.next());//for each childnode call the function.
            }
        }
        else
        {
            System.out.println("end of branch+-------------------efs--------");
            xcc++;
            return;
        }
    }
    public static void main(String[] args){
        Display.initialize();
    }
    public static void startTree() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        BufferedReader br = null;
        FileReader fr = null;
        int i;
        ArrayList<ArrayList<String>> ar=new ArrayList<ArrayList<String>>();
        try {

            fr = new FileReader(FILENAME);
            br = new BufferedReader(fr);

            String CurrentLine;

            br = new BufferedReader(new FileReader(FILENAME));

            while ((CurrentLine = br.readLine()) != null) {
                String[] s=CurrentLine.split(",",2);
                //splits data into Transaction ID and transactions
                String [] b=s[1].split(",");
                ArrayList<String> e=new ArrayList<String>();
                for(i=0;i<b.length;i++)
                {
                    e.add(b[i]); //adds each element of transaction as an element of array list./
                }
                ar.add(e);//adds the whole array list into an outer array list which contains all transactions

            }



            int j;
            for(i=0;i<ar.size();i++)
            {
                System.out.println(ar.get(i).toString());
                ArrayList<String> s=ar.get(i);
                //creates a key value pair of number of items and thier occurance
                for(j=0;j<s.size();j++)
                {
                    if(map.containsKey(s.get(j)))
                    {
                        int x=map.get(s.get(j));
                        x++;
                        map.put(s.get(j),x);

                    }
                    else{
                        map.put(s.get(j),1);
                    }
                }


            }


            System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");

            Iterator<String> keySetIterator = map.keySet().iterator();

            while(keySetIterator.hasNext()){
                String key = keySetIterator.next();
                System.out.println("key: " + key + " value: " + map.get(key));
            }



            ArrayList<ArrayList<String>> data=new ArrayList<ArrayList<String>>();


            for(i=0;i<ar.size();i++)
            {
                ArrayList<String> a;
                a=ar.get(i);
//sorts the transaction arraylist based on the number of ocurances of each item
                Collections.sort(a, new Comparator<String>() {
                    @Override
                    public int compare(String s1, String s2)
                    {

                        return  map.get(s2).compareTo(map.get(s1));
                    }
                });

                data.add(a); // adds the sorted transactions into a new array list called data
            }
            node parent=new node();
            parent.name="parent";
            for(i=0;i<data.size();i++)//goes through each transaction set
            {
                System.out.println("data -----------"+data.get(i).toString());
                Iterator<String> iterator = data.get(i).iterator();
                node check=parent;//check is the base node used to compare
                while(iterator.hasNext()){//iterator next gets each individual element in the transaction
                    String s= iterator.next();
                    System.out.println("Iterator "+s);
                    int f=0;
                    int k;
                    // System.out.println("outer if condition entered ");
                    for(k=0;k<check.child.size();k++)
                    {
                        if(check.child.get(k).name.equals(s)) //if there exists a child of the check node with same name as item
                        {
                            check=check.child.get(k);//set check as the child of current check node

                            check.inccount();//increase the count of the node as it has been traversed
                            System.out.println("if condition entered //traversal of tree performed");
                            f=1;
                        }
                    }

                    if(f==0){//if no such child node exists then create a new child node and add it as a child of checknode and make check node its parent.
                        node n=new node();
                        n.name=s;
                        n.setparent(check);
                        check.setchild(n);
                        check=n;
                    }
                }
                System.out.println("parents child  "+parent.getchild().toString());

            }
            System.out.println("parents child  "+parent.name);
            parent.depth=0;
            xcc=0;

            depthtree(parent);//adds cordinates
            printtree(parent);





        }
        catch (Exception e) {

            e.printStackTrace();

        }

        finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();

            }
            catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

}