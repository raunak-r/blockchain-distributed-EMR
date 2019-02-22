import java.util.ArrayList;
import com.google.gson.GsonBuilder;

class BlockChain{
	public static int difficulty = 5;
	public static ArrayList<Block> blockchain = new ArrayList<Block>();

	public static Boolean isChainValid(){
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

	public static void main(String args[]) {
		blockchain.add(new Block("Hi im the first block", "0"));
		System.out.println("Hash for block 1... ");
		blockchain.get(0).mineBlock(difficulty);

		
		blockchain.add(new Block("Yo im the second block", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("Hash for block 2... ");
		blockchain.get(1).mineBlock(difficulty);		
		
		blockchain.add(new Block("Hey im the third block", blockchain.get(blockchain.size() - 1).hash));
		System.out.println("Hash for block 3... ");
		blockchain.get(2).mineBlock(difficulty);

		System.out.println("\nBlockChain is Valid: " + isChainValid());

		String blockchainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockchain);
		System.out.println("\nThe Block Chain: ");
		System.out.println(blockchainJson);
	}
}