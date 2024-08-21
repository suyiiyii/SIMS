package top.suyiiyii.sims;

import com.tangzc.autotable.springboot.EnableAutoTable;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAutoTable
@SpringBootApplication
@MapperScan("top.suyiiyii.sims.mapper")
public class SimsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimsApplication.class, args);
    }

}
