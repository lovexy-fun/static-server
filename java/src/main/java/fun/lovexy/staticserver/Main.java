package fun.lovexy.staticserver;

public class Main {

    public static void main(String[] args) {

        try {
            CmdCLI cmdCLI = new CmdCLI(args);
            Server server = new Server();
            server.setPort(cmdCLI.getPort())
                    .setContentEncoding(cmdCLI.getContentEncoding())
                    .setStaticDir(cmdCLI.getStaticDir())
                    .setMatchedPath(cmdCLI.getMatchedPath())
                    .start();
            Runtime.getRuntime().addShutdownHook(new Thread(server::stop));
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

}
