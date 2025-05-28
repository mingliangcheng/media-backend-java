package com.chen.media.controller;

import com.chen.media.result.Result;
import com.chen.media.service.impl.MinioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {
    @Autowired
    private MinioService minioService;

    @PostMapping("/upload")
    public Result<String> upload(@RequestParam("file") MultipartFile file, @RequestParam("folder") String folder) throws Exception {
        boolean notBlank = StringUtils.isNotBlank(folder);
        if (!notBlank) {
            return Result.fail(500, "folder不能为空");
        }
        String url =  minioService.upload(folder, file);
        return Result.ok(url);
    }
}
