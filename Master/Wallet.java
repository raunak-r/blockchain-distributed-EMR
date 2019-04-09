import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.awt.event.*;
import javax.swing.*;

class Wallet implements ActionListener{
	public PrivateKey privateKey;
	public PublicKey publicKey;

	JFrame wallet = new JFrame();
	JLabel labelInsert = new JLabel("Enter New Diagnosis");
	JTextField tfInsert = new JTextField();

	JButton buttonAddTrans = new JButton("Add New Diagnosis");
	JButton buttonViewUserTrans = new JButton("View Diagnosis History");
	JButton buttonMenu = new JButton("Logout");

	public Wallet(){
		// generateKeyPair();
		generateAWTWindow();
	}
	
	public void generateKeyPair(){
	 	try {
	 		KeyPairGenerator keyGen = KeyPairGenerator.getInstance("ECDSA","BC");
	 		SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
	 		ECGenParameterSpec ecSpec = new ECGenParameterSpec("prime192v1");
	 		// Initialize the key generator and generate a KeyPair
	 		keyGen.initialize(ecSpec, random);   //256 bytes provides an acceptable security level
	         	KeyPair keyPair = keyGen.generateKeyPair();
	         	// Set the public and private keys from the keyPair
	         	privateKey = keyPair.getPrivate();
	         	publicKey = keyPair.getPublic();
	 	}catch(Exception e) {
	 		throw new RuntimeException(e);
	 	}
	 }

	public void generateAWTWindow(){
		labelInsert.setBounds(50,75,250,20);
        tfInsert.setBounds(50,110,250,20);
        buttonAddTrans.setBounds(50,140,250,25);
        buttonViewUserTrans.setBounds(50,180,250,25);
        buttonMenu.setBounds(50,235,250,25);
        
        wallet.add(labelInsert); wallet.add(tfInsert);
        wallet.add(buttonAddTrans);
        wallet.add(buttonViewUserTrans);
        wallet.add(buttonMenu);

        buttonAddTrans.addActionListener(this);
        buttonViewUserTrans.addActionListener(this);
        buttonMenu.addActionListener(this);

        wallet.setLayout(null);
        wallet.setVisible(true);
        wallet.setSize(400,400);
	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource() == buttonAddTrans){
			String diagnosis = tfInsert.getText();
//			String signature = createSignature(diagnosis);

			System.out.println(diagnosis);

//			Blockchain.createBlock(diagnosis, publicKey, );
		}
		else if(e.getSource() == buttonViewUserTrans){

		}
		else if(e.getSource() == buttonMenu){
			wallet.setVisible(false);
			new Login();
		}
	}

	public static void main(String args[]){
		new Wallet();
	}
	
}