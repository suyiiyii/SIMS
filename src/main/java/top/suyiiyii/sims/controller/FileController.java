package top.suyiiyii.sims.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.suyiiyii.sims.common.AuthAccess;
import top.suyiiyii.sims.service.FileService;

import java.io.IOException;
import java.io.InputStream;

@Slf4j
@RestController
public class FileController {
    @Autowired
    FileService fileService;

    @AuthAccess(allowRoles = {"user"})
    @Operation(summary = "上传文件", description = "使用form-data格式\nfile：文件\nfilename： 文件名\n返回可访问的路径")
    @PostMapping("/upload")
    public String uploadFile(
            @Parameter String filename,
            @RequestBody(content = @Content(mediaType = "multipart/form-data",
                    schema = @Schema(type = "string", format = "binary"))) MultipartFile file) {
        try (InputStream in = file.getInputStream()) {
            log.info("文件上传，文件名：{}，描述：{}", file.getOriginalFilename(), filename);
            return fileService.uploadFile(in, filename);
        } catch (IOException e) {
            log.warn("文件上传失败", e);
            throw new RuntimeException(e);
        }
    }
}
