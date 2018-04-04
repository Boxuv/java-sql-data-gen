import java.util.Random;

public class Utility {

    public static int randomNumberAtoB(int min, int max){
        Random random = new Random();
        int answer = min + random.nextInt((max - min) + 1);
        return answer;
    }

    public static int randomDayInMonthBounded(int min, int month, int year){
        int maxDays = min;
        switch (month) {
            case 1: case 3: case 5:
            case 7: case 8: case 10:
            case 12:
                maxDays = 31;
                break;
            case 4: case 6:
            case 9: case 11:
                maxDays = 30;
                break;
            case 2:
                if (((year % 4 == 0) &&
                        !(year % 100 == 0))
                        || (year % 400 == 0))
                    maxDays = 29;
                else
                    maxDays = 28;
                break;
            default:
                System.out.println("Invalid month.");
                break;
        }
        int answer = randomNumberAtoB(min,maxDays);
        return answer;
    }

    public static String randomString(int length){

        StringBuilder returnString = new StringBuilder(length);
        String possibleChars = "abcdefghijklmnopqrstuvwxyz";

        for(int i=0; i<length; i++){
            char c = possibleChars.charAt(randomNumberAtoB(0,possibleChars.length()-1));
            returnString.append(c);
        }

        return returnString.toString();
    }

    public static String numberFiller(int number){
        StringBuilder returnString = new StringBuilder();
        if (number <10){
            returnString.append(0);
        }
        returnString.append(number);
        return returnString.toString();
    }
}