package com.ybj.crawler.controller;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.PutObjectRequest;
import org.junit.Test;

import java.io.File;

/**
 * @Author File
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class Filetest {

    @Test
    public void test(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-shanghai.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
        String accessKeyId = "LTAI4FbsSeHn6Yje6LE7jNyN";
        String accessKeySecret = "tydx7SSckyEKST12nql4Ww6zkijEhy";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        File file =new File("C:\\Users\\yuanbaojian\\Desktop\\img.png");


// 创建PutObjectRequest对象。
        PutObjectRequest putObjectRequest = new PutObjectRequest("warehouse-ybj", "pictures/abc.jpg", file);

// 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
// ObjectMetadata metadata = new ObjectMetadata();
// metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
// metadata.setObjectAcl(CannedAccessControlList.Private);
// putObjectRequest.setMetadata(metadata);

// 上传文件。
        ossClient.putObject(putObjectRequest);

// 关闭OSSClient。
        ossClient.shutdown();
    }
}
