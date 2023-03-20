package fun.lovexy.staticserver;

import io.vertx.core.Vertx;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.StaticHandler;

public class Server {

    /** 启动端口 */
    private Integer port = 4096;

    /** 响应头编码 */
    private String contentEncoding = "UTF-8";

    /** 静态资源目录 */
    private String staticDir = "static";

    /** 静态资源匹配路径 */
    private String matchedPath = "/*";

    private Vertx vertx;

    public Server() {
        vertx = Vertx.vertx();
    }

    public Integer getPort() {
        return port;
    }

    public Server setPort(Integer port) {
        if (port != null) {
            this.port = port;
        }
        return this;
    }

    public String getContentEncoding() {
        return contentEncoding;
    }

    public Server setContentEncoding(String contentEncoding) {
        if (contentEncoding != null && !contentEncoding.isEmpty()) {
            this.contentEncoding = contentEncoding;
        }
        return this;
    }

    public String getStaticDir() {
        return staticDir;
    }

    public Server setStaticDir(String staticDir) {
        if (staticDir != null && !staticDir.isEmpty()) {
            this.staticDir = staticDir;
        }
        return this;
    }

    public String getMatchedPath() {
        return matchedPath;
    }

    public Server setMatchedPath(String matchedPath) {
        if (matchedPath != null && !matchedPath.isEmpty()) {
            this.matchedPath = matchedPath + "*";
        }
        return this;
    }

    /**
     * 启动服务
     */
    public void start() {
        Router router = Router.router(vertx);
        router.route(matchedPath)
                .handler(
                        StaticHandler.create(staticDir)
                                .setDefaultContentEncoding(contentEncoding)
                );
        vertx.createHttpServer()
                .requestHandler(router)
                .listen(port);
        System.out.println("Server running at http://localhost:" + port + "/");
    }

    /**
     * 停止服务
     */
    public void stop() {
        vertx.close();
    }

}