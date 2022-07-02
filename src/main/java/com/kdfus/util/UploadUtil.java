package com.kdfus.util;

/**
 * @author Cra2iTeT
 * @date 2022/6/30 14:04
 */

import com.kdfus.common.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 文件上传工具类
 */
@Slf4j
public class uploadUtil {
    public static String uploadFile(MultipartFile file, String parentPath) {
        String fileName = file.getOriginalFilename();
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        //生成文件名称通用方法
        Random random = new Random();
        StringBuilder tempName = new StringBuilder();
        tempName.append(NumberUtil.genNo()).append(suffixName);
        String newFileName = tempName.toString();
        File fileDirectory = new File(parentPath + "\\" + TimeUtil.getTimeNow("yyyyMMdd"));
        //创建文件
        File destFile = new File(parentPath + newFileName);
        try {
            if (!fileDirectory.exists()) {
                if (!fileDirectory.mkdir()) {
                    throw new IOException("文件夹创建失败,路径为：" + fileDirectory);
                }
            }
            file.transferTo(destFile);
            log.info("上传的文件夹 => " + parentPath + newFileName);
            return parentPath + newFileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
