package net.chrisrichardson.faramir;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class FaramirController {

    private final PhoneNumberParser phoneNumberParser;

    @Autowired
    public FaramirController(PhoneNumberParser phoneNumberParser) {
        this.phoneNumberParser = phoneNumberParser;
    }

    @PostMapping("/parse")
    public ResponseEntity<?> parseNumber(@RequestBody ParsePhoneNumberRequest request) {
        String result = phoneNumberParser.parse(request.getPhoneNumber());
        if ("FAIL".equals(result)) {
            return ResponseEntity.status(400).build();
        } else {
            return ResponseEntity.ok(Map.of("result", result));
        }
    }

    public static class ParsePhoneNumberRequest {
        private String phoneNumber;

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }
}
