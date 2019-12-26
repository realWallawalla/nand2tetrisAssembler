package com.timonsarakinis.parser.command;

public class AddressCommand implements Command {
    private String value;

    public AddressCommand(String value) {
        this.value = value;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.A_COMMAND;
    }

    @Override
    public String getValue() {
        return value;
    }
}
