
class EnglishAlphabet {

    public static StringBuilder createEnglishAlphabet() {
        // write your code
        StringBuilder sb = new StringBuilder();
        for (char c = 'A'; c <= 'Z'; c++) {
            sb.append(c).append(" ");
        }
        sb.deleteCharAt(sb.lastIndexOf(" "));
        return sb;
    }

//    public static void main(String[] args) {
//        StringBuilder sb = createEnglishAlphabet();
//        System.out.println("**" + sb + "**");
//    }
}