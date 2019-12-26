package com.timonsarakinis.parser.command;

public class ComputeCommand implements Command {
    private String dest;
    private String comp;
    private String jump;

    public ComputeCommand(String dest, String comp, String jump) {
        this.dest = dest;
        this.comp = comp;
        this.jump = jump;
    }

    @Override
    public CommandType getCommandType() {
        return CommandType.C_COMMAND;
    }

    @Override
    public String getValue() {
        String value = dest + "=" + comp + ";" + jump;
        if (jump.isBlank()) {
            value = dest + "=" + comp;
        }
        if (dest.isBlank()) {
            value = comp + ";" + jump;
        }
        return value;
    }

    public String getDest() {
        return dest;
    }

    public String getComp() {
        return comp;
    }

    public String getJump() {
        return jump;
    }
}
