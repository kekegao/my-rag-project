package com.example.demo;

import com.example.demo.service.UserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class Demo1ApplicationTests {

    @Resource
    UserService userService;

    @Test
    void testChat() {
        String result = userService.testChat();
        assertNotNull(result, "返回值不应为空");
        assertEquals("test", result, "应返回 test");
    }

}
