package OneTwo;
import edu.*;
import java.util.Scanner;

public class lab12 {
    public static void main (String[] args)
    {
        int q = 0;
        while (q == 0)
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("");
            System.out.println("1 = recursive, 2 = iterative, 3 = quit");
            int in = scanner.nextInt();
            switch (in)
            {
                case 1:
                    recursive();
                    break;
                case 2:
                    iterative();
                    break;
                case 3:
                    q = 1;
            }
        }

    }
    static void recursive() {
        char ch = StdIn.readChar();
        if (ch != '\n')
        {
            recursive(); // call recursively
            StdOut.print(ch); // print
        }
    }
    static void iterative()
    {
        char ch;
        Stack stack = new Stack();
        while ((ch = (char) StdIn.readChar()) != '\n')
        {
            stack.push(ch);
        }
        while(stack.isEmpty() == false) // while stack is not empty
        {
            StdOut.print(stack.pop());
        }
    }
}
