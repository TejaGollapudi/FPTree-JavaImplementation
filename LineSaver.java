import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.geom.*;

public class LineSaver extends Frame
{
private Timer timer;

public LineSaver()
{
super("Screen saver with 100s of random lines");

setSize(400,400);
show();

timer = new Timer(1000, new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
repaint();
}
});
timer.start();
}

public void paint(Graphics g)
{


for (int i=0; i<100; i++)
{

g.drawString(Integer.toString(i),
(int)(Math.random()*400),
(int)(Math.random()*400)
);
}

}

public static void main (String [] agrs)
{
LineSaver app = new LineSaver();

app.addWindowListener(
new WindowAdapter()
{
public void windowClosing( WindowEvent e)
{
System.exit(0);
}
});
}
}