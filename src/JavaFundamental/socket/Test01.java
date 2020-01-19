package JavaFundamental.socket;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 用InetAddress实现域名解析
 */
public class Test01 {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress netAddress = InetAddress.getByName("github.com");
        System.out.println(netAddress.getHostAddress());
    }
}
