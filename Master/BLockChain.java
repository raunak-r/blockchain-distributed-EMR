//import java.security.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import com.google.gson.*;

class BlockChain{
	public static int difficulty = 4;
	public static ArrayList<Block> blockchain = new ArrayList<Block>();

	public static Boolean verifyTransaction(){
		Block currentBlock;
		Block previousBlock;
		String hashTarget = new String(new char[difficulty]).replace('\0', '0');

		for(int i=1; i<blockchain.size(); i++){
			previousBlock = blockchain.get(i - 1);
			currentBlock = blockchain.get(i);

			if(!previousBlock.hash.equals(currentBlock.previousHash)){
				System.out.println("Previous Hashes Not Equal");
				return false;
			}

			if(!currentBlock.hash.equals(currentBlock.calculateHash())){
				System.out.println("Current Hashes Not Equal");
				return false;
			}

			if(!currentBlock.hash.substring(0, difficulty).equals(hashTarget)){
				System.out.println("The Block hasn't been mined");
				return false;
			}
		}
		return true;
	}

	public static void viewLedger(){
		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\n\n*******ELECTRONIC MEDICAL LEDGER **********");
		System.out.println(blockchainJson);
		
		System.out.println("\nBlockChain is Valid: " + verifyTransaction());
	}
	
	public static void createBlock(String patientUserName, String diagnosis){
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		Block currBlock = new Block(patientUserName, diagnosis, timeStamp,
									blockchain.get(blockchain.size() - 1).hash);
		System.out.println("***Mining Block***");
		currBlock.mineBlock(difficulty);
		blockchain.add(currBlock);
	}
	
	public static void initiateBlockChain(){
		blockchain.add(new Block("Dummy User", "Cough and Cold", "20-03-2019", "0"));
		System.out.println("Hash for block 1... ");
		blockchain.get(0).mineBlock(difficulty);

		 // blockchain.add(new Block("User 2", "Fever", "15-03-2019", blockchain.get(blockchain.size() - 1).hash));
		 // System.out.println("Hash for block 2... ");
		 // blockchain.get(1).mineBlock(difficulty);		
		
		 // blockchain.add(new Block("User 1", "Malaria", "20-03-2019", blockchain.get(blockchain.size() - 1).hash));
		 // System.out.println("Hash for block 3... ");
		 // blockchain.get(2).mineBlock(difficulty);

		System.out.println("\nBlockChain is Valid: " + verifyTransaction());
		viewLedger();
	}
	
	public static ArrayList<Block> getUserData(String patientUsername) {
		ArrayList<Block> userData = new ArrayList<Block>();
		
		for(int i = 0; i<blockchain.size(); i++) {
			if(blockchain.get(i).patientUsername.equals(patientUsername)) {
				userData.add(blockchain.get(i));
			}
		}
		return userData;
	}
	
}