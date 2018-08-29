package team.redrock.microboot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import team.redrock.microboot.StartSpringBootMain;
import team.redrock.microboot.service.serviceImp.ServiceImp;
import team.redrock.microboot.vo.ShortUrl;

@SpringBootTest(classes = StartSpringBootMain.class)
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class TestDeptService {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private ServiceImp serviceImp;
    @Test
    public void testConnection() throws Exception {
//        ShortUrl shortUrl = new ShortUrl();
//        shortUrl.setShortUrl("asdafa");
//        shortUrl.setLongUrl("www.baidu.com");
//        shortUrl.setExpiration(1);
//        shortUrl.setPassword("123");

//        this.redisTemplate.opsForValue().set("asdaf",shortUrl);
//        ShortUrl shortUrl2 = this.serviceImp.findValueByKey("asdafa");
//        System.out.println(shortUrl2.getLongUrl());
        String a = this.redisTemplate.opsForValue().get("fdgszxf");
        System.out.println(a);
    }
}
