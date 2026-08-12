package com.example.demo.service;

import com.example.demo.tool.EmployeeTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatService {

    private final VectorStore vectorStore;

    private final ChatClient chatClient;

    private final EmployeeTool employeeTool;

    public ChatService(ChatClient.Builder builder,VectorStore vectorStore,EmployeeTool employeeTool) {
        this.chatClient = builder.build();
        this.vectorStore = vectorStore;
        this.employeeTool = employeeTool;
    }

    public String testChat(String question){

        SearchRequest request =
                SearchRequest.builder()
                        .query(question)
                        .topK(5)
                        .similarityThreshold(0.7)
                        .build();

        List<Document> documents = vectorStore.similaritySearch(request);
        // 2. 没有检索到知识
        if (documents.isEmpty()) {
            return chatClient.prompt()
                    .user(question)
                    .call()
                    .content();
        }

        // 3. 把检索结果拼接起来
        String context = documents.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n\n"));

        // 4. 把检索结果交给 DeepSeek
        String systemPrompt = """
                你是一个企业知识库智能助手。
                请严格根据下面提供的知识库内容回答用户问题。
                如果知识库中没有相关信息，请明确回答：
                “知识库中没有找到相关信息。”
                不要编造知识库中不存在的信息。
                """;
        String userPrompt = """
                %s
                ===== 用户问题 =====
                %s
                ===== 回答要求 =====
                1. 根据知识库内容回答
                2. 回答准确、简洁
                3. 不要凭空编造
                """.formatted(context, question);

        // 5. DeepSeek 根据检索结果回答
        return chatClient
                .prompt()
                .system(systemPrompt)
                .user(userPrompt)
                .tools(employeeTool)
                .call()
                .content();

    }

}
