package com.pengling.beauty.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Locale;
import java.util.UUID;

@Component
public class uploadUtil {
    public static final String ALI_DOMAIN="https://beauty-pengling.oss-cn-zhangjiakou.aliyuncs.com/";
    public static String uploadImage(MultipartFile file) throws IOException{
        String originalFilename = file.getOriginalFilename();
        String ext ="."+ FilenameUtils.getExtension(originalFilename);
        String uuid = UUID.randomUUID().toString().replace("-","");
        String fileName =uuid+ext;
        String endpoint ="https://oss-cn-zhangjiakou.aliyuncs.com";
        String accessKeyId="LTAI5tK2CfYnpahWQzdSi37y";
        String accessKeySecret="lFI5dgxt1jviLDxFt7sqYcf02y7djw";
        OSS ossClient = new OSSClientBuilder().build(endpoint,accessKeyId,accessKeySecret);
        ossClient.putObject("beauty-pengling",fileName,file.getInputStream());
        ossClient.shutdown();
        return ALI_DOMAIN+fileName;
    }

}
