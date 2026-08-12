package com.example.demo.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

@Component
public class OrderTool {

    @Tool(description = "根据订单号查询订单基本信息")
    public String getOrder(
            @ToolParam(description = "订单号") String orderId) {

        // 查询MySQL
        return "订单" + orderId +
                "状态：运输中，当前位置：上海";
    }

    @Tool(description = "根据订单号查询物流轨迹")
    public String getLogistics(
            @ToolParam(description = "订单号") String orderId) {

        return "订单" + orderId +
                "物流：深圳 → 上海，目前在上海转运中心";
    }
}
