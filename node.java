import java.io.*;


class node{
String name;
int count;
ArrayList <node> child;
node parent;
node()
{
count=1;
parent=null;
child=new ArrayList<node>();
}
void setname(String s)
{
    name=s;
}
void setchild(node n)
{
	child.add(n);
}
void inccount()
{
count++;	
}
ArrayList<node> getchild()
{
	return child;
}
void setparent(node n)
{
parent=n;
}
public boolean equals(node object)
{
    boolean isEqual= false;

    if (object != null )
    {
        isEqual = (this.name.equalsIgnoreCase(object.name));
    }

    return isEqual;
}
public String toString() {
		return name;
	}

}