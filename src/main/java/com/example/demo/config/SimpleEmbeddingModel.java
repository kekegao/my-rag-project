/*
package com.example.demo.config;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.Embedding;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingRequest;
import org.springframework.ai.embedding.EmbeddingResponse;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * 免 API Key 的自定义 Embedding 模型。
 * 基于字符哈希生成 384 维向量，使用词袋方式编码语义相似度。
 *//*

public class SimpleEmbeddingModel implements EmbeddingModel {

    private static final int DIMENSION = 384;

    @Override
    public EmbeddingResponse call(EmbeddingRequest request) {
        List<Embedding> embeddings = new ArrayList<>();
        for (String text : request.getInstructions()) {
            float[] vector = hashEmbed(text);
            embeddings.add(new Embedding(vector, 0));
        }
        return new EmbeddingResponse(embeddings);
    }

    @Override
    public float[] embed(Document document) {
        return hashEmbed(document.getText());
    }

    @Override
    public int dimensions() {
        return DIMENSION;
    }

    */
/**
     * 字符级哈希 + L2 归一化，生成 384 维向量。
     * 相似文本会产生相近的向量。
     *//*

    private float[] hashEmbed(String text) {
        float[] vec = new float[DIMENSION];
        if (text == null || text.isEmpty()) {
            return vec;
        }
        // 字符映射到向量维度
        for (int i = 0; i < text.length(); i++) {
            int idx = Math.abs(text.charAt(i)) % DIMENSION;
            vec[idx] += 1.0f;
        }
        // L2 归一化
        float norm = 0.0f;
        for (float v : vec) {
            norm += v * v;
        }
        norm = (float) Math.sqrt(norm);
        if (norm > 0) {
            for (int i = 0; i < DIMENSION; i++) {
                vec[i] /= norm;
            }
        }
        return vec;
    }
}
*/
