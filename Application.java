import java.util.Scanner;

//main command line app
public class Application {
    public static void main(String[] args) {
        ManualSolvers app = new ManualSolvers();
        int bits = 8;

        //allows users to ask for help if they need it
        System.out.println("Welcome to the 2's Complement Algorithm Speed Comparator!");
        Scanner stdin = new Scanner(System.in);
        while(true) {
            System.out.println("To continue, type any key. For help, type help: ");
            String input = stdin.next();
                if(input.equalsIgnoreCase("help"))  {
                    System.out.println("This program has been designed to test two ways of converting 2's Complement \nbinary strings into their decimal format. The first method involves using Java's\nnative libraries to complement the binary strings, convert them to decimal\nusing Java's native method, and then add one. The second method involves\ngrabbing the largest value from the binary string, and subtracting each subsequently\nsmaller value from the largest value. The program aims to prove that both methods of \nconversion are accurate, and aims to test wether or not one is more efficient!\nFor more information, check out: http://bit.ly/2diOZI6");
                    System.out.println();
                }else{
                    System.out.println();
                    break;
                }
            }

        //checks if a user entered an int divisible by 8, if not, they are told to try again
        while(true) {
            System.out.print("(Note: Only supports multiples of 8, up to 24) Enter the number of bits: ");
            String input1 = stdin.next();
            try{
                bits = Integer.parseInt(input1);
                if(bits % 8 == 0) {
                    System.out.println("Thank you!");
                    break;
                }else{
                    System.out.println("Needs to be an 8 bit multiple!");
                }
            }catch(NumberFormatException e){
                System.out.println("Not a number, try again");
            }
        }
        System.out.println("\n\n");

        //begins test for generating all possible negative values
        long start = System.currentTimeMillis();
        System.out.println("***Now generating all possible "+bits+"-bit negative binary values***");
        app.collector(bits);
        long end = System.currentTimeMillis();
        long result = (end - start);
        System.out.println("Took "+result+" milliseconds.");
        //end of test

        //begins test that both methods result in the same decimal conversion
        boolean proof = true;
        System.out.println("\n\n");
        System.out.println("***Now testing both 2's Complement solutions for "+bits+"-bit binary values***");
        start = System.currentTimeMillis();
        for(String value: app.getValues()){
            int newMethod = app.tryTwosComplement(value);
            long javaMethod = app.getTwosComplement(value);
            if(javaMethod!=newMethod){
               proof = false;
            }
        }if(proof){
            System.out.println("Both solutions have been proved!");
        }else{
            System.out.println("Test failed!");
        }
        end = System.currentTimeMillis();
        result = (end - start);
        System.out.println("Took "+result+" milliseconds");
        //end of test

        //tests which algorithm is faster in milliseconds
        long newTime = 0;
        long javaTime = 0;
        System.out.println("\n\n");
        System.out.println("***Now testing which solution is faster for "+bits+"-bit binary values***");
        start = System.currentTimeMillis();
        for(String value: app.getValues()) {
            int newMethod = app.tryTwosComplement(value);
        }
        end = System.currentTimeMillis();
        newTime = (end - start);
        start = System.currentTimeMillis();
        for(String value: app.getValues()) {
            long javaMethod = app.getTwosComplement(value);
        }
        end = System.currentTimeMillis();
        javaTime = (end - start);
        System.out.println("It took Java's solution "+javaTime+" milliseconds!");
        System.out.println("It took the new solution "+newTime+" milliseconds!");
        String utt = stdin.nextLine();
    }

}