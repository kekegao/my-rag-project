package com.example.demo.tool;

import org.springframework.ai.document.Document;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KnowledgeBaseTool {

    private final VectorStore vectorStore;

    public KnowledgeBaseTool(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @Tool(description = """
            查询公司内部知识库。
            
            仅当用户的问题涉及以下公司内部信息时使用：
            - 公司规章制度
            - 员工手册
            - 公司福利政策
            - 公司年假制度
            - 公司考勤制度
            - 公司薪酬制度
            - 公司内部技术规范
            - 公司内部产品文档
            
            不要用于：
            - 普通知识
            - 新闻
            - 历史
            - 科普
            - 编程基础知识
            - 与公司内部资料无关的问题
            
            如果用户的问题不涉及公司内部知识，
            不要调用此工具。
            """)
    public String searchKnowledge(
            @ToolParam(description = "需要查询的问题") String question) {

        List<Document> documents =
                vectorStore.similaritySearch(
                        SearchRequest.builder()
                                .query(question)
                                .topK(5)
                                .build()
                );

        if (documents.isEmpty()) {
            return "知识库中没有找到相关信息";
        }

        return documents.stream()
                .map(Document::getText)
                .collect(Collectors.joining("\n\n"));
    }
}
