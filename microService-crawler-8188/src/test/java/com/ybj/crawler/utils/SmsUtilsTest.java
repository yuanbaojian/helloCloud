package com.ybj.crawler.utils;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

@Slf4j
@SpringBootTest
class SmsUtilsTest {

    @Test
    public void test(){
        YunpianClient client = new YunpianClient("21143c3addbf7b1449ac6e51cc1ed085").init();
        Map<String, String> param = client.newParam(2);
        param.put(YunpianClient.MOBILE, "18616020***");
        param.put(YunpianClient.TEXT, "【云片网】您的验证码是1234");
        Result<SmsSingleSend> result = client.sms().single_send(param);
        log.info("result : {}",result);
    }

    public static void main(String[] args) {
        YunpianClient client = new YunpianClient("21143c3addbf7b1449ac6e51cc1ed085").init();
        Map<String, String> param = client.newParam(2);
        param.put(YunpianClient.MOBILE, "18616020***");
        param.put(YunpianClient.TEXT, "【云片网】您的验证码是1234");
        Result<SmsSingleSend> result = client.sms().single_send(param);
        log.info("result : {}",result);
    }

}