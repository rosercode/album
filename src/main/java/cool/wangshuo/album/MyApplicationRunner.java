package cool.wangshuo.album;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * 在 SpringBoot 项目启动后执行的代码。主要负责的功能：判断配置文件中的路径是否存在，不存在就创建 <br>
 *
 *
 * @author wangsh
 * @date 2022/6/11 14:39
 */

@Component
@Slf4j
public class MyApplicationRunner implements ApplicationRunner {

    public MyApplicationRunner() {
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        File imagePaceFile = new File(AlbumApplication.imagePace);
        File imagePathFile = new File(AlbumApplication.imagePath);

        if (!imagePathFile.exists()){
            log.info("路径: " + imagePaceFile.getAbsolutePath()+" 不存在，创建目录");
            imagePathFile.mkdirs();
        }

        if (!imagePathFile.exists()){
            log.info("路径: " + imagePathFile.getAbsolutePath()+" 不存在，创建目录");
            imagePathFile.mkdirs();
        }
    }
}
