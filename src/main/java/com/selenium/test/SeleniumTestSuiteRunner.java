package com.selenium.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.selenium.dto.TestSuite;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class SeleniumTestSuiteRunner {
    public static void main(String[] args) {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        try {
            TestSuite testSuite = mapper.readValue(Thread.currentThread().getContextClassLoader().getResourceAsStream("am-web-test-case.yaml"), TestSuite.class);
            System.out.println(ReflectionToStringBuilder.toString(testSuite, ToStringStyle.MULTI_LINE_STYLE));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
