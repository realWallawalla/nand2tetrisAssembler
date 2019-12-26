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
        if (line.contains(";"))  {
            return new ComputeCommand("", line.substring(0, line.indexOf(";")), line.substring(line.indexOf(";")+1));
        }
        if (line.contains("="))  {
            return new ComputeCommand(line.substring(0, line.indexOf("=")), line.substring(line.indexOf("=")+1), "");
        }
        return new CommandIgnore();
    }
}