package com.zhihu.openbox;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * 工具类
 *
 * @author shilm 2018-3-30
 */
public class Utils {

    private Utils(){};

    private static final char[] PASSWORD_SEED = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j',
    'i', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
    '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
    'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X',
    'Y', 'Z'};

    /**
     * 随机生成固定长度的密码
     * @param number
     * @return
     */
    public static String generatePsw(final int number) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < number; i++) {
            int index = random.nextInt(PASSWORD_SEED.length);
            sb.append(PASSWORD_SEED[index]);
        }
        return sb.toString();
    }

    /**
     * 创建文件
     * @param name
     * @return
     * @throws IOException
     */
    public static File createFile(String name) throws IOException {
        File file = new File(name);
        file.createNewFile();
        return file;
    }

    /**
     *
     * @param phoneNo
     * @param password
     * @param name
     * @return
     * @throws IOException
     */
    public static boolean appendFileContents(String phoneNo, String password, String name) throws IOException {

        File file = new File(name);
        if (!file.exists()) {
            createFile(name);
        }
        OutputStreamWriter writer = new FileWriter(file, true);
        writer.append(phoneNo + "\t" + password + "\r\n");
        writer.flush();
        writer.close();
        return true;
    }

    public static boolean appendFileLine(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            createFile(fileName);
        }
        OutputStreamWriter writer = new FileWriter(file, true);
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd mm:dd SSS");
        writer.append("###### [ " +format.format(new Date()) + " ]###### \r\n");
        writer.flush();
        writer.close();
        return true;
    }
}
