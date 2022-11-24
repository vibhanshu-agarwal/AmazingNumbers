import java.util.Scanner;

class ManufacturingController {
    // here you may declare a field
    private static int nrOfProducts = 0;

    public static String requestProduct(String product) {
        // write your code here
        nrOfProducts++;
        return String.format("%d. Requested %s", nrOfProducts, product);
    }

    public static int getNumberOfProducts() {
        // write your code here
        return nrOfProducts;
    }
}

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String product = scanner.nextLine();
            System.out.println(ManufacturingController.requestProduct(product));
            System.out.println(ManufacturingController.getNumberOfProducts());
        }
    }
}