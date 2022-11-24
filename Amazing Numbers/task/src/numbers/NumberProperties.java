package numbers;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

enum Properties {
    BUZZ("BUZZ"),
    DUCK("DUCK"),
    PALINDROMIC("PALINDROMIC"),
    GAPFUL("GAPFUL"),
    SPY("SPY"),
    SQUARE("SQUARE"),
    SUNNY("SUNNY"),
    JUMPING("JUMPING"),
    HAPPY("HAPPY"),
    SAD("SAD"),
    EVEN("EVEN"),
    ODD("ODD");

    private final String property;

    Properties(String property) {
        this.property = property;
    }

    public String getProperty() {
        return property;
    }

    public static boolean contains(String property) {
        for (Properties p : Properties.values()) {
            if (p.getProperty().equals(property)) {
                return true;
            }
        }
        return false;
    }

    public static String getPropertiesAsList() {
        StringJoiner sj = new StringJoiner(", ");
        for (Properties p : Properties.values()) {
            sj.add(p.getProperty());
        }
        return sj.toString();
    }

}

public class NumberProperties {

    private long number;
    private boolean buzz;
    private boolean duck;
    private boolean palindromic;
    private boolean gapful;
    private boolean spy;
    private boolean square;
    private boolean sunny;

    private boolean jumping;

    private boolean happy;

    private boolean sad;
    private boolean even;
    private boolean odd;

    public NumberProperties(long number) {
        this.number = number;
    }

    public static NumberProperties buildNumberProperties(long num) {
        NumberProperties numberProperties = new NumberProperties(num);
        numberProperties.setBuzz(numberProperties.checkIsBuzz());
        numberProperties.setDuck(numberProperties.checkIsDuck());
        numberProperties.setPalindromic(numberProperties.checkIsPalindromic());
        numberProperties.setGapful(numberProperties.checkIsGapful());
        numberProperties.setSpy(numberProperties.checkIsSpy());
        numberProperties.setSquare(numberProperties.checkIsSquare(num));
        numberProperties.setSunny(numberProperties.checkIsSunny());
        numberProperties.setJumping(numberProperties.checkIsJumping());
        numberProperties.setHappy(numberProperties.checkIsHappy());
        numberProperties.setSad(numberProperties.checkIsSad());
        numberProperties.setEven(numberProperties.checkIsEven());
        numberProperties.setOdd(numberProperties.checkIsOdd());
        return numberProperties;
    }

    public void listAllProperties() {
        System.out.printf("Properties of %,d",
                this.getNumber());
        System.out.println();
        System.out.println("         buzz: " + this.isBuzz());
        System.out.println("         duck: " + this.isDuck());
        System.out.println("         palindromic: " + this.isPalindromic());
        System.out.println("         gapful: " + this.isGapful());
        System.out.println("         spy: " + this.isSpy());
        System.out.println("         square: " + this.isSquare());
        System.out.println("         sunny: " + this.isSunny());
        System.out.println("         jumping: " + this.isJumping());
        System.out.println("         happy: " + this.isHappy());
        System.out.println("         sad: " + this.isSad());
        System.out.println("         even: " + this.isEven());
        System.out.println("         odd: " + this.isOdd());
        System.out.println();
    }

    public boolean checkIsBuzz() {
        return this.number % 7 == 0 || this.number % 10 == 7;
    }

    public boolean checkIsPalindromic() {
        return String.valueOf(this.number).equals(new StringBuilder(String.valueOf(this.number)).reverse().toString());
    }

    public boolean checkIsGapful() {
        long num = this.number;
        String numAsString = String.valueOf(num);
        return numAsString.length() > 2
                && num % Long.parseLong(
                numAsString.substring(0,
                        1)
                        + numAsString.substring(numAsString.length() - 1)) == 0;
    }

    public boolean checkIsDuck() {
        return String.valueOf(this.number).contains("0");
    }

    public boolean checkIsSpy() {
        long sum = 0;
        long product = 1;
        long num = this.number;
        while (num > 0) {
            long digit = num % 10;
            sum += digit;
            product *= digit;
            num /= 10;
        }
        return sum == product;
    }

    private boolean checkIsSunny() {
        return checkIsSquare(this.number + 1);
    }

    private boolean checkIsSquare(long num) {
        double sqrt = Math.sqrt(num);
        return sqrt == Math.floor(sqrt);
    }

    private boolean checkIsJumping() {
        //Check number of digits in a long
        StringBuilder numAsString = new StringBuilder(String.valueOf(this.number));
        int numDigits = numAsString.length();
        boolean isJumping = true;
        //Comparing two adjacent digits in a StringBuilder
        for (int i = 0; i < numDigits - 1; i++) {
            int firstDigit = Character.getNumericValue(numAsString.charAt(i));
            int secondDigit = Character.getNumericValue(numAsString.charAt(i + 1));
            if (Math.abs(firstDigit - secondDigit) != 1) {
                isJumping = false;
                break;
            }
        }
        return isJumping;
    }

    private boolean checkIsHappy() {
        long num = this.number;
        long sum = 0;

        while (sum != 1 ) {
            sum =0;
            String numAsString = String.valueOf(num);
            int numDigits = numAsString.length();
            for (int i = 0; i < numDigits; i++) {
                int digit = Character.getNumericValue(numAsString.charAt(i));
                sum += Math.pow(digit,
                        2);
            }
            //Unhappy numbers?
            if(sum == 1 || sum == 4) {
                break;
            } else {
                num = sum;
            }

        }
        return sum == 1;
    }

    private boolean checkIsSad() {
        return !this.checkIsHappy();
    }

    private boolean checkIsEven() {
        return this.number % 2 == 0;
    }

    private boolean checkIsOdd() {
        return this.number % 2 != 0;
    }

    public boolean hasProp(String propToCheck) {
        return switch (propToCheck) {
            case "BUZZ" -> buzz;
            case "DUCK" -> duck;
            case "PALINDROMIC" -> palindromic;
            case "GAPFUL" -> gapful;
            case "SPY" -> spy;
            case "SQUARE" -> square;
            case "SUNNY" -> sunny;
            case "JUMPING" -> jumping;
            case "HAPPY" -> happy;
            case "SAD" -> sad;
            case "EVEN" -> even;
            case "ODD" -> odd;
            default -> false;
        };

    }

    public long getNumber() {
        return number;
    }

    public boolean isBuzz() {
        return buzz;
    }

    public void setBuzz(boolean buzz) {
        this.buzz = buzz;
    }

    public boolean isDuck() {
        return duck;
    }

    public void setDuck(boolean duck) {
        this.duck = duck;
    }

    public boolean isPalindromic() {
        return palindromic;
    }

    public void setPalindromic(boolean palindromic) {
        this.palindromic = palindromic;
    }

    public boolean isGapful() {
        return gapful;
    }

    public void setGapful(boolean gapful) {
        this.gapful = gapful;
    }

    public boolean isSpy() {
        return spy;
    }

    public void setSpy(boolean spy) {
        this.spy = spy;
    }

    public boolean isSquare() {
        return square;
    }

    public void setSquare(boolean square) {
        this.square = square;
    }

    public boolean isSunny() {
        return sunny;
    }

    public void setSunny(boolean sunny) {
        this.sunny = sunny;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public boolean isHappy() {
        return happy;
    }

    public void setHappy(boolean happy) {
        this.happy = happy;
    }

    public boolean isSad() {
        return sad;
    }

    public void setSad(boolean sad) {
        this.sad = sad;
    }

    public boolean isEven() {
        return even;
    }

    public void setEven(boolean even) {
        this.even = even;
    }

    public boolean isOdd() {
        return odd;
    }

    public void setOdd(boolean odd) {
        this.odd = odd;
    }

}
