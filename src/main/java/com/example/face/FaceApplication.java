package com.example.face;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;

@SpringBootApplication
public class FaceApplication implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(FaceApplication.class);

    @Autowired
    private DataSource dataSource;

    public static void main(String[] args) {

        SpringApplication.run(FaceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception{
        showDataSource();
    }

    private void showDataSource() throws Exception{
        logger.info(dataSource.toString());
        Connection connection = dataSource.getConnection();
        logger.info(connection.toString());
        connection.close();
    }


}
