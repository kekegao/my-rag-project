package com.example.demo.service;

import com.example.demo.tool.EmployeeTool;
import com.example.demo.tool.KnowledgeBaseTool;
import com.example.demo.tool.OrderTool;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiAgentService {

    private final ChatClient chatClient;

    private final KnowledgeBaseTool knowledgeBaseTool;
    private final EmployeeTool employeeTool;
    private final OrderTool orderTool;

    public AiAgentService(
            ChatClient.Builder builder,
            KnowledgeBaseTool knowledgeBaseTool,
            EmployeeTool employeeTool,
            OrderTool orderTool) {

        this.chatClient = builder.build();
        this.knowledgeBaseTool = knowledgeBaseTool;
        this.employeeTool = employeeTool;
        this.orderTool = orderTool;
    }

    public String chat(String question) {

        return chatClient
                .prompt()
                .system("""
                        你是一个企业智能助手。
                        
                        你拥有以下能力：
                        
                         1. 直接回答
                            对于普通知识、常识、技术概念、
                            一般性分析等问题，如果你已经知道答案，
                            可以直接回答，不需要调用任何工具。
        
                         2. 查询公司知识库
                            仅当用户询问公司内部制度、员工手册、
                            公司规定、内部技术文档等信息时，
                            才调用知识库工具。
        
                         3. 查询员工信息
                            当用户需要查询员工实时业务数据时，
                            调用员工工具。
        
                         4. 查询订单和物流
                            当用户需要查询订单或物流实时信息时，
                            调用订单/物流工具。
        
                         请根据用户问题自主判断是否需要调用工具。
        
                         如果不需要工具，直接回答。
        
                         不要为了回答问题而强制调用工具。
        
                         如果调用知识库后没有找到相关信息，
                         不要编造公司内部信息。
        
                         ===== 回答要求 =====
                         1. 回答准确、简洁
                         2. 不要凭空编造
                        """)
                .user("""
                       用户ID：10001
                       用户问题：
                       %s
                       ===== 回答要求 =====
                       1. 回答准确、简洁
                       2. 不要凭空编造
                      """.formatted(question))
                .tools(
                        knowledgeBaseTool,
                        employeeTool,
                        orderTool
                )
                .call()
                .content();
    }
}
