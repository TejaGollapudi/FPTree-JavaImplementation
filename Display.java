import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Display {
    static JFrame jj;
    static JApplet app;
    static Color NODE_COLOR=Color.DARK_GRAY;
    static Color TEXT_COLOR=Color.WHITE;
    public static void printtree(int x, int depth,int px,int pdepth,String name,int co) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        app.add(new Component() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                g.setColor(NODE_COLOR);
                if(px+pdepth>0)
                    g.drawLine(px,pdepth,x,depth);
                g.fillRect(x,depth,70,50);
                g.setColor(TEXT_COLOR);
                g.drawString(name+": "+co,x+10,depth+20);
            }
        });
        app.revalidate();
        app.repaint();
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Beep2.wav").getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }

        //jj.repaint();
    }
    public static void initialize(){
        jj=new JFrame();
        app=new JApplet();
        app.setBounds(60,50,1200,800);
        jj.setSize(1200,800);
        jj.add(app);

        jj.setResizable(false);
        JPanel pp=new JPanel();
        pp.setBackground(Color.gray);
        pp.setBounds(0,0,jj.getWidth(),50);
        pp.setLayout(null);

        JFileChooser fileChooser = new JFileChooser();
        JButton b=new JButton("FILE");
        JButton b2=new JButton("START");
        b.setBounds(40,10,100,30);
        b.setBackground(Color.ORANGE);
        b.setFocusPainted(false);
        b2.setBounds(150,10,100,30);
        b2.setBackground(Color.WHITE);
        b.setFocusPainted(false);
        pp.add(b);
        pp.add(b2);

        JLabel l=new JLabel("   "+FP.FILENAME);
        l.setBounds(260,10,800,30);
        l.setOpaque(true);
        l.setBackground(Color.WHITE);

        pp.add(l);
        /**/

        jj.add(pp);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenu edit = new JMenu("Edit");
        JMenu color=new JMenu("Color");
        JMenuItem txtCol = new JMenuItem("text");
        JMenuItem nodCol = new JMenuItem("node");
        JMenuItem item = new JMenuItem("Exit");
        edit.add(color);
        color.add(txtCol);
        color.add(nodCol);
        JColorChooser c=new JColorChooser();
        menu.add(item);
        menuBar.add(menu);
        menuBar.add(edit);
        jj.setJMenuBar(menuBar);
        jj.setLayout(null);
        jj.setVisible(true);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        txtCol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color ss=c.showDialog(null,"Select Text Color",Color.DARK_GRAY);
                System.out.println(ss.toString());
                TEXT_COLOR=ss;
            }
        });

        nodCol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color ss=c.showDialog(null,"Select Node color",Color.DARK_GRAY);
                System.out.println(ss.toString());
                NODE_COLOR=ss;
            }
        });

        item.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TreeFormation().execute();
            }
        });

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = fileChooser.showOpenDialog(jj);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    System.out.println("Selected file: " + selectedFile.getAbsolutePath());
                    FP.FILENAME=selectedFile.getAbsolutePath();
                    l.setText("  "+FP.FILENAME);
                    pp.revalidate();
                    pp.repaint();
                    jj.repaint();
                }
            }
        });

    }
    public static class TreeFormation extends SwingWorker<Void,Void>{
        @Override
        protected Void doInBackground() throws Exception {
            FP.startTree();
            Thread.sleep(200);

            JLabel l2=new JLabel("   "+FP.FILENAME);
            l2.setBounds(1000,10,100,55);
            l2.setOpaque(true);
            l2.setBackground(Color.WHITE);
            l2.setText("       DONE!");
            app.add(l2);
            app.repaint();
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("Beep8.wav").getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start();
            } catch(Exception ex) {
                System.out.println("Error with playing sound.");
                ex.printStackTrace();
            }
            return null;
        }
    }
}
