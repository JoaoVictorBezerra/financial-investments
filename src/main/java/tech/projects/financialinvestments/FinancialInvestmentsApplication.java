package tech.projects.financialinvestments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class FinancialInvestmentsApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinancialInvestmentsApplication.class, args);
    }

}
