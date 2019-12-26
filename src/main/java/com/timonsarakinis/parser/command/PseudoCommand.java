package com.timonsarakinis.parser.command;

public class PseudoCommand implements Command {

    private String computeFormat;

    public PseudoCommand(String computeFormat) {
        this.computeFormat = computeFormat;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.L_COMMAND;
    }

    @Override
    public String getValue() {
        return computeFormat;
    }
}
