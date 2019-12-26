package com.timonsarakinis;

import com.timonsarakinis.io.FileReaderWriter;
import com.timonsarakinis.parser.HackParser;
import com.timonsarakinis.symboltablemodule.SymbolTable;

import java.nio.file.Path;
import java.util.List;

import static com.timonsarakinis.io.FileReaderWriter.getOutputPath;
import static com.timonsarakinis.parser.command.CommandType.*;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class Main {

    public static final int MEMORY_ADDRESS_INIT_VALUE = 16;
    public static final int ROM_ADDRESS_INIT_VALUE = 0;

    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable();
        HackParser parser;
        String filePath = "src/main/resources/Pong.asm";
        List<String> lines = FileReaderWriter.readFile(filePath);
        if (lines.isEmpty()) {
            System.out.println("empty lines, nothing to parse");
            System.exit(0);
        }
        Path outputPath = getOutputPath();
        parser = new HackParser(lines);
        //first run through to collect symbols
        int romAddressCounter = ROM_ADDRESS_INIT_VALUE;
        while (parser.hasMoreCommands()) {
            parser.advance();
            String symbol = parser.symbol();
            if (parser.commandType() == C_COMMAND || parser.commandType() == A_COMMAND) {
                romAddressCounter += 1;
            }
            if (parser.commandType() == L_COMMAND) {
                symbolTable.addEntry(symbol, String.valueOf(romAddressCounter));
            }
        }
        //second run tanslate to binary
        int memoryAddressCounter = MEMORY_ADDRESS_INIT_VALUE;
        parser = new HackParser(lines);
        while (parser.hasMoreCommands()) {
            parser.advance();
            String symbol = parser.symbol();
            if (parser.commandType() == A_COMMAND && isNotBlank(symbol) && symbolTable.doesNotContain(symbol)) {
                symbolTable.addEntry(symbol, String.valueOf(memoryAddressCounter));
                memoryAddressCounter += 1;
            }
            byte[] bytes = parser.translateCurrentCommand();
            FileReaderWriter.writeToFile(bytes, outputPath);
        }
    }
}