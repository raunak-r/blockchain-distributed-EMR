import java.util.Date;

class Data{
	String patientUsername;
	String diagnosis;
	String dateAdded;

	public Data(String p, String d, String date){
		this.patientUsername = p;
		this.diagnosis = d;
		this.dateAdded = date;
	}
}

class Block extends Data{
	public String hash;
	public String previousHash;
	private long timestamp;
	private int nonce;

	public Block(String p, String d, String date, String previousHash){
		super(p, d, date);
		this.previousHash = previousHash;
		this.timestamp = new Date().getTime();
		this.hash = calculateHash();
	}

	public String calculateHash(){
		String calculatedHash = StringUtil.applySha256(
									previousHash +
									Long.toString(timestamp) +
									patientUsername + diagnosis + dateAdded +
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