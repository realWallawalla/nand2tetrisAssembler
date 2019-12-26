package com.timonsarakinis.parser.command;

public interface Command {
    CommandType getCommandType();

    String getValue();
}
