import java.util.Date;

class Data{
	String patientName;
	String diagnosis;
	String nextConnected = "";
	String dateAdded;

	public Data(String p, String d, String n, String date){
		this.patientName = p;
		this.diagnosis = d;
		this.nextConnected = n;
		this.dateAdded = date;
	}
}

class Block extends Data{
	public String hash;
	public String previousHash;
	private long timestamp;
	private int nonce;

	public Block(String p, String d, String n, String date, String previousHash){
		super(p, d, n, date);
		this.previousHash = previousHash;
		this.timestamp = new Date().getTime();
		this.hash = calculateHash();
	}

	public String calculateHash(){
		String calculatedHash = StringUtil.applySha256(
									previousHash +
									Long.toString(timestamp) +
									patientName + diagnosis + nextConnected + dateAdded +
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