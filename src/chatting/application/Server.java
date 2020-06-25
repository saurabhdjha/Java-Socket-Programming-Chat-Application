
package chatting.application;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.net.*;
import java.io.*;

public class Server extends JFrame implements ActionListener{
    
    JPanel p1;
    JTextField t1;
    JButton b1;
    static JTextArea ta1;
    
    static ServerSocket skt;
    static Socket s;
    
    static DataInputStream din;
    static DataOutputStream dout;
    
    Server()
    {
        
        p1=new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(7,94,84));
        p1.setBounds(0,0,450,70);
        add(p1);
        
        
        JLabel l1 = new JLabel("X");
       l1.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
       l1.setForeground(Color.WHITE);
       l1.setBounds(7, 17, 30, 30);
       p1.add(l1);
        
        l1.addMouseListener(new MouseAdapter(){
           public void mouseClicked(MouseEvent ae){
               System.exit(0);
           }
       });

       JLabel l3 = new JLabel("SERVER");
       l3.setFont(new Font("SAN_SERIF", Font.BOLD, 18));
       l3.setForeground(Color.WHITE);
       l3.setBounds(110, 15, 100, 18);
       p1.add(l3);
       
       JLabel l4 = new JLabel("Active Now");
       l4.setFont(new Font("SAN_SERIF", Font.PLAIN, 14));
       l4.setForeground(Color.WHITE);
       l4.setBounds(110, 35, 100, 20);
       p1.add(l4);   
       
       ta1 = new JTextArea();
       ta1.setBounds(5, 75, 340, 540);
       ta1.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
       ta1.setBackground(Color.PINK);
       ta1.setEditable(false);
       ta1.setLineWrap(true);
       ta1.setWrapStyleWord(true);
       add(ta1);
       
       
       
        
       t1 = new JTextField();
       t1.setBounds(5, 620, 250, 40);
       t1.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
       add(t1);
       
       b1 = new JButton("Send");
       b1.setBounds(250, 620, 95, 40);
       b1.setBackground(new Color(7, 94, 84));
       b1.setForeground(Color.WHITE);
       b1.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
       b1.addActionListener(this);
       add(b1);
       
        setLayout(null);
        setSize(350,670);
        setLocation(600,300);
        setUndecorated(true);
        setVisible(true);
    }
    public static void main(String args[])
    {
        new Server().setVisible(true);
        String msginput = "";
        try{
            skt = new ServerSocket(6001);
            while(true){
                s = skt.accept();
                din = new DataInputStream(s.getInputStream());
                dout = new DataOutputStream(s.getOutputStream());
            
	        while(true){
	                msginput = din.readUTF();
        		ta1.setText(ta1.getText()+"\n"+msginput);
            	}
                
            }
            
        }catch(Exception e){}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       try{
            String out = t1.getText();
            ta1.setText(ta1.getText()+"\n\t\t"+out);
            dout.writeUTF(out);
            t1.setText("");
        }catch(Exception e1){}
    }
}
