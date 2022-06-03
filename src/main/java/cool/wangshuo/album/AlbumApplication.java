package cool.wangshuo.album;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
public class AlbumApplication {

    public static String imagePath;
    public static String imagePace;

    @Value("${image.path}")
    public void setImagePath(String imagePath) {
        AlbumApplication.imagePath = imagePath;
    }

    @Value("${image.face}")
    public void setImagePace(String imagePace) {
        AlbumApplication.imagePace = imagePace;
    }


    public static void main(String[] args) {
        SpringApplication.run(AlbumApplication.class, args);
    }
}
