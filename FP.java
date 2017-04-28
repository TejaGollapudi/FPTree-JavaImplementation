
import java.io.*;
import java.util.*;





public class FP {

	private static final String FILENAME = "file.txt";


	public static void printtree(node n)
	{
		System.out.println("node " +n.name+" count "+ n.count);
		ArrayList<node> c=n.getchild();
		if(!c.isEmpty())
		{
			Iterator<node> iterator = c.iterator();
			while(iterator.hasNext())
			{
				printtree(iterator.next());
			}
		}
		else
		{
			System.out.println("end of branch++++++++++++++++++++++++++++++++++");
			return;
		}
	}	

	public static void main(String[] args) {
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
				String [] b=s[1].split(",");
				ArrayList<String> e=new ArrayList<String>();
				for(i=0;i<b.length;i++)
				{
					e.add(b[i]);
				}
				ar.add(e);
				
				}
			

	
	int j;
	for(i=0;i<ar.size();i++)
	{
	System.out.println(ar.get(i).toString());
		ArrayList<String> s=ar.get(i);
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

Collections.sort(a, new Comparator<String>() {
        @Override
        public int compare(String s1, String s2)
        {

            return  map.get(s2).compareTo(map.get(s1));
        }
    });

data.add(a);
}
node parent=new node();
parent.name="parent";
for(i=0;i<data.size();i++)
{
System.out.println("data -----------"+data.get(i).toString());
Iterator<String> iterator = data.get(i).iterator();
node check=parent;
while(iterator.hasNext()){
  String s= iterator.next();
  System.out.println("Iterator "+s);
  int f=0;
  	int k;
 // System.out.println("outer if condition entered ");
  	for(k=0;k<check.child.size();k++)
  	{
  		if(check.child.get(k).name.equals(s))
  		{
  			check=check.child.get(k);
  			check.inccount();
  			System.out.println("if condition entered //traversal of tree performed");
  			f=1;
  		}
  	}
  
  if(f==0){
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