package cool.wangshuo.album;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class AlbumApplication {



    public static void main(String[] args) {
        SpringApplication.run(AlbumApplication.class, args);
    }
}
