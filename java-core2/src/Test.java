
public class Test {

    public static void main(String[] args) {
        int theNum = 10;
        int num;
        num = theNum << 1;
        System.out.println("左移一位：");
        print(theNum, num);
        //右移一位
        num = theNum >> 1;
        System.out.println("右移一位：");
        print(theNum, num);
        //无符号右移一位
        num = theNum >>> 1;
        System.out.println("无符号右移一位：");
        print(theNum, num);
        //无符号右移一位
        num = -10 >>> 1;
        System.out.println("无符号右移一位：");
        print(-10, num);
    }

    private static void print(int theNum, int num) {
        System.out.println(String.format("FROM:%s:%s;TO:%s:%s",
                theNum, Integer.toBinaryString(theNum), num, Integer.toBinaryString(num)));
    }
}