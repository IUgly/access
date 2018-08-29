package team.redrock.microboot.service.serviceImp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import team.redrock.microboot.service.IService;

import team.redrock.microboot.vo.ShortUrl;

@Service
public class ServiceImp implements IService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public String findNum() {
        this.stringRedisTemplate.boundValueOps("num").increment(1);
        return this.stringRedisTemplate.opsForValue().get("num");
    }

    @Override
    public void insertNum() {
        this.stringRedisTemplate.opsForValue().set("num", "10");
        return;
    }


    @Override
    public void insertKey(String key, String shortUrl) {
        this.stringRedisTemplate.opsForValue().set("key", shortUrl);
    }

    @Override
    public String findValueByKey(String key) {
        return this.stringRedisTemplate.opsForValue().get("key");
    }
}
