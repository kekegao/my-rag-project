package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.LoginResponse;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {


    // 模拟用户数据存储
    private static final Map<String, String> USER_DB = new ConcurrentHashMap<>();

    private final VectorStore vectorStore;
    static {
        USER_DB.put("admin", "123456");
        USER_DB.put("user", "password");
    }

    public UserService(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    public String testChat(){


        /*EmbeddingResponse embedding =
                embeddingModel.embedForResponse(
                        List.of("Spring AI 是 Spring 官方 AI 框架"));

        System.out.println(
                embedding.getResults().get(0).getOutput().length);*/

        //Document document = new Document("Spring AI 是一个AI应用开发框架");

        //vectorStore.add(List.of(document));

        String question = "高可可是一个什么样的人?";

        SearchRequest request =
                SearchRequest.builder()
                        .query(question)
                        .topK(5)
                        .similarityThreshold(0.7)
                        .build();


        List<Document> docs = vectorStore.similaritySearch(request);
        /*List<Document> filtered = docs.stream()
                .filter(doc -> {
                    Double score = doc.getMetadata().get("distance") instanceof Number
                            ? ((Number) doc.getMetadata().get("distance")).doubleValue()
                            : 0.0;

                    return score >= threshold;
                })
                .toList();*/

        System.out.println("===== 向量检索结果 =====");
        for (int i = 0; i < docs.size(); i++) {
            System.out.println("结果" + (i + 1) + ": " + docs.get(i).getText());
        }
        System.out.println("======================");

        return "test";
    }

    /**
     * 用户登录
     *
     * @param request 登录请求（用户名 + 密码）
     * @return 登录响应（包含 token 或错误信息）
     */
    public LoginResponse login(LoginRequest request) {
        // 1. 参数校验
        if (request.getUsername() == null || request.getUsername().isBlank()) {
            return LoginResponse.fail("用户名不能为空");
        }
        if (request.getPassword() == null || request.getPassword().isBlank()) {
            return LoginResponse.fail("密码不能为空");
        }

        // 2. 校验用户名密码
        String storedPassword = USER_DB.get(request.getUsername());
        if (storedPassword == null) {
            return LoginResponse.fail("用户名或密码错误");
        }
        if (!storedPassword.equals(request.getPassword())) {
            return LoginResponse.fail("用户名或密码错误");
        }

        // 3. 登录成功，生成 token
        String token = UUID.randomUUID().toString().replace("-", "");
        return LoginResponse.success(token);
    }


}
