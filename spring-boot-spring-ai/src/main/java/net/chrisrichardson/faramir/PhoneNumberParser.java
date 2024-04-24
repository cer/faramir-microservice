package net.chrisrichardson.faramir;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhoneNumberParser {

    private final ChatClient chatClient;

    @Autowired
    public PhoneNumberParser(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String parse(String phoneNumber) {
        ChatResponse response = chatClient.call(
                new Prompt("""
Instruction: 
What follows is a phone number.
Validate the phone number
If it is a valid US phone number print it in the standard format, eg (205) 555-1212.
Otherwise print FAIL. 
Print only the phone number or FAIL. 
No other words                    

phone number: '%s'
 """.formatted(phoneNumber)));
        return response.getResult().getOutput().getContent();
    }
}
