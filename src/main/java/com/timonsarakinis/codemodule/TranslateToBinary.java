package com.timonsarakinis.codemodule;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;
import static java.lang.Integer.toBinaryString;

public class TranslateToBinary {
    public static final int BASE = 111;
    private final Map<String, String> destinations = new HashMap<>();
    private final Map<String, String> computes = new HashMap<>();
    private final Map<String, String> jumps = new HashMap<>();

    public TranslateToBinary() {
        loadDestinations();
        loadComputes();
        loadJumps();
    }

    private void loadDestinations() {
        destinations.put("M", "001");
        destinations.put("D", "010");
        destinations.put("MD", "011");
        destinations.put("A", "100");
        destinations.put("AM", "101");
        destinations.put("AD", "110");
        destinations.put("AMD", "111");
        destinations.put("", "000");
    }

    private void loadComputes() {
        computes.put("0", "0101010");
        computes.put("1", "0111111");
        computes.put("-1", "0111010");
        computes.put("D", "0001100");
        computes.put("A", "0110000");
        computes.put("!D", "0001101");
        computes.put("!A", "0110001");
        computes.put("-D", "0001111");
        computes.put("-A", "0110011");
        computes.put("D+1", "0011111");
        computes.put("A+1", "0110111");
        computes.put("D-1", "0001110");
        computes.put("A-1", "0110010");
        computes.put("D+A", "0000010");
        computes.put("D-A", "0010011");
        computes.put("A-D", "0000111");
        computes.put("D&A", "0000000");
        computes.put("D|A", "0010101");
        computes.put("M", "1110000");
        computes.put("!M", "1110001");
        computes.put("-M", "1110011");
        computes.put("M+1", "1110111");
        computes.put("M-1", "1110010");
        computes.put("D+M", "1000010");
        computes.put("D-M", "1010011");
        computes.put("M-D", "1000111");
        computes.put("D&M", "1000000");
        computes.put("D|M", "1010101");
    }

    private void loadJumps() {
        jumps.put("", "000");
        jumps.put("JGT", "001");
        jumps.put("JEQ", "010");
        jumps.put("JGE", "011");
        jumps.put("JLT", "100");
        jumps.put("JNE", "101");
        jumps.put("JLE", "110");
        jumps.put("JMP", "111");
    }

    public String getComputeBinary(String compKey, String destKey, String jumpKey) {
        return BASE + comp(compKey) + dest(destKey) + jump(jumpKey);
    }

    /*remove 1 from to make 16bitBinary string. */
    public String getAddressBinary(String address) {
        String binaryString = toBinaryString(parseInt(address));
        return ("0000000000000000" + binaryString).substring(binaryString.length());
    }

    public String dest(String destination) {
        return destinations.get(destination);
    }

    public String comp(String compute) {
        return computes.get(compute);
    }

    public String jump(String jump) {
        return jumps.get(jump);
    }

    public byte[] translateStringToByte(String commandValue) {
        return (commandValue + System.lineSeparator()).getBytes();
    }
}
