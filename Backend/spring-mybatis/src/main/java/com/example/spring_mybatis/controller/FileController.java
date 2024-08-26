package com.example.spring_mybatis.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import com.example.spring_mybatis.utils.R;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

@CrossOrigin
@RestController
@RequestMapping("/file")
public class FileController {
    @Value("${files.upload.path}")
    private String fileUploadPath;
    @Value("${files.upload.fileMapper}")
    private String fileMapper;   // 文件映射路径
    @Autowired
    private ServletContext servletContext;

    /**
     * 文件上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public R upload(@RequestParam MultipartFile file) throws IOException {
        // 获取项目根目录路径
        String rootPath = System.getProperty("user.dir");
        // 生成绝对路径
        String absolutePath = rootPath + File.separator + fileUploadPath;

        // 获取文件名
        String originalFilename = file.getOriginalFilename();
        // 获取文件的后缀（类型）
        String fileType = FileUtil.extName(originalFilename);

        // 将文件先存储到磁盘
        File uploadParentFile = new File(absolutePath);
        // 判断存储文件的目录是否存在，不存在则创建
        if (!uploadParentFile.exists()){
            uploadParentFile.mkdirs();
        }

        // 定义唯一标识码，重新命名文件
        String uuid = IdUtil.fastSimpleUUID();
        String fileName = uuid + StrUtil.DOT + fileType;
        // 文件路径与下载接口路径一样
        String url = fileMapper + fileName;

        // 将文件写入磁盘
        File uploadFile = new File(absolutePath + File.separator + fileName);
        file.transferTo(uploadFile);

        return R.success(url);
    }

    @GetMapping("/download/{fileUUID}")
    public void downLoad(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        // 获取项目根目录路径
        String rootPath = System.getProperty("user.dir");
        // 生成绝对路径
        String absolutePath = rootPath + File.separator + fileUploadPath;

        // 根据文件标识码获取文件
        File file = new File(absolutePath + File.separator + fileUUID);

        // 设置输出流对象
        ServletOutputStream os = response.getOutputStream();
        response.addHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(fileUUID, "UTF-8"));
        response.setContentType("application/octet-stream");

        // 读取字节流
        os.write(FileUtil.readBytes(file));
        os.flush();
        os.close();
    }

    @GetMapping("/profile/{fileUUID}")
    public void viewImage(@PathVariable String fileUUID, HttpServletResponse response) throws IOException {
        // 获取项目根目录路径
        String rootPath = System.getProperty("user.dir");
        // 生成绝对路径
        String absolutePath = rootPath + File.separator + fileUploadPath;

        // 生成文件路径
        File file = new File(absolutePath + File.separator + fileUUID);

        if (!file.exists()) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 获取文件的 MIME 类型
        String mimeType = servletContext.getMimeType(file.getAbsolutePath());
        if (mimeType == null) {
            mimeType = "application/octet-stream";
        }

        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", "inline; filename=\"" + URLEncoder.encode(fileUUID, "UTF-8") + "\"");

        // 读取文件并写入响应
        try (ServletOutputStream os = response.getOutputStream()) {
            os.write(FileUtil.readBytes(file));
            os.flush();
        }
    }
}
