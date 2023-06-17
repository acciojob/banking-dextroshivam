package com.driver;

public class CurrentAccount extends BankAccount{
    private String tradeLicenseId; //consists of Uppercase English characters only

    public CurrentAccount(String name, double balance, String tradeLicenseId) throws Exception {
        super(name, balance, 5000);
        if (balance < 5000) throw new Exception("Insufficient Balance");
        else{
            this.tradeLicenseId=tradeLicenseId;
        }
        // minimum balance is 5000 by default. If balance is less than 5000, throw "Insufficient Balance" exception

    }

    private boolean isValid(String id) {
        if(id.equals("")) return false;
        for(int i=0;i<id.length()-1;i++){
            if(id.charAt(i)==id.charAt(i+1)) return false;
        }
        return true;
    }

    public String getTradeLicenseId() {
        return tradeLicenseId;
    }

    public void setTradeLicenseId(String tradeLicenseId) {
        this.tradeLicenseId = tradeLicenseId;
    }

    public void validateLicenseId() throws Exception {
        // A trade license Id is said to be valid if no two consecutive characters are same
        // If the license Id is valid, do nothing
        // If the characters of the license Id can be rearranged to create any valid license Id
        // If it is not possible, throw "Valid License can not be generated" Exception
        boolean check = isValid(tradeLicenseId);
        if(!check){
            String s=tradeLicenseId;
            int [] freq = new int[26]; //to Store Frequency of each alphabet
            char [] arr = s.toCharArray();

            for(int i = 0;i<arr.length;i++){  //store the frequency
                freq[arr[i] - 'a']++;
            }

            int max = 0,letter = 0;

            for(int i = 0;i<26;i++){  //find the max frequency
                if(freq[i] > max){
                    max = freq[i];
                    letter = i;
                }
            }

            if(max > (s.length() + 1)/2) {
                throw new Exception("Valid License can not be generated");
            } //if max is more than half then not possible

            int idx = 0;
            char [] res = new char[s.length()];

            while(freq[letter] > 0){   //distribute the max freq char into even indices
                res[idx] = (char)(letter + 'a');
                idx += 2;
                freq[letter]--;
            }

            for(int i = 0;i<26;i++){
                while(freq[i] > 0){
                    if(idx >= s.length()) idx = 1; //all even indices filled, so switch to odd indices
                    res[idx] = (char)(i + 'a');
                    idx += 2;
                    freq[i]--;
                }

            }

            tradeLicenseId=String.valueOf(res);
        }
    }

}
