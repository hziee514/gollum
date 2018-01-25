package org.gollum.simple;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author wurenhai
 * @date 2018/1/23
 */
@SpringBootApplication
@ServletComponentScan
public class NoteMvcApp {

    public static void main(String[] args) {
        SpringApplication.run(NoteMvcApp.class, args);
    }

}
