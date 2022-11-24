package numbers;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import static numbers.Properties.*;

public class Main {

    public static final String THE_FIRST_PARAMETER_SHOULD_BE_A_NATURAL_NUMBER_OR_ZERO = "The first parameter should be a natural number or zero.";
    public static final String THE_SECOND_PARAMETER_SHOULD_BE_A_NATURAL_NUMBER = "The second parameter should be a natural number.";

    public static void main(String[] args) {
//        write your code here
        System.out.println("Welcome to Amazing Numbers!");
        System.out.println();
        printInstructions();

        long num = 0;
        long repetitions = 0;

        do {
            System.out.print("Enter a request:");
            Scanner scanner = new Scanner(System.in);
            String[] numStrs = scanner.nextLine().split(" ");
            List<String> validationMessages = new ArrayList<>();

            for (int i = 0; i < numStrs.length; i++) {
                if (i == 0) {
                    num = validateNumber(i,
                            numStrs[0],
                            validationMessages);
                } else if (i == 1) {
                    repetitions = validateNumber(i,
                            numStrs[1],
                            validationMessages);
                } else {
                    break;
                }

            }

            if (num > 0 && numStrs.length == 1) {
                NumberProperties numberProps = NumberProperties.buildNumberProperties(num);
                numberProps.listAllProperties();
            } else if (numStrs.length > 1) {
                String[] propsToSearch = Arrays.copyOfRange(numStrs,
                        2,
                        numStrs.length);

                boolean isAllPropsValid = !hasInvalidProperties(propsToSearch,
                        validationMessages);
                boolean isMutex = isMutuallyExclusive(Arrays.asList(propsToSearch),
                        validationMessages);

                if (isAllPropsValid && !isMutex) {
                    findAndPrintNumbersWithProp(num,
                            repetitions,
                            propsToSearch);
                }

            }
            validationMessages.forEach(System.out::println);

        } while (num != 0);

        System.out.println("Goodbye!");
    }

    private static long validateNumber(int index, String numStr, List<String> validationMessages) {

        boolean isPositiveNumber = numStr.matches("\\d+");
        if (index == 0 && !isPositiveNumber) {
            validationMessages.add(THE_FIRST_PARAMETER_SHOULD_BE_A_NATURAL_NUMBER_OR_ZERO);
            return -1;
        } else if (index == 1 && (!isPositiveNumber || numStr.equals("0"))) {
            validationMessages.add(THE_SECOND_PARAMETER_SHOULD_BE_A_NATURAL_NUMBER);
            return -1;
        }

        return Long.parseLong(numStr);
    }

    private static boolean hasInvalidProperties(String[] propsToSearch, List<String> validationMessages) {
        int invalidCount = 0;
        List<String> invalidProps = new ArrayList<>();

        for (String propToSearch : propsToSearch) {
            if(propToSearch.startsWith("-")) {
                propToSearch = propToSearch.substring(1);
            }
            if (isInvalidProperty(propToSearch)) {
                invalidCount++;
                invalidProps.add(propToSearch);
            }
        }
        //How to make all strings in the string collection invalidProps uppercase?
        invalidProps = invalidProps.stream().map(String::toUpperCase).collect(Collectors.toList());

        if (invalidCount == 1) {
            validationMessages.add(String.format("The property [%s] is wrong.%n",
                    invalidProps.get(0)));
            validationMessages.add(String.format("Available properties: [%s]%n",
                    Properties.getPropertiesAsList()));
            return true;
        } else if (invalidCount > 1) {
            validationMessages.add(String.format("The properties [%s] are wrong.%n",
                    String.join(", ",
                            invalidProps)));
            validationMessages.add(String.format("Available properties: [%s]%n",
                    Properties.getPropertiesAsList()));
            return true;
        }
        return false;
    }

    private static boolean isMutuallyExclusive(List<String> propsToSearch, List<String> validationMessages) {

        final AtomicBoolean isMutexProps = new AtomicBoolean(false);

        if (propsToSearch.contains(EVEN.toString().toLowerCase()) && propsToSearch.contains(ODD.toString().toLowerCase())) {
            addMutuallyExclusivePropsMsg(validationMessages,
                    EVEN.toString(),
                    ODD.toString());
            isMutexProps.set(true);
        }
        if (propsToSearch.contains("-" + EVEN.toString().toLowerCase()) && propsToSearch.contains("-" + ODD.toString().toLowerCase())) {
            addMutuallyExclusivePropsMsg(validationMessages,
                    "-" + EVEN.toString(),
                    "-" + ODD.toString());
            isMutexProps.set(true);
        }
        if (propsToSearch.contains(SQUARE.toString().toLowerCase()) && propsToSearch.contains(SUNNY.toString().toLowerCase())) {
            addMutuallyExclusivePropsMsg(validationMessages,
                    SQUARE.toString(),
                    SUNNY.toString());
            isMutexProps.set(true);
        }
        if (propsToSearch.contains("-" + SQUARE.toString().toLowerCase()) && propsToSearch.contains("-" + SUNNY.toString().toLowerCase())) {
            addMutuallyExclusivePropsMsg(validationMessages,
                    "-" + SQUARE.toString(),
                    "-" + SUNNY.toString());
            isMutexProps.set(true);
        }
        if (propsToSearch.contains(SPY.toString().toLowerCase()) && propsToSearch.contains(DUCK.toString().toLowerCase())) {
            addMutuallyExclusivePropsMsg(validationMessages,
                    SPY.toString(),
                    DUCK.toString());
            isMutexProps.set(true);
        }
        if (propsToSearch.contains("-" + SPY.toString().toLowerCase()) && propsToSearch.contains("-" + DUCK.toString().toLowerCase())) {
            addMutuallyExclusivePropsMsg(validationMessages,
                    "-" + SPY.toString(),
                    "-" + DUCK.toString());
            isMutexProps.set(true);
        }
        if (propsToSearch.contains(SAD.toString().toLowerCase()) && propsToSearch.contains(HAPPY.toString().toLowerCase())) {
            addMutuallyExclusivePropsMsg(validationMessages,
                    SAD.toString(),
                    HAPPY.toString());
            isMutexProps.set(true);
        }
        if (propsToSearch.contains("-" + SAD.toString().toLowerCase()) && propsToSearch.contains("-" + HAPPY.toString().toLowerCase())) {
            addMutuallyExclusivePropsMsg(validationMessages,
                    "-" + SAD.toString(),
                    "-" + HAPPY.toString());
            isMutexProps.set(true);
        }

        propsToSearch.forEach(prop -> {
            String negProp = "-" + prop;
            if (propsToSearch.contains(negProp)) {
                addMutuallyExclusivePropsMsg(validationMessages,
                        prop.toUpperCase(),
                        negProp.toUpperCase());
                isMutexProps.set(true);
            }
        });

        return isMutexProps.get();
    }

    private static void addMutuallyExclusivePropsMsg(List<String> validationMessages, String prop1, String prop2) {
        validationMessages.add(String.format("The request contains mutually exclusive properties: [%s, %s]%n",
                prop1,
                prop2));
        validationMessages.add(String.format("There are no numbers with these properties.%n"));
    }

    private static void findAndPrintNumbersWithProp(long num, long repetitions, String[] propsToSearch) {
        List<String> propsToInclude = Arrays.asList(propsToSearch).stream().filter(prop -> !prop.startsWith("-")).collect(Collectors.toList());
        List<String> propsToExclude = Arrays.asList(propsToSearch).stream().filter(prop -> prop.startsWith("-")).map(prop -> prop.substring(1)).collect(Collectors.toList());

        long count = 1;
        for (long i = num; count <= repetitions; i++) {
            NumberProperties numberProps = NumberProperties.buildNumberProperties(i);
            if (checkIfNumberIncludesAllProp(numberProps,
                    propsToInclude) && checkIfNumberExcludesAllProp(numberProps,
                    propsToExclude)) {
                printFoundProperties(i,
                        numberProps);
                count++;
            }
        }
    }

    private static boolean checkIfNumberExcludesAllProp(NumberProperties numberProps, List<String> propsToExclude) {
        for (String prop : propsToExclude) {
            String propUpper = prop.toUpperCase();
            if (numberProps.hasProp(propUpper)) {
                return false;
            }
        }
        return true;
    }

    private static boolean checkIfNumberIncludesAllProp(NumberProperties numberProps, List<String> propsToInclude) {
        for (String prop : propsToInclude) {
            String propUpper = prop.toUpperCase();
            if (!numberProps.hasProp(propUpper)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isInvalidProperty(String propToSearch) {
        return !Properties.contains(propToSearch.toUpperCase());
    }

    private static void printFoundProperties(long i, NumberProperties numberProps) {
        //format i in thousands separator
        String formattedNum = String.format("%,d",
                i);
        System.out.println(formattedNum + " is " + (numberProps.isBuzz() ? "buzz, " : "")
                + (numberProps.isDuck() ? "duck, " : "")
                + (numberProps.isPalindromic() ? "palindromic, " : "")
                + (numberProps.isGapful() ? "gapful, " : "")
                + (numberProps.isSpy() ? "spy, " : "")
                + (numberProps.isSquare() ? "square, " : "")
                + (numberProps.isSunny() ? "sunny, " : "")
                + (numberProps.isJumping() ? "jumping, " : "")
                + (numberProps.isHappy() ? "happy, " : "")
                + (numberProps.isSad() ? "sad, " : "")
                + (numberProps.isEven() ? "even " : "")
                + (numberProps.isOdd() ? "odd " : "")
                + "");
    }

    private static void printInstructions() {
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");
        System.out.println();
    }
}
