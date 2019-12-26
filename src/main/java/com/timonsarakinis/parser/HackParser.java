package com.timonsarakinis.parser;

import com.timonsarakinis.codemodule.TranslateToBinary;
import com.timonsarakinis.factory.CommandFactory;
import com.timonsarakinis.parser.command.Command;
import com.timonsarakinis.parser.command.CommandType;
import com.timonsarakinis.parser.command.ComputeCommand;

import java.util.List;
import java.util.ListIterator;

import static com.timonsarakinis.parser.command.CommandType.*;
import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.lang3.StringUtils.isNumeric;

public class HackParser implements Parser {

    private final ListIterator<String> iterator;
    private Command currentCommand;
    private TranslateToBinary translateToBinary = new TranslateToBinary();

    public HackParser(List<String> lines) {
        this.iterator = lines.listIterator();
    }

    @Override
    public boolean hasMoreCommands() {
        return iterator.hasNext();
    }

    @Override
    public void advance() {
        if (hasMoreCommands()) {
            currentCommand = CommandFactory.createCommand(iterator.next().trim());
        } else {
            System.out.println("no more commands");
        }
    }

    public byte[] translateCurrentCommand() {
        if (commandType() == A_COMMAND && isNumeric(currentCommand.getValue())) {
            return (translateToBinary.getAddressBinary(currentCommand.getValue()) + System.lineSeparator()).getBytes(UTF_8);
        }
        if (commandType() == C_COMMAND) {
            String computeBinary = translateToBinary.getComputeBinary(((ComputeCommand) currentCommand).getComp(), ((ComputeCommand) currentCommand).getDest(),
                    ((ComputeCommand) currentCommand).getJump());
            return (computeBinary + System.lineSeparator()).getBytes(UTF_8);
        }
        if (commandType() == L_COMMAND) {
        }
        return new byte[0];
    }

    public CommandType commandType() {
        return currentCommand.getCommandType();
    }

    public String symbol() {
        if (currentCommand.getCommandType() == A_COMMAND || currentCommand.getCommandType() == L_COMMAND) {
            return currentCommand.getValue();
        }
        return "";
    }

    public String dest() {
        if (currentCommand.getCommandType() == C_COMMAND) {
            return ((ComputeCommand) currentCommand).getDest();
        }
        return "";
    }
    public String comp() {
        if (currentCommand.getCommandType() == C_COMMAND) {
            return ((ComputeCommand) currentCommand).getComp();
        }
        return "";
    }
    public String jump() {
        if (currentCommand.getCommandType() == C_COMMAND) {
            return ((ComputeCommand) currentCommand).getJump();
        }
        return "";
    }
}