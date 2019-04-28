```
https://docs.google.com/presentation/d/1kQBK7jXEy7K6CHuYTY2pAUYUKxdJZ8vUtnf5UOP3yO4/edit#slide=id.gcb9a0b074_1_0
```

## Credentials
raunak, ritesh, 7
2015160, 533420, 5

### What problem is this project solving?

Current Scenario

Doctors are very limited in diagnosing a patient because of the lack of access to his medical history. 
Currently, this is solved only if the person is visiting the same hospital of which he is a regular patient (hospital database) or he is carrying his documents with himself. 
But what if the patient is in critical condition and is in a immediate need of a doctor?

### Proposed Solution

There can be a Medical Record Ledger cryptographically written and accessible by all the stakeholders (distributed ledger).  Patients can access their personal records by using a service like a wallet. A private key representing each of the user can be used to access their Medical wallets.

### Step 1 - Understanding the Ledger
mineBlock()
createBlock()

Each block will contain n number of transactions.

A block when filled with n transactions can be verified or mined using 
(Prev Hash + Current Data + K) 

Once a block is mined it can be added to the ledger.

### Step 2 - Accessing the MedWallet
viewUser()

Each wallet will have a public key accessible to everyone.

Each user will have a private key.

The wallet can be accessed only when they are in right combination.

### Step 3 - Making a new Transaction
verifyTransaction()

A signature will be generated from the private and the public key.

This signature together with the public key can be used to verify if the request is from a trusted source or not. If valid, the transaction can be added to a block.