package team.redrock.microboot;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.security.MessageDigest;
import java.util.Stack;

/**
 * Created By 谭健 2017年6月17日 22:17:04（TEL:15197447018）
 *
 * @version 2.0
 *
 *
 *          加解密算法
 *          用户输入网址
 *          放号器放出一个号码，转为64进制 h
 *          存redis,key-value.  64进制字符对应用户输入的网址
 *          64进制字符与服务器域名拼接返回用户json
 *
 *          用户访问短连接
 *          获取路径（此时路径为存库的64进制）
 *          通过该路径查库，返回原本的网址
 */
public class PECode {
    public static void main(String[] args) throws IOException {
        System.out.println("64进制：" + encode(201314520));
        System.out.println("10进制：" + decode(encode(201314520)));

    }

    /**
     * 打乱改字符数组的组合顺序，可以得到不同的转换结果
     */
    private static final char[] array = { 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's', 'd', 'f', 'g','h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b', 'n', 'm', '8', '5', '2', '7', '3', '6', '4', '0', '9', '1', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', '+', '-' };

    /**
     * @param number
     *            double类型的10进制数,该数必须大于0
     * @return string类型的64进制数
     */
    public static String encode(double number) {
        double rest = number;
        // 创建栈
        Stack<Character> stack = new Stack<Character>();
        StringBuilder result = new StringBuilder(0);
        while (rest >= 1) {
            // 进栈,
            // 也可以使用(rest - (rest / 64) * 64)作为求余算法
            stack.add(array[new Double(rest % 64).intValue()]);
            rest = rest / 64;
        }
        for (; !stack.isEmpty();) {
            // 出栈
            result.append(stack.pop());
        }
        return result.toString();

    }

    /**
     * 支持范围是A-Z,a-z,0-9,+,-
     *
     * @param str 64进制的数字
     * @return 10进制的数字
     */
    public static double decode(String str) {
        // 倍数
        int multiple = 1;
        double result = 0;
        Character c;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(str.length() - i - 1);
            result += decodeChar(c) * multiple;
            multiple = multiple * 64;
        }
        return result;
    }

    private static int decodeChar(Character c) {
        for (int i = 0; i < array.length; i++) {
            if (c == array[i]) {
                return i;
            }
        }
        return -1;
    }
}