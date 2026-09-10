import java.util.InputMismatchException;
import java.util.Scanner;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PasswordGenerator{
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMBERS = "0123456789";
    private static final String SYMBOLS = "!@#$%^&*()-_=+";

    public static void main(String[]args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("╔══════════════════════════════╗");
        System.out.println("║     PASSWORD GENERATOR 🔐    ║");
        System.out.println("╚══════════════════════════════╝");

        System.out.print("Enter the length of the password: ");
        try{
            int length = scanner.nextInt();
            if (length <= 0) {
                System.out.println("❌Password length must be a positive integer!");
                scanner.close();
                return;
            }
            else{
            System.out.print("✅ Valid length: "+ length + "\n");
            }
        
        
        

        
        boolean includeNumbers = askYesOrNo(scanner,"Include numbers?");
        boolean includeSymbols = askYesOrNo(scanner,"Include symbols?");
        boolean includeLowercase = askYesOrNo(scanner,"Include lowercase letters?");
        boolean includeUppercase = askYesOrNo(scanner,"Include uppercase letters?");
        int selectedCategories = 0;

        if (includeNumbers) {
        selectedCategories++;
        }

        if (includeSymbols) {
            selectedCategories++;
        }

        if (includeLowercase) {
            selectedCategories++;
        }

        if (includeUppercase) {
            selectedCategories++;
        }
        if (selectedCategories == 0) {
        System.out.println("❌ You must select at least one character type!");
        scanner.close();
        return;
        }

    if (length < selectedCategories) {
        System.out.println("❌ Password length is too short!");
        scanner.close();
        return;
        }


        
        String password = generatePassword(length, includeNumbers, includeSymbols, includeLowercase, includeUppercase);
        password = shuffleString(password);
        System.out.println("Generated Password: " + password);
        scanner.close();
        }
        catch (InputMismatchException e) {
            System.out.println("❌ You must enter a number!");
            return;
        }
    }
    private static String generatePassword(int length,boolean includeNumbers,boolean includeSymbols,boolean includeLowercase,boolean includeUppercase) {
        StringBuilder password = new StringBuilder();
    String characters = "";

    if (includeLowercase) {
        password.append(getRandomCharacter(LOWERCASE));
        characters += LOWERCASE;
    }

    if (includeUppercase) {
        password.append(getRandomCharacter(UPPERCASE));
        characters += UPPERCASE;
    }

    if (includeNumbers) {
        password.append(getRandomCharacter(NUMBERS));
        characters += NUMBERS;
    }

    if (includeSymbols) {
        password.append(getRandomCharacter(SYMBOLS));
        characters += SYMBOLS;
    }
        while (password.length() < length) {
            
            password.append(getRandomCharacter(characters));
        }
        return password.toString();
    }
    
    private static char getRandomCharacter(String characters) {
        SecureRandom random = new SecureRandom();
        int index = random.nextInt(characters.length());
        return characters.charAt(index);
    }
    private static boolean askYesOrNo(Scanner scanner,String domanda){
        while(true){
            System.out.print(domanda + " (y/n): ");
            String input = scanner.next().trim().toLowerCase();
            if(input.equalsIgnoreCase("y")){
                return true;
            }
            else if(input.equalsIgnoreCase("n")){
                return false;
            }
            else{
                System.out.println("❌ Invalid input! Please enter 'y' or 'n'.");
            }
        }
    }
    private static String shuffleString(String input) {
        List<Character> characters = new ArrayList<>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        Collections.shuffle(characters);
        StringBuilder shuffled = new StringBuilder();
        for (char c : characters) {
            shuffled.append(c);
        }
        return shuffled.toString();
    }
}