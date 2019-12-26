package com.timonsarakinis.io;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class FileReaderWriterTest {


    @Test
    void should_read_text_file() {
        List<String> lines = FileReaderWriter.readFile("src/main/resources/testFile.asm");
        String line = lines.stream().collect(Collectors.joining());
        assertThat(line).isEqualTo("helloWorld");
    }

    @Test
    void return_empty_if_no_file() {
        List<String> lines = FileReaderWriter.readFile("src/testFile.asm");
        String line = lines.stream().collect(Collectors.joining());
        assertThat(line).isEmpty();
    }
}
