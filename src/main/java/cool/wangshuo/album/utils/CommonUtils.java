package cool.wangshuo.album.utils;

import io.netty.handler.codec.http.HttpResponse;
import net.coobird.thumbnailator.Thumbnails;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
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


    /**
     * 展示图片 设置响应流
     * 1. 设置响应的 MIME（文件类型）
     * @param response http 响应
     * @param imagePath 图片的路径
     * @param scale 图片的缩放情况
     * @throws IOException
     */
    public static void showPhoto(HttpServletResponse response, String imagePath, Float scale) throws IOException {

        File imageFile = new File(imagePath);

        // 根据文件名称获取文件的MIME类型，用于指定ContentType
        String mimeType = CommonUtils.getContentType(imagePath);
        response.setContentType(mimeType);

        // 设定Content-disposition属性值，inline 表示在浏览器页面打开显示，attachment 表示以附件的形式下载
        response.setHeader("Content-disposition", "inline;filename="+ URLEncoder.encode(imagePath , "UTF-8"));
        //如果文件名存在中文，要使用 URLEncoder 进行重新编码

        FileInputStream fis = new FileInputStream(imagePath);

        byte[] in = new byte[2048];

        if (scale!=null){
            ByteArrayOutputStream out1 = new ByteArrayOutputStream();
            Thumbnails.of(imageFile)
                    .scale(scale)
                    .toOutputStream(out1);
            response.getOutputStream().write(out1.toByteArray());
            return;
        }

        while (fis.read(in, 0, in.length) != -1) {
            response.getOutputStream().write(in);
        }
        //向响应的输出流中写入内容
        response.getOutputStream().flush(); //送出
    }

}
