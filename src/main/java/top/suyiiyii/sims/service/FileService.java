package top.suyiiyii.sims.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.suyiiyii.sims.utils.S3Client;

import java.io.InputStream;

@Service
public class FileService {
    @Autowired
    private S3Client s3Client;

    public String uploadFile(InputStream input,String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf("."));
        return s3Client.uploadFile(input, extension);
    }
}
