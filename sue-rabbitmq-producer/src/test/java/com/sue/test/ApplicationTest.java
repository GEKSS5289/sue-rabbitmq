package com.sue.test;

import com.sue.JsonUtils;
import com.sue.Users;
import com.sue.component.RabbitSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sue
 * @date 2020/8/15 14:44
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTest {
    @Autowired
    private RabbitSender rabbitSender;

    @Test
    public void testSender() throws Exception{

        Map<String,Object> properties = new HashMap<>();
        properties.put("attr1","12345");
        properties.put("attr2","abcde");
        rabbitSender.send(JsonUtils.objectToJson(new Users("ss", "1")),properties);
        Thread.sleep(10000);
    }
}
