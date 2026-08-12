/*
package com.example.demo.config;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class VectorStoreConfig {

    */
/**
     * 自定义 Embedding 模型，无需 API Key
     *//*

    @Bean
    @Primary
    public EmbeddingModel embeddingModel() {
        return new SimpleEmbeddingModel();
    }

    */
/**
     * 内存向量库，无需外部服务
     *//*

    @Bean
    public VectorStore vectorStore(EmbeddingModel embeddingModel) {
        return SimpleVectorStore.builder(embeddingModel).build();
    }
}
*/
