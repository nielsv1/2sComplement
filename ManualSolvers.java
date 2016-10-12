import java.util.ArrayList;
import java.util.List;

public class ManualSolvers {
    private List<String> possibleValues= new ArrayList<>();

    //java's way of solving
    public static long getTwosComplement(String binaryInt) {
        if (binaryInt.charAt(0) == '1') {
            String invertedInt = invertDigits(binaryInt);
            long decimalValue = Long.parseLong(invertedInt, 2);
            decimalValue = (decimalValue + 1) * -1;
            return decimalValue;
        } else {
            return Integer.parseInt(binaryInt, 2);
        }
    }

    //new way of solving
    public static int tryTwosComplement(String binaryInt){
        char[]bitz = binaryInt.toCharArray();
        int size = bitz.length;
        int output = 0;
        int multiplier = 1;
        for(int i=size-1;i>0;i--){
            if(bitz[i]=='1'){
                output+=multiplier;
            }
            multiplier = multiplier * 2;
        }
        output = (multiplier - output);
        return output * -1;
    }

    public static String invertDigits(String binaryInt) {
        String result = binaryInt;
        result = result.replace("0", " ");
        result = result.replace("1", "0");
        result = result.replace(" ", "1");
        return result;
    }

    public void collector(int bits) {
        for (int i = (int) -(Math.pow(2, bits - 1)); i < 0; i++) {
            String temp = Long.toBinaryString(i);
            String output = temp.substring(temp.length() - bits, temp.length());
            possibleValues.add(output);
        }
    }

    public List<String> getValues(){
        return possibleValues;
    }
}



