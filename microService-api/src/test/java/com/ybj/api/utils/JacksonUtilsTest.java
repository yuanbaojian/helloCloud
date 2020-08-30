package com.ybj.api.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ybj.api.model.Dept;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JacksonUtilsTest {

    @Test
    void stringToObject() throws JsonProcessingException {
        Dept dept = new Dept();
        dept.setDeptNo("no");
        dept.setDName("name");

        List<Dept> deptList = new LinkedList<>();
        deptList.add(dept);

        String deptString = JacksonUtils.objectToJsonString(deptList);
    }
}