package com.timonsarakinis.parser;

import com.timonsarakinis.codemodule.TranslateToBinary;
import com.timonsarakinis.factory.CommandFactory;
import com.timonsarakinis.parser.command.Command;
import com.timonsarakinis.parser.command.CommandType;
import com.timonsarakinis.parser.command.ComputeCommand;

import java.util.List;
import java.util.ListIterator;

import static com.timonsarakinis.parser.command.CommandType.*;
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
        if (commandType() == A_COMMAND) {
            return convertToBytes(currentCommand.getValue());
        }
        if (commandType() == C_COMMAND) {
            String computeBinary = translateToBinary.getComputeBinary(((ComputeCommand) currentCommand));
            return (computeBinary + System.lineSeparator()).getBytes();
        }
        if (commandType() == L_COMMAND) {
        }
        return new byte[0];
    }

    private byte[] convertToBytes(String address) {
        return (translateToBinary.getAddressBinary(address) + System.lineSeparator()).getBytes();
    }

    public CommandType commandType() {
        return currentCommand.getCommandType();
    }

    public String symbol() {
        return isNumeric(currentCommand.getValue()) ? "" : currentCommand.getValue();
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