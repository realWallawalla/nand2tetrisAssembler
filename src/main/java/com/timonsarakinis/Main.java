package com.timonsarakinis;

import com.timonsarakinis.io.FileReaderWriter;
import com.timonsarakinis.parser.HackParser;

import java.nio.file.Path;
import java.util.List;

import static com.timonsarakinis.io.FileReaderWriter.getPath;

public class Main {
    public static void main(String[] args) {
        HackParser parser;
        //String filePath = (String) Array.get(args, 0);
        String filePath = "C:/nand2tetris/nand2tetris/nand2tetris/projects/06/pong/PongL.asm";
        List<String> lines = FileReaderWriter.readFile(filePath);
        if (lines.isEmpty()) {
            System.out.println("empty lines, nothing to parse");
            System.exit(0);
        }
        Path path = getPath();
        parser = new HackParser(lines);
        //first run through to collect symobls
        while (parser.hasMoreCommands()) {
            parser.advance();
            byte[] bytes = parser.translateCurrentCommand();
            FileReaderWriter.writeToFile(bytes, path);
        }
        //second run tanslate to binary
        while (parser.hasMoreCommands()) {
            parser.advance();
            byte[] bytes = parser.translateCurrentCommand();
            FileReaderWriter.writeToFile(bytes, path);
        }
    }
}