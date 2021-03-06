package com.timonsarakinis.symboltablemodule;

import java.util.Hashtable;
import java.util.Map;

public class SymbolTable {
    private final static Map<String, String> table = new Hashtable<>();
    private static boolean initilized;

    public SymbolTable() {
        if (!initilized) {
            addPredefinedValue();
            initilized = true;
        }
    }

    private void addPredefinedValue() {
        addEntry("R0", "0");
        addEntry("R1", "1");
        addEntry("R2", "2");
        addEntry("R3", "3");
        addEntry("R4", "4");
        addEntry("R5", "5");
        addEntry("R6", "6");
        addEntry("R7", "7");
        addEntry("R8", "8");
        addEntry("R9", "9");
        addEntry("R10", "10");
        addEntry("R11", "11");
        addEntry("R12", "12");
        addEntry("R13", "13");
        addEntry("R14", "14");
        addEntry("R15", "15");
        addEntry("SCREEN", "16384");
        addEntry("KBD", "24576");
        addEntry("SP", "0");
        addEntry("LCL", "1");
        addEntry("ARG", "2");
        addEntry("THIS", "3");
        addEntry("THAT", "4");
    }

    public void addEntry(String symbol, String address) {
        table.putIfAbsent(symbol, address);
    }

    public boolean doesNotContain(String symbol) {
        return !table.containsKey(symbol);
    }

    public String getAddress(String symbol) {
        return table.get(symbol);
    }
}
