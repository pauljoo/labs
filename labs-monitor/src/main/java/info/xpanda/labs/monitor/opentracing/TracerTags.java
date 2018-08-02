package info.xpanda.labs.monitor.opentracing;

public class TracerTags {
    /**
     * true|false
     */
    public static String TAG_ERROR = "error";

    /**
     * 监控组件类型
     */
    public static String TAG_COMPONENT = "component";
    public static String TAG_COMPONENT_JDBC = "jdbc";

    /**
     * client|server
     */
    public static String TAG_SPAN_KIND = "span.kind";
    public static String TAG_SPAN_KIND_CLIENT = "span.client";
    public static String TAG_SPAN_KIND_SERVER = "span.server";

    public static String TAG_HTTP_URL = "http.url";
    public static String TAG_HTTP_METHOD = "http.method";
    public static String TAG_HTTP_STATUS_CODE = "http.status_code";

    /**
     * 客户端记录下行访问，服务端记录上行访问
     */
    public static String TAG_PEER_HOSTNAME = "peer.hostname";
    public static String TAG_PEER_IPV4 = "peer.ipv4";
    public static String TAG_PEER_IPV6 = "peer.ipv6";
    public static String TAG_PEER_PORT = "peer.port";
    public static String TAG_PEER_SERVICE = "peer.service";

    /**
     * 全局:xpanda.global.*
     * 业务:xpanda.[业务名称].[模块名称].[业务点]
     */
    public static String XPANDA_MONITOR_POINT = "";
}
