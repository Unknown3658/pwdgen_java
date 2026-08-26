package com.pwdgen.cli;

public final class CliArgs {
    public static final int DEFAULT_LENGTH = 16;
    public static final int MIN_LENGTH = 16;
    public static final int MAX_LENGTH = 256;
    public static final int DEFAULT_COUNT = 1;
    public static final int MIN_COUNT = 1;
    public static final int MAX_COUNT = 10000;
    
    private int length;
    private int count;
    private boolean lower;
    private boolean upper;
    private boolean digits;
    private boolean symbols;
    
    private CliArgs() {
        this.length = DEFAULT_LENGTH;
        this.count = DEFAULT_COUNT;
        this.lower = true;
        this.upper = true;
        this.digits = true;
        this.symbols = true;
    }
    
    public int length() {
        return length;
    }
    
    public int count() {
        return count;
    }
    
    public boolean lower() {
        return lower;
    }
    
    public boolean upper() {
        return upper;
    }
    
    public boolean digits() {
        return digits;
    }
    
    public boolean symbols() {
        return symbols;
    }
    
    public static CliArgs parse(String[] args) {
        CliArgs result = new CliArgs();
        int i = 0;
        
        while (i < args.length) {
            String arg = args[i];
            
            if (arg.equals("--help") || arg.equals("-h")) {
                printUsage();
                System.exit(0);
            } else if (arg.startsWith("--length=")) {
                try {
                    result.length = Integer.parseInt(arg.substring(9));
                    if (result.length < MIN_LENGTH) {
                        throw new CliException("Length must be >= " + MIN_LENGTH);
                    }
                    if (result.length > MAX_LENGTH) {
                        throw new CliException("Length must be <= " + MAX_LENGTH);
                    }
                } catch (NumberFormatException e) {
                    throw new CliException("Invalid length value: " + arg, e);
                }
            } else if (arg.startsWith("--count=")) {
                try {
                    result.count = Integer.parseInt(arg.substring(8));
                    if (result.count < MIN_COUNT) {
                        throw new CliException("Count must be >= " + MIN_COUNT);
                    }
                    if (result.count > MAX_COUNT) {
                        throw new CliException("Count must be <= " + MAX_COUNT);
                    }
                } catch (NumberFormatException e) {
                    throw new CliException("Invalid count value: " + arg, e);
                }
            } else if (arg.startsWith("-l=")) {
                try {
                    result.length = Integer.parseInt(arg.substring(3));
                    if (result.length < MIN_LENGTH) {
                        throw new CliException("Length must be >= " + MIN_LENGTH);
                    }
                    if (result.length > MAX_LENGTH) {
                        throw new CliException("Length must be <= " + MAX_LENGTH);
                    }
                } catch (NumberFormatException e) {
                    throw new CliException("Invalid length value: " + arg, e);
                }
            } else if (arg.startsWith("-c=")) {
                try {
                    result.count = Integer.parseInt(arg.substring(3));
                    if (result.count < MIN_COUNT) {
                        throw new CliException("Count must be >= " + MIN_COUNT);
                    }
                    if (result.count > MAX_COUNT) {
                        throw new CliException("Count must be <= " + MAX_COUNT);
                    }
                } catch (NumberFormatException e) {
                    throw new CliException("Invalid count value: " + arg, e);
                }
            } else if (arg.equals("-l")) {
                if (i + 1 >= args.length) {
                    throw new CliException("Missing value for -l");
                }
                try {
                    result.length = Integer.parseInt(args[++i]);
                    if (result.length < MIN_LENGTH) {
                        throw new CliException("Length must be >= " + MIN_LENGTH);
                    }
                    if (result.length > MAX_LENGTH) {
                        throw new CliException("Length must be <= " + MAX_LENGTH);
                    }
                } catch (NumberFormatException e) {
                    throw new CliException("Invalid length value: " + args[i], e);
                }
            } else if (arg.equals("-c")) {
                if (i + 1 >= args.length) {
                    throw new CliException("Missing value for -c");
                }
                try {
                    result.count = Integer.parseInt(args[++i]);
                    if (result.count < MIN_COUNT) {
                        throw new CliException("Count must be >= " + MIN_COUNT);
                    }
                    if (result.count > MAX_COUNT) {
                        throw new CliException("Count must be <= " + MAX_COUNT);
                    }
                } catch (NumberFormatException e) {
                    throw new CliException("Invalid count value: " + args[i], e);
                }
            } else if (arg.equals("-nl")) {
                result.lower = false;
            } else if (arg.equals("-nu")) {
                result.upper = false;
            } else if (arg.equals("-nd")) {
                result.digits = false;
            } else if (arg.equals("-ns")) {
                result.symbols = false;
            } else if (arg.equals("--no-lower")) {
                result.lower = false;
            } else if (arg.equals("--no-upper")) {
                result.upper = false;
            } else if (arg.equals("--no-digits")) {
                result.digits = false;
            } else if (arg.equals("--no-symbols")) {
                result.symbols = false;
            } else {
                throw new CliException("Unknown option: " + arg);
            }
            
            i++;
        }
        
        return result;
    }
    
    private static void printUsage() {
        System.out.println("Password Generator (pwdgen)");
        System.out.println();
        System.out.println("Usage: pwdgen [options]");
        System.out.println();
        System.out.println("Options:");
        System.out.println("  -h, --help              Show this help message");
        System.out.println("  -l, --length N          Password length (16 <= N <= 256, default 16)");
        System.out.println("  -c, --count M           Number of passwords (M >= 1, default 1)");
        System.out.println("  -nu                     Exclude uppercase letters");
        System.out.println("  -nd                     Exclude digits");
        System.out.println("  -ns                     Exclude symbols");
        System.out.println("  --no-upper              Exclude uppercase letters");
        System.out.println("  --no-digits             Exclude digits");
        System.out.println("  --no-symbols            Exclude symbols");
    }
}
