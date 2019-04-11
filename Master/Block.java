import java.security.*;

class Data{
	String patientUsername;
	String diagnosis;
	String timestamp;

	public Data(String p, String d, String timestamp){
		this.patientUsername = p;
		this.diagnosis = d;
		this.timestamp = timestamp;
	}
}

class Block extends Data{
	public String hash;
	public String previousHash;
	private int nonce;
//	public byte[] signature;
//	public PublicKey publicKey;

	public Block(String p, String d, String timestamp, String previousHash){
		super(p, d, timestamp);
		this.previousHash = previousHash;
		this.hash = calculateHash();
//		this.publicKey = publicKey;
//		this.signature = signature;
	}

	public String calculateHash(){
		String calculatedHash = StringUtil.applySha256(
									previousHash +
									patientUsername + diagnosis + timestamp +
									Integer.toString(nonce)
								);
		return calculatedHash;
	}

	public void mineBlock(int difficulty){
		String target = new String(new char[difficulty]).replace('\0', '0');
		while(!hash.substring(0, difficulty).equals(target)){
			nonce++;
			hash = calculateHash();
		}
		System.out.println("Block Mined! : " + hash);
	}
}