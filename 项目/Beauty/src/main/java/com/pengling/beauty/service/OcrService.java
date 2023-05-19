package com.pengling.beauty.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baidu.aip.ocr.AipOcr;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Service
public class OcrService {
    public static  final  String APP_ID ="32459234";
    public static  final  String API_KEY ="5UQALqrO5C0csjCwqVsHBOOs";
    public static  final  String SECRET_KEY ="epiVBF3DSOtTUGWQFP2HXKz2cGwrFkH9";
    public List<String> ocr(String Path) {
//        初始化一个AipOcr
        AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
//        可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
//        可选：设置代理服务器地址，http和socket二选一，或者均不设置
//        client.setHttpProxy("host","port");
//        client.setSocketProxy();

//        可选：设置log4j日志输出格式，若不设置，则使用默认配置
//        也可以直接通过jvm启动参数设置此环境变量
        System.setProperty("api.log4j.conf", "path/to/your/log4j.properties");
//        调用接口
        String path = Path;
        JSONObject RES = client.basicGeneralUrl(path, new HashMap<String, String>());
        System.out.println("--------------");
        System.out.println(RES.toString(1));
        System.out.println("--------------");
        List<Object> result = RES.getJSONArray("words_result").toList();
        HashMap<Object, Object> hashMap = new HashMap<>();
        List<String> array = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            String res = result.get(i).toString().substring(7);
            int length = res.length();
            res = res.substring(0, length - 1);
            System.out.println(res);
            array.add(res);
        }
        return array;
    }
}
