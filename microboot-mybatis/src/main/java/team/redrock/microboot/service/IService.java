package team.redrock.microboot.service;

import org.springframework.stereotype.Service;
import team.redrock.microboot.vo.ShortUrl;


public interface IService {

    /*
    /发号器
     */
    public String findNum();

    /*
   /如果redis里没有号码，则插入一个
    */
    public void insertNum();
    /*
    /转换为64字节
     */

    /*
    /插入redis数据库并设置缓存时间
     */
    public void insertKey(String key, String shortUrl);

    /*
    /通过Key查询短链接
     */
    public String findValueByKey(String key);

}
