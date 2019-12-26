package com.timonsarakinis.parser.command;

import static com.timonsarakinis.parser.command.CommandType.A_COMMAND;

public class AddressCommand implements Command {
    private String value;

    public AddressCommand(String value) {
        this.value = value;
    }

    @Override
    public CommandType getCommandType() {
        return A_COMMAND;
    }

    @Override
    public String getValue() {
        return value;
    }
}
