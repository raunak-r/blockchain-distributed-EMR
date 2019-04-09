import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.io.File;

class Login implements ActionListener{
    
    JFrame f=new JFrame();

    JLabel l1=new JLabel("Wallet Username");
    JLabel l2=new JLabel("Wallet Password");

    JTextField t1=new JTextField();
    JTextField t2=new JTextField();
    
    JButton b1=new JButton("Login");
    JButton b2=new JButton("Quit");
    JButton b3=new JButton("See Ledger");

    Login()
    {
        l1.setBounds(50,50,300,20);
        t1.setBounds(50,100,300,20);

        l2.setBounds(50,150,300,20);
        t2.setBounds(50,200,300,20);
        
        b1.setBounds(50,250,300,20);
        b2.setBounds(50,270,300,20);
        b3.setBounds(50,300,300,20);

        f.add(l1);
        f.add(l2);
        
        f.add(t1);
        f.add(t2);
        
        f.add(b1); f.add(b2); f.add(b3);
        
        b1.addActionListener(this); b2.addActionListener(this); b3.addActionListener(this);
        
        f.setLayout(null);
        f.setVisible(true);
        f.setSize(400,400);
    }
    
    public void actionPerformed(ActionEvent e){
        // Login button
        if(e.getSource() == b1 ){
        	System.out.println(t1.getText() + " " + t2.getText());
        	
            if( verifyUser(t1.getText(), t2.getText()) == true)
            {
                new Wallet();
                f.setVisible(false);
            }
            else
            {
                new Login();
            }
        }
        // ViewLedger Button on Terminal
        else if(e.getSource() == b3){
            BlockChain.viewLedger();
        }
        // Exit Button
        else if( e.getSource() == b2){
            System.exit(0);
        } 
    }

    public static boolean verifyUser(String a, String b){
        try{
            String hashA = StringUtil.applySha256(a);
            String hashB = StringUtil.applySha256(b);
            System.out.println(hashA);
            System.out.println(hashB);

            File f = new File("userDB.txt");
            BufferedReader buffer = new BufferedReader(new FileReader(f));
            String readLine = "";
           
            while ( (readLine = buffer.readLine()) != null){
                String[] line = readLine.split(",");

                if(line[0].equals(hashA) && line[1].equals(hashB)){
                    buffer.close();
                    return true;
                }
            }
            buffer.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
    
    public static void main(String args[]){
        BlockChain.createBlockChain();
        new Login();
    }
}