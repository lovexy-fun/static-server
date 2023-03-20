package fun.lovexy.staticserver;

import org.apache.commons.cli.*;

public class CmdCLI {

    public final static Option HELP = new Option("h", "help", false, "Help");
    public final static Option PORT = newOptionWithType("p", "port", true, "Server port", Number.class);
    public final static Option CONTENT_ENCODING = newOptionWithType("e", "encoding", true, "Response content encoding", String.class);
    public final static Option STATIC_DIR = newOptionWithType("d", "dir", true, "Static resources directory", String.class);
    public final static Option MATCHED_PATH = newOptionWithType("m", "match", true, "URI paths that begin with this path will match", String.class);

    private final static Options OPTIONS = new Options()
            .addOption(HELP)
            .addOption(PORT)
            .addOption(CONTENT_ENCODING)
            .addOption(STATIC_DIR)
            .addOption(MATCHED_PATH);

    private final static DefaultParser DEFAULT_PARSER = new DefaultParser();
    private final static HelpFormatter HELP_FORMATTER = new HelpFormatter();

    private static Option newOptionWithType(String option, String longOption, boolean hasArg, String description, Class<?> type) {
        Option opt = new Option(option, longOption, hasArg, description);
        opt.setType(type);
        return opt;
    }

    private CommandLine cli;

    public CmdCLI(String[] args) {
        try {
            cli = DEFAULT_PARSER.parse(OPTIONS, args);
            if (cli.hasOption(HELP)) {
                HELP_FORMATTER.printHelp(" ", OPTIONS);
                System.exit(0);
            }
        } catch (ParseException e) {
            HELP_FORMATTER.printHelp(" ", OPTIONS);
            System.err.println(e.getMessage());
            System.exit(0);
        }
    }

    public Integer getPort() {
        if (cli.hasOption(PORT)) {
            try {
                return ((Long) cli.getParsedOptionValue(PORT)).intValue();
            } catch (ParseException e) {
                HELP_FORMATTER.printHelp(" ", OPTIONS);
                System.err.println(e.getMessage());
                System.exit(0);
            }
        }
        return null;
    }

    public String getContentEncoding() {
        return cli.getOptionValue(CONTENT_ENCODING);
    }

    public String getStaticDir() {
        return cli.getOptionValue(STATIC_DIR);
    }

    public String getMatchedPath() {
        return cli.getOptionValue(MATCHED_PATH);
    }

}
