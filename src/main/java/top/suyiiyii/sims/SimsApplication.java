package top.suyiiyii.sims;

import com.tangzc.mpe.autotable.EnableAutoTable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@EnableAutoTable
@SpringBootApplication
public class SimsApplication {
    public static void main(String[] args) {
        SpringApplication.run(SimsApplication.class, args);
    }

}
