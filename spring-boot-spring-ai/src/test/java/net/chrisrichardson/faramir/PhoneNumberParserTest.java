package net.chrisrichardson.faramir;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ContextConfiguration(classes = {PhoneNumberParserTest.Config.class})
public class PhoneNumberParserTest {

    @ComponentScan
    @EnableAutoConfiguration
    public static class Config {
    }

    @Autowired
    private PhoneNumberParser phoneNumberParser;

    @Test
    public void testValidNumber() {
        assertEquals("(510) 555-1212", phoneNumberParser.parse("5105551212"));
    }

    @Test
    public void testValidNumberSpaces() {
        assertEquals("(510) 555-1212", phoneNumberParser.parse("510 555 1212"));
    }

    @Test
    public void testValidNumberFormatted() {
        assertEquals("(510) 555-1212", phoneNumberParser.parse("(510) 555-1212"));
    }

    @Test
    public void testBadNumber() {
        assertEquals("FAIL", phoneNumberParser.parse("510555121"));
    }


}
