import java.util.InputMismatchException;
import java.util.Scanner;
/*
* Jerry's Caesar Cipher V1.0
* There are going to be a couple of options available for this application:
* 1) Enter a string to be encrypted
* 2) Encrypt the String
* 3) Decrypt the String
* 4) Change the shift value
* 5) Re-Encrypt or De-Encrypt the inputted string
* TODO 6) Change encryption status of String
*/
// --TODO--
// --TODO: I WILL PROBABLY ADD OPTION 5 being to re-encrypt/decrypt the inputted string--
// --TODO: OPTION 6: CHANGE ENCRYPTION STATUS OF STRING--
// --TODO: ADD SOME SORT OF DELAY SO THAT THE READER CAN SEE THEIR OUTPUTS
public class CLI {

    //static int numOptions here so that if I ever update the number of available
    // options, all I need to do is update the prompts and the global variable
    static int numOptions = 5;

    public static int intPrompt(Scanner scanner){
        int choice = 0;
        boolean validInput = false;

        while(!validInput){
            try {
                choice = scanner.nextInt();
                validInput = true;
            }catch(InputMismatchException ime){
                System.out.println("Invalid input. Please enter a number");
                scanner.nextLine();
            }
        }
        return choice;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Jerry's Caesar Cipher v1.0. Please do not hesitate to " +
                "report any bugs, code recommendations, or feature requests to the GitHub page.\n\n" +
                "**NOTE: This program specifically works to shift characters SOLELY. Any other characters" +
                "will not be encrypted or decrypted.");
        int i;
        Cipher cipher = null;
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("What would you like to do? \n\n" +
                    "1. Encrypt a given String\n" +
                    "2. Decrypt a given String\n" +
                    "3. Input another String\n"   +
                    "4. Change the key value\n" +
                    "5. Re-Encrypt or De-Encrypt the inputted string\n" +
                    "Option (enter a value between 1-" + numOptions + "): ");
            i = intPrompt(sc);
            sc.nextLine();
            switch(i){
                case 1:
                    if(cipher != null){
                        System.out.println("Error: You currently already have a string inputted." +
                        " Try using option 3 instead.");
                        break;
                    }
                    System.out.println("Enter a string to be ENCRYPTED: ");
                    String input = sc.nextLine();
                    System.out.print("What is your key value? This is what is your string will be shifted by." +
                                       "\n**NOTE: Although you can enter any 64-bit integer value," +
                                       " the max shift possible is 26 characters** \n" +
                                       "Value: ");
                    int shift = intPrompt(sc);
                    cipher = new Cipher(input, shift, false);
                    System.out.println("Your encrypted string is \"" + cipher.encrypt() + "\"");
                    break;
                case 2:
                    if(cipher != null){
                        System.out.println("Error: You currently already have a string inputted." +
                                           " Try using option 3 instead.");
                        break;
                    }
                    System.out.println("Enter a string to be DECRYPTED: ");
                    String input2 = sc.nextLine();
                    System.out.print("What is your key value? This is what is your string will be shifted by." +
                                     "\n**NOTE: Although you can enter any 64-bit integer value," +
                                     " the max shift possible is 26 characters** \n" +
                                     "Value: ");
                    int shift2 = intPrompt(sc);
                    cipher = new Cipher(input2, shift2, true);
                    System.out.println("Your decrypted string is \"" + cipher.decrypt() + "\"");
                    break;
                case 3:
                    if(cipher == null){
                        System.out.println("Error: You currently do NOT have a string already inputted." +
                                           " Try using options 1/2 instead.");
                        break;
                    }
                    System.out.println("Enter the new String: ");
                    String input3 = sc.nextLine();
                    cipher.setStringBuilder(input3, false);
                    System.out.println("Your string has been defaulted to DECRYPTED. To change this, use option 6.");
                    break;
                case 4:
                    if(cipher == null){
                        System.out.println("Error: Cannot change key value because you have not input a String yet!" +
                                           "Try options 1/2 instead");
                        break;
                    }
                    System.out.print("What is your new key value? This is what is your string will be shifted by." +
                                     "\n**NOTE: Although you can enter any 64-bit integer value," +
                                     " the max shift possible is 26 characters** \n" +
                                     "Value: ");
                    int shift4 = intPrompt(sc);
                    cipher.setShiftValue(shift4);
                    break;
                case 5:
                    if(cipher == null){
                        System.out.println("Error: You currently do NOT have a string already inputted." +
                                           " Try using options 1/2 instead.");
                        break;
                    }
                    System.out.println("Your String has been " + (cipher.returnEncryptionStatus() ? "decrypted to \"" +
                            cipher.decrypt() + "\"" : "encrypted to \"" + cipher.encrypt() + "\""));
                    break;
                case 6:
                    if(cipher == null){
                        System.out.println("Error: You currently do NOT have a string already inputted. " +
                                "Try using options 1/2 instead.");
                    }
                    cipher.setEncryptionStatus(!cipher.returnEncryptionStatus());

                    break;
                default:
                    System.out.println("Invalid option. There are only " + numOptions + "options");
                    break;
            }
        }
    }
}