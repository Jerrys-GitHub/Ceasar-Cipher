public class Cipher {
    private int shiftValue;
    private static StringBuilder string;
    private boolean encryptionStatus;

    public Cipher(String originalString, int shiftValue, boolean isEncrypted){
        if(shiftValue < 0){
            shiftValue %= 26;
            shiftValue += 26;
        } else if(shiftValue >= 26){
            shiftValue %= 26;
        }
        this.shiftValue = shiftValue;
        this.encryptionStatus = isEncrypted;
        string = new StringBuilder(originalString);
    }

    public String encrypt(){
        if(encryptionStatus) return string.toString();
        for(int i = 0; i < string.length(); i++){
            char currentChar = string.charAt(i);
            if(!Character.isLetter(currentChar)) continue;
            if(Character.isUpperCase(currentChar)) {
                shift(currentChar, i, 'A', shiftValue);
                continue;
            }
            shift(currentChar, i, 'a', shiftValue);
        }
        encryptionStatus = true;
        return this.toString();
    }
    public String decrypt(){
        if(!encryptionStatus) return string.toString();
        for(int i = 0; i < string.length(); i++){
            char currentChar = string.charAt(i);
            if(!Character.isLetter(currentChar)) continue;
            if(Character.isUpperCase(currentChar)){
                shift(currentChar, i, 'A', -1 * shiftValue);
                continue;
            }
            shift(currentChar, i,'a', -1 * shiftValue);
        }
        encryptionStatus = false;
        return this.toString();
    }

    private void shift(char c, int position, int baseValue, int shiftValue){
        char shiftedChar = (char) ((c - baseValue + shiftValue + 26) % 26 + baseValue);
        string.setCharAt(position, shiftedChar);
    }

    public boolean returnEncryptionStatus(){
        return encryptionStatus;
    }

    public void setShiftValue(int newShiftValue){
        if(encryptionStatus){
            //it is redundant to have the decrypt function next to the false variable
            // but it will work for now. I want to keep this outside of the CLI
            this.setStringBuilder(this.decrypt(), false);
            this.shiftValue = newShiftValue;
            System.out.println("Your String was encrypted, but has been de-encrypted and re-encrypted!" +
                               " Your new encrypted String is \"" + this.encrypt() + "\"");
        }else{
            this.shiftValue = newShiftValue;
        }
        System.out.println("Done!");
    }

    public void setStringBuilder(String newString , boolean newEncryptionStatus){
        string = new StringBuilder(newString);
        setEncryptionStatus(newEncryptionStatus);
    }
    public void setEncryptionStatus(boolean newEncryptionStatus){
        //todo: if these are equal to each other, do not change anything;
        encryptionStatus = newEncryptionStatus;
    }

    public String toString(){
        return string.toString();
    }
}