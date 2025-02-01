package online.mizak.phdict.utils;

public class Color {

    // Reset
    public static final String RESET = "\033[0m";  // Text Reset

    // Regular Colors
    public static final String BLACK = "\033[0;30m";   // Black
    public static final String RED = "\033[0;31m";     // Red
    public static final String GREEN = "\033[0;32m";   // Green
    public static final String YELLOW = "\033[0;33m";  // Yellow
    public static final String BLUE = "\033[0;34m";    // Blue
    public static final String PURPLE = "\033[0;35m";  // Purple
    public static final String CYAN = "\033[0;36m";    // Cyan
    public static final String WHITE = "\033[0;37m";   // White

    // Bold
    public static final String BLACK_BOLD = "\033[1;30m";  // Black Bold
    public static final String RED_BOLD = "\033[1;31m";    // Red Bold
    public static final String GREEN_BOLD = "\033[1;32m";  // Green Bold
    public static final String YELLOW_BOLD = "\033[1;33m"; // Yellow Bold
    public static final String BLUE_BOLD = "\033[1;34m";   // Blue Bold
    public static final String PURPLE_BOLD = "\033[1;35m"; // Purple Bold
    public static final String CYAN_BOLD = "\033[1;36m";   // Cyan Bold
    public static final String WHITE_BOLD = "\033[1;37m";  // White Bold

    // Underline
    public static final String BLACK_UNDERLINED = "\033[4;30m";  // Black Underlined
    public static final String RED_UNDERLINED = "\033[4;31m";    // Red Underlined
    public static final String GREEN_UNDERLINED = "\033[4;32m";  // Green Underlined
    public static final String YELLOW_UNDERLINED = "\033[4;33m"; // Yellow Underlined
    public static final String BLUE_UNDERLINED = "\033[4;34m";   // Blue Underlined
    public static final String PURPLE_UNDERLINED = "\033[4;35m"; // Purple Underlined
    public static final String CYAN_UNDERLINED = "\033[4;36m";   // Cyan Underlined
    public static final String WHITE_UNDERLINED = "\033[4;37m";  // White Underlined

    // Background
    public static final String BLACK_BACKGROUND = "\033[40m";  // Black Background
    public static final String RED_BACKGROUND = "\033[41m";    // Red Background
    public static final String GREEN_BACKGROUND = "\033[42m";  // Green Background
    public static final String YELLOW_BACKGROUND = "\033[43m"; // Yellow Background
    public static final String BLUE_BACKGROUND = "\033[44m";   // Blue Background
    public static final String PURPLE_BACKGROUND = "\033[45m"; // Purple Background
    public static final String CYAN_BACKGROUND = "\033[46m";   // Cyan Background
    public static final String WHITE_BACKGROUND = "\033[47m";  // White Background

    // High Intensity
    public static final String BLACK_BRIGHT = "\033[0;90m";  // Black Bright
    public static final String RED_BRIGHT = "\033[0;91m";    // Red Bright
    public static final String GREEN_BRIGHT = "\033[0;92m";  // Green Bright
    public static final String YELLOW_BRIGHT = "\033[0;93m"; // Yellow Bright
    public static final String BLUE_BRIGHT = "\033[0;94m";   // Blue Bright
    public static final String PURPLE_BRIGHT = "\033[0;95m"; // Purple Bright
    public static final String CYAN_BRIGHT = "\033[0;96m";   // Cyan Bright
    public static final String WHITE_BRIGHT = "\033[0;97m";  // White Bright

    // Bold High Intensity
    public static final String BLACK_BOLD_BRIGHT = "\033[1;90m"; // Black Bold Bright
    public static final String RED_BOLD_BRIGHT = "\033[1;91m";   // Red Bold Bright
    public static final String GREEN_BOLD_BRIGHT = "\033[1;92m"; // Green Bold Bright
    public static final String YELLOW_BOLD_BRIGHT = "\033[1;93m";// Yellow Bold Bright
    public static final String BLUE_BOLD_BRIGHT = "\033[1;94m";  // Blue Bold Bright
    public static final String PURPLE_BOLD_BRIGHT = "\033[1;95m";// Purple Bold Bright
    public static final String CYAN_BOLD_BRIGHT = "\033[1;96m";  // Cyan Bold Bright
    public static final String WHITE_BOLD_BRIGHT = "\033[1;97m"; // White Bold Bright

    // High Intensity Backgrounds
    public static final String BLACK_BACKGROUND_BRIGHT = "\033[0;100m";// Black Background Bright
    public static final String RED_BACKGROUND_BRIGHT = "\033[0;101m";// Red Background Bright
    public static final String GREEN_BACKGROUND_BRIGHT = "\033[0;102m";// Green Background Bright
    public static final String YELLOW_BACKGROUND_BRIGHT = "\033[0;103m";// Yellow Background Bright
    public static final String BLUE_BACKGROUND_BRIGHT = "\033[0;104m";// Blue Background Bright
    public static final String PURPLE_BACKGROUND_BRIGHT = "\033[0;105m"; // Purple Background Bright
    public static final String CYAN_BACKGROUND_BRIGHT = "\033[0;106m";  // Cyan Background Bright
    public static final String WHITE_BACKGROUND_BRIGHT = "\033[0;107m";   // White Background Bright

    // Methods to simplify concatenation

    public static String black(String msg) {
        return appendWithReset(BLACK + msg);
    }

    public static String red(String msg) {
        return appendWithReset(RED + msg);
    }

    public static String green(String msg) {
        return appendWithReset(GREEN + msg);
    }

    public static String yellow(String msg) {
        return appendWithReset(YELLOW + msg);
    }

    public static String blue(String msg) {
        return appendWithReset(BLUE + msg);
    }

    public static String purple(String msg) {
        return appendWithReset(PURPLE + msg);
    }

    public static String cyan(String msg) {
        return appendWithReset(CYAN + msg);
    }

    public static String white(String msg) {
        return appendWithReset(WHITE + msg);
    }

    public static String blackBold(String msg) {
        return appendWithReset(BLACK_BOLD + msg);
    }

    public static String redBold(String msg) {
        return appendWithReset(RED_BOLD + msg);
    }

    public static String greenBold(String msg) {
        return appendWithReset(GREEN_BOLD + msg);
    }

    public static String yellowBold(String msg) {
        return appendWithReset(YELLOW_BOLD + msg);
    }

    public static String blueBold(String msg) {
        return appendWithReset(BLUE_BOLD + msg);
    }

    public static String purpleBold(String msg) {
        return appendWithReset(PURPLE_BOLD + msg);
    }

    public static String cyanBold(String msg) {
        return appendWithReset(CYAN_BOLD + msg);
    }

    public static String whiteBold(String msg) {
        return appendWithReset(WHITE_BOLD + msg);
    }

    public static String blackUnderlined(String msg) {
        return appendWithReset(BLACK_UNDERLINED + msg);
    }

    public static String redUnderlined(String msg) {
        return appendWithReset(RED_UNDERLINED + msg);
    }

    public static String greenUnderlined(String msg) {
        return appendWithReset(GREEN_UNDERLINED + msg);
    }

    public static String yellowUnderlined(String msg) {
        return appendWithReset(YELLOW_UNDERLINED + msg);
    }

    public static String blueUnderlined(String msg) {
        return appendWithReset(BLUE_UNDERLINED + msg);
    }

    public static String purpleUnderlined(String msg) {
        return appendWithReset(PURPLE_UNDERLINED + msg);
    }

    public static String cyanUnderlined(String msg) {
        return appendWithReset(CYAN_UNDERLINED + msg);
    }

    public static String whiteUnderlined(String msg) {
        return appendWithReset(WHITE_UNDERLINED + msg);
    }

    public static String blackBackground(String msg) {
        return appendWithReset(BLACK_BACKGROUND + msg);
    }

    public static String redBackground(String msg) {
        return appendWithReset(RED_BACKGROUND + msg);
    }

    public static String greenBackground(String msg) {
        return appendWithReset(GREEN_BACKGROUND + msg);
    }

    public static String yellowBackground(String msg) {
        return appendWithReset(YELLOW_BACKGROUND + msg);
    }

    public static String blueBackground(String msg) {
        return appendWithReset(BLUE_BACKGROUND + msg);
    }

    public static String purpleBackground(String msg) {
        return appendWithReset(PURPLE_BACKGROUND + msg);
    }

    public static String cyanBackground(String msg) {
        return appendWithReset(CYAN_BACKGROUND + msg);
    }

    public static String whiteBackground(String msg) {
        return appendWithReset(WHITE_BACKGROUND + msg);
    }

    public static String blackBoldBright(String msg) {
        return appendWithReset(BLACK_BOLD_BRIGHT + msg);
    }

    public static String redBoldBright(String msg) {
        return appendWithReset(RED_BOLD_BRIGHT + msg);
    }

    public static String greenBoldBright(String msg) {
        return appendWithReset(GREEN_BOLD_BRIGHT + msg);
    }

    public static String yellowBoldBright(String msg) {
        return appendWithReset(YELLOW_BOLD_BRIGHT + msg);
    }

    public static String blueBoldBright(String msg) {
        return appendWithReset(BLUE_BOLD_BRIGHT + msg);
    }

    public static String purpleBoldBright(String msg) {
        return appendWithReset(PURPLE_BOLD_BRIGHT + msg);
    }

    public static String cyanBoldBright(String msg) {
        return appendWithReset(CYAN_BOLD_BRIGHT + msg);
    }

    public static String whiteBoldBright(String msg) {
        return appendWithReset(WHITE_BOLD_BRIGHT + msg);
    }

    public static String blackBackgroundBright(String msg) {
        return appendWithReset(BLACK_BACKGROUND_BRIGHT + msg);
    }

    public static String redBackgroundBright(String msg) {
        return appendWithReset(RED_BACKGROUND_BRIGHT + msg);
    }

    public static String greenBackgroundBright(String msg) {
        return appendWithReset(GREEN_BACKGROUND_BRIGHT + msg);
    }

    public static String yellowBackgroundBright(String msg) {
        return appendWithReset(YELLOW_BACKGROUND_BRIGHT + msg);
    }

    public static String blueBackgroundBright(String msg) {
        return appendWithReset(BLUE_BACKGROUND_BRIGHT + msg);
    }

    public static String purpleBackgroundBright(String msg) {
        return appendWithReset(PURPLE_BACKGROUND_BRIGHT + msg);
    }

    public static String cyanBackgroundBright(String msg) {
        return appendWithReset(CYAN_BACKGROUND_BRIGHT + msg);
    }

    public static String whiteBackgroundBright(String msg) {
        return appendWithReset(WHITE_BACKGROUND_BRIGHT + msg);
    }

    public static String blackBright(String msg) {
        return appendWithReset(BLACK_BRIGHT + msg);
    }

    public static String redBright(String msg) {
        return appendWithReset(RED_BRIGHT + msg);
    }

    public static String greenBright(String msg) {
        return appendWithReset(GREEN_BRIGHT + msg);
    }

    public static String yellowBright(String msg) {
        return appendWithReset(YELLOW_BRIGHT + msg);
    }

    public static String blueBright(String msg) {
        return appendWithReset(BLUE_BRIGHT + msg);
    }

    public static String purpleBright(String msg) {
        return appendWithReset(PURPLE_BRIGHT + msg);
    }

    public static String cyanBright(String msg) {
        return appendWithReset(CYAN_BRIGHT + msg);
    }

    public static String whiteBright(String msg) {
        return appendWithReset(WHITE_BRIGHT + msg);
    }

    // Other helper methods
    public static String appendWithReset(String msg) {
        return msg + RESET;
    }

    public static String tag(String msg) {
        return greenBold("[" + msg + "] ");
    }

    public static String concat(String... args) {
        StringBuilder sb = new StringBuilder();
        for (String arg : args) {
            sb.append(arg);
        }
        return sb.toString();
    }

}
