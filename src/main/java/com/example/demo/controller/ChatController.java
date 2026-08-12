package com.example.demo.controller;

import com.example.demo.service.AiAgentService;
import com.example.demo.service.ChatService;
import com.example.demo.service.UserService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/chatControl")
public class ChatController {


    private final AiAgentService aiAgentService;

    public ChatController(AiAgentService  aiAgentService) {
        this.aiAgentService = aiAgentService;
    }

    @PostMapping("/chat")
    public Map<String,String> chat(@RequestBody Map<String, String> request) {
        String message = request.get("message");
        System.out.println(message);
        System.out.println("***********");
        String res = aiAgentService.chat(message);
        System.out.println(res);
        return Map.of(
                "reply", res
        );

    }
}
