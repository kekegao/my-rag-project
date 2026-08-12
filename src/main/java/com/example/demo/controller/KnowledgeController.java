package com.example.demo.controller;

import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/knowledge")
public class KnowledgeController {

    private final VectorStore vectorStore;

    public KnowledgeController(VectorStore vectorStore) {
        this.vectorStore = vectorStore;
    }

    @PostMapping("/upload")
    public String upload(@RequestParam("file") MultipartFile file) {

        System.out.println("文件名：" + file.getOriginalFilename());
        System.out.println("文件大小：" + file.getSize());

        // 后面在这里做：
        try {
            // 1. 文档解析
            String text = new String(file.getBytes(),StandardCharsets.UTF_8);
            Document document = new Document(text);
            // 2. 文本切分
            TokenTextSplitter splitter = new TokenTextSplitter();
            List<Document> chunks = splitter.apply(List.of(document));
            // 3. 保存 Milvus
            vectorStore.add(chunks);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return "上传成功";
    }
}
