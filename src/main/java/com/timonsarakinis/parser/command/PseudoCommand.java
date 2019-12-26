package com.timonsarakinis.parser.command;

public class PseudoCommand implements Command {

    private String symbol;

    public PseudoCommand(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.L_COMMAND;
    }

    @Override
    public String getValue() {
        return symbol;
    }
}
