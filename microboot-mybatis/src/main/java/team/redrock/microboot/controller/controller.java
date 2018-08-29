package team.redrock.microboot.controller;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import team.redrock.microboot.PECode;
import team.redrock.microboot.util.controller.AbstractBaseController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;

@Controller
public class controller extends AbstractBaseController {


    public static final String realm = "http://www.cqyyss.club/";
    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/short")
    public String getShort(String url, Model model){
        Random rand = new Random();
        Long timeMillis = System.currentTimeMillis();
        Long random= Long.valueOf(rand.nextInt(100));

        String key = PECode.encode(timeMillis+random);
        this.redisTemplate.opsForValue().set(key,url);

        model.addAttribute("longUrl",url);
        model.addAttribute("shortUrl", realm+key);
        return "long_url";
    }
    @RequestMapping("/{url}")
    public String restoreUrl(@PathVariable("url") String url){
        String restoreUrl = this.redisTemplate.opsForValue().get(url);

        if(restoreUrl != null && !"".equals(restoreUrl)){
            return "redirect:"+restoreUrl;
        }else{
            return "redirect:http://www.cqyyss.club/index";
//            return  "forward:/404.html";    //如果要访问本地html，@RequestMapping("/{url}")前面必须加一层路径/aa/{url}
        }
    }

}
