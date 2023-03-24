package cn.gyw.individual.commons.utils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Objects;

/**
 * 系统工具
 *
 * @date 2023/3/24
 */
public final class SystemUtil {

    private static final String LOCAL_HOST = "127.0.0.1";

    private static final String SYS_PROP_OS_NAME = "os.name";

    private static final String OS_WINDOWS = "Windows";

    private static final String HOST_SPLIT = ":";

    /**
     * 获取本机IP
     *
     * @return host ip地址
     * @throws Exception 异常
     */
    public static String getLocalIp() throws Exception {
        InetAddress inet = null;
        String osName = System.getProperty(SYS_PROP_OS_NAME);
        if (OS_WINDOWS.compareToIgnoreCase(osName) < 0) {
            inet = InetAddress.getLocalHost();
        } else {
            inet = getUnixLocalIp();
        }
        if (Objects.nonNull(inet)) {
            return inet.getHostAddress();
        }
        return LOCAL_HOST;
    }

    private static InetAddress getUnixLocalIp() throws Exception {
        Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
        if (null == netInterfaces) {
            return null;
        }
        InetAddress ip;
        while (netInterfaces.hasMoreElements()) {
            NetworkInterface ni = netInterfaces.nextElement();
            if (ni.isUp()) {
                Enumeration<InetAddress> addressEnumeration = ni.getInetAddresses();
                while (addressEnumeration.hasMoreElements()) {
                    ip = addressEnumeration.nextElement();
                    if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
                            && !ip.getHostAddress().contains(HOST_SPLIT)) {
                        return ip;
                    }
                }
            }
        }
        return null;
    }

    private SystemUtil() {
        throw new UnsupportedOperationException();
    }
}
