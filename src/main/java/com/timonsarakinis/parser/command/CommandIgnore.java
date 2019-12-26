package com.timonsarakinis.parser.command;

public class CommandIgnore implements Command {
    @Override
    public CommandType getCommandType() {
        return CommandType.COMMENT;
    }

    @Override
    public String getValue() {
        return "";
    }
}
