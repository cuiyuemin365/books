import java.io.Console;
import java.util.Scanner;

/**
 * This program demonstrates console input.
 *
 * @author Cay Horstmann
 * @version 1.10 2004-02-10
 */
public class InputTest {

    public static void test001() {
        Scanner in = new Scanner(System.in);
        System.out.print("What is your idea? ");
        String v2 = in.nextLine();//以换行符分隔
        System.out.print("What is your name? ");
        String v1 = in.next();//以空白符分隔
        System.out.print("How old are you? ");
        int v3 = in.nextInt();
        System.out.println(String.format("v1:%s;V2:%s;V3:%s", v1, v2, v3));
        // What is your idea? asdf asdvfw asegvq
        // What is your name? cuiyuemin
        // How old are you? 13
        // v1:cuiyuemin;V2:asdf asdvfw asegvq;V3:13
    }

    public static void test002() {
        Console console = System.console();
        String username = console.readLine("User name:");
        char[] password = console.readPassword("password:");
        System.out.println(username + ":" + password);
    }

    public static void test003() {
        System.out.printf("Hello， %s，you`ll be %d", "cuiyuemin", 23);
    }

    public static void main(String[] args) {
//        test002();
        test003();
    }
}
