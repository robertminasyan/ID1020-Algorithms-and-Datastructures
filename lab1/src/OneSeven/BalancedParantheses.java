package OneSeven;

import java.util.Scanner;

public class BalancedParantheses {

    public static void main(String[] args) {
        System.out.println(balancedBoolean());
    }

    /*
    this is the method that checks if the input of parantheses
    are balanced or not. This is done by calling another method
    down below called ifEqual while iterating through the charArray
    which is created in this method by taking in a string.
    It returns true if it is balanced and false if it is not
     */
    public static boolean balancedBoolean(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("type a series of parantheses");
        String input = scanner.next();
        char StringToCharArray[] = input.toCharArray();
        int arrayLength = StringToCharArray.length;
        int k = 0;
        int p = 0;
        int l = arrayLength-1; // length minus 1 gives the index of the last element
        // checks if odd amount and returns false
        if (arrayLength % 2 != 0){
            return false;
        }
        for(int i = 0; i < (arrayLength/2); i++)
        {
            if(ifEqual(StringToCharArray[i], StringToCharArray[i+1])){
                i++; // by incrementing i here it increments twice bc of the for-loop
                k = 1; // in order to not enter next if
                p = 1; // in order to not enter last else
            }
            if(ifEqual(StringToCharArray[i], StringToCharArray[l]) && (k != 1)){
                l--; // decrement the last unchecked position
                p = 1; // in order to not enter last else
            }
            // enter if none above are true
            else if(p == 0)
                {return false;}
            k = 0;
            p = 0;
        }
        return true;
    }
    /*
    this method checks if two parameters are equal
    three if statements, one for each type of parantheses
    it returns true if the comparison is balanced and
    false if it is not
     */
    public static boolean ifEqual(char first, char second){
        if ((first == '(') && (second == ')')){
            return true;
        }
        if((first == '{') && (second == '}'))
        {
            return true;
        }
        if((first == '[') && (second == ']'))
        {
            return true;
        }
        else
            return false;
    }
}
