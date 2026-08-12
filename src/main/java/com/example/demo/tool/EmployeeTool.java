package com.example.demo.tool;

import com.example.demo.dto.EmployeeInfo;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class EmployeeTool {

    @Tool(description = "根据员工姓名查询员工的基本信息，包括部门、职位和入职时间")
    public EmployeeInfo getEmployeeInfo(@ToolParam(description = "员工姓名") String name) {

        System.out.println("====== AI正在调用 getEmployeeInfo ======");

        // 这里以后可以真正查询 MySQL
        return new EmployeeInfo(
                name,
                "技术部",
                "Java高级开发工程师",
                "2018-06-01"
        );
    }

    @Tool(description = "查询员工今年剩余的年假天数")
    public String getRemainingLeave(
            @ToolParam(description = "员工ID") Long employeeId) {

        // 实际项目这里查询 MySQL
        Integer days = 6;

        return "员工" + employeeId +
                "今年剩余年假：" + days + "天";
    }
}
