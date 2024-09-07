package top.suyiiyii.sims.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.suyiiyii.sims.utils.S3Client;

@Configuration
public class S3Config {
    @Bean
    public static S3Client Config(@Value("${S3.ENDPOINT}") String endpoint,
                                  @Value("${S3.ACCESS_KEY}") String accessKey,
                                  @Value("${S3.SECRET_KEY}") String secretKey,
                                  @Value("${S3.BUCKET}") String bucket) {
        return new S3Client(endpoint, accessKey, secretKey, bucket);
    }
}
