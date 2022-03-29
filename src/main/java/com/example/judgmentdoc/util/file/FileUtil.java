package com.example.judgmentdoc.util.file;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

public class FileUtil {

    /**
     * 返回文件流
     *
     * @param response 回应
     * @param pathName 文件路径和名称
     * @throws Exception 异常
     */
    public static void returnStream(HttpServletResponse response, String pathName) throws Exception {
        String suffix = pathName.substring(pathName.lastIndexOf(".") + 1);
        switch (suffix) {
            case "pdf":
                response.setContentType("application/pdf");
                break;
        }

        File file = new File(pathName);
        if (file.exists()) {
            FileInputStream in = new FileInputStream(file);
            OutputStream out = response.getOutputStream();
            byte[] b = new byte[1024 * 5];
            int n;
            while ((n = in.read(b)) != -1) {
                out.write(b, 0, n);
            }
            out.flush();
            in.close();
            out.close();
        }
    }

    /**
     * 删除文件
     *
     * @param path 待删除文件/目录路径和名称
     */
    public static void delFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (file.isFile()) {
            file.delete();
            System.out.println("Delete: " + path);
            return;
        }
        if (file.isDirectory()) {
            for (String temp : file.list()) {
                delFile(file.getAbsolutePath() + File.separator + temp);
            }
            file.delete();
            System.out.println("Delete: " + path);
        }
    }

}