package com.itmasir.file;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

/**
 * 
 * @ClassName: FileUtils
 * @Description: 文件工具类 可以直接继承 org.apache.commons.io.FileUtils
 * @author shinian
 * @date 2017年7月21日 下午2:31:18
 * 
 */
public class FileUtils {
    /**
     * 一KB
     */
    public static final long ONE_KB = 1024;

    /**
     * 一M
     */
    public static final long ONE_MB = ONE_KB * ONE_KB;

    /**
     * copy文件的bufferSize
     */
    private static final long FILE_COPY_BUFFER_SIZE = ONE_MB * 30;

    /**
     * 
     * @Title: createFile
     * @Description:创建文件
     * @param path
     *            目录
     * @param fileName
     *            文件名
     * @return File
     * @throws
     */
    public static File createFile(String path, String fileName) {

        if (path == null) {
            throw new NullPointerException("目录不能为空!");
        }
        if (fileName == null) {
            throw new NullPointerException("文件名不能为空!");
        }

        File file = new File(path, fileName);
        if (!file.getParentFile().exists()) {
            if (!file.getParentFile().mkdir()) {
                throw new RuntimeException("文件夹创建失败");
            }
        }
        if (file.exists()) {
            throw new RuntimeException("文件已经存在");
        }
        try {
            if (file.createNewFile()) {
                return file;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 
     * @Title: copyFile
     * @Description:
     * @param srcFile
     *            源文件
     * @param destFile
     *            目标文件
     * @return void
     * @throws
     */
    public static void copyFile(File srcFile, File destFile) throws IOException {
        if (srcFile == null) {
            throw new NullPointerException("源文件不能为空");
        }
        if (destFile == null) {
            throw new NullPointerException("目标文件不能为空");
        }
        if (srcFile.exists() == false) {
            throw new FileNotFoundException("源文件 '" + srcFile + "' 不存在");
        }
        if (srcFile.isDirectory()) {
            throw new IOException("源文件 '" + srcFile + "' 是一个目录");
        }
        if (srcFile.getCanonicalPath().equals(destFile.getCanonicalPath())) {
            throw new IOException("源文件 '" + srcFile + "' 和目标文件 '" + destFile
                    + "' 是同一个目录");
        }
        File parentFile = destFile.getParentFile();
        if (parentFile != null) {
            if (!parentFile.mkdirs() && !parentFile.isDirectory()) {
                throw new IOException(parentFile + "'目录不能创建");
            }
        }
        if (destFile.exists() && destFile.canWrite() == false) {
            throw new IOException(destFile + "' 是只读的");
        }
        doCopyFile(srcFile, destFile);
    }

    private static void doCopyFile(File srcFile, File destFile)
            throws IOException {
        if (destFile.exists() && destFile.isDirectory()) {
            throw new IOException(destFile + "' 是一个目录");
        }

        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel input = null;
        FileChannel output = null;
        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(destFile);
            input = fis.getChannel();
            output = fos.getChannel();
            long size = input.size();
            long pos = 0;
            long count = 0;
            while (pos < size) {
                count = size - pos > FILE_COPY_BUFFER_SIZE ? FILE_COPY_BUFFER_SIZE
                        : size - pos;
                pos += output.transferFrom(input, pos, count);
            }
        } finally {
            closeQuietly(output);
            closeQuietly(fos);
            closeQuietly(input);
            closeQuietly(fis);
        }

        if (srcFile.length() != destFile.length()) {
            throw new IOException("拷贝文件'" + srcFile + "' 到 '" + destFile
                    + "'失败 ");
        }
    }

    private static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
        }
    }

    private FileUtils() {
    }

}