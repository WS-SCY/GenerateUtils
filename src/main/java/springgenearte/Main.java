package springgenearte;

import java.io.*;

/**
 * @Author: SunCY
 * @Description: TODO
 * @DateTime: 2022/3/31 14:38
 **/
public class Main {

    private static String encoding = "UTF-8";
    private static String parentPath = "D:\\codes\\JavaGenerateUtils\\src\\main\\java\\springgenearte\\input";
    private static String inputFile = "input.txt";

    public static void main(String[] args) {
        try{
            File file = new File(parentPath,inputFile);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                SpringGenerateUtil.generate(bufferedReader);
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
