enum DangerLevel {
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    final int levelNumber;
    DangerLevel(int levelNumber) {
        this.levelNumber = levelNumber;
    }

    public int getLevel() {
        return levelNumber;
    }
}

//class Main
//{
//    public static void main(String[] args) {
//        DangerLevel high = DangerLevel.HIGH;
//        DangerLevel medium = DangerLevel.MEDIUM;
//
//        System.out.println(high.getLevel() > medium.getLevel());
//
//    }
//}