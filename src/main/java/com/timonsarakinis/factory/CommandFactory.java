package com.timonsarakinis.factory;

import com.timonsarakinis.parser.command.*;

public class CommandFactory {
    public static Command createCommand(String line) {
        if (line.isBlank() || line.charAt(0) == '/') {
            return new CommandIgnore();
        }
        if (line.charAt(0) == '@') {
            return new AddressCommand(line.substring(1));
        }
        if (line.charAt(0) == '(') {
            String symbol = line.substring(1, line.indexOf(')'));
            return new PseudoCommand(symbol);
        }
        String lineWithoutComments = groomLine(line);
        if (lineWithoutComments.contains(";"))  {
            return new ComputeCommand("", lineWithoutComments.substring(0, line.indexOf(";")), lineWithoutComments.substring(line.indexOf(";")+1));
        }
        if (lineWithoutComments.contains("="))  {
            return new ComputeCommand(lineWithoutComments.substring(0, lineWithoutComments.indexOf("=")), lineWithoutComments.substring(line.indexOf("=")+1), "");
        }
        return new CommandIgnore();
    }

    private static String groomLine(String line) {
        return line.contains("/") ? line.substring(0, line.indexOf('/')).trim() : line;
    }
}