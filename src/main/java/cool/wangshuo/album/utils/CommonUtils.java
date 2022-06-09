package cool.wangshuo.album.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author wangsh
 * @date 2022/4/29 23:54
 */

public class CommonUtils {

    public static String uuid() {
        int hashCode = java.util.UUID.randomUUID().toString().hashCode();
        if (hashCode < 0) {
            hashCode = -hashCode;
        }
        // 0 代表前面补充0
        // 10 代表长度为10
        // d 代表参数为正数型
        String format = String.format("%010d", hashCode).substring(0, 10);
        return format;
    }

    /**
     * 获取对应文件的 MIME 值
     * @date 2022-05-06 13:56:12
     * @param filename
     * @return
     */
    public static String getContentType(String filename){
        String type = null;
        Path path = Paths.get(filename);
        try {
            type = Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return type;
    }

}
