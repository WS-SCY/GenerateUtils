package springgenearte;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: SunCY
 * @Description: 控制类：自动生成Service、Controller、Repository、DTO、VO、Param、Assembler代码
 * @DateTime: 2022/3/31 14:49
 **/

@Slf4j
public class SpringGenerateUtil {
    private static String entityCapitalName;
    private static List<Param> params = new LinkedList<Param>();


    public static void read(BufferedReader bufferedReader) throws IOException {

        ResolveUtil.read(bufferedReader,entityCapitalName,params);

    }

    public static void generate(BufferedReader bufferedReader) throws IOException {
        log.info("生成代码开始");
        read(bufferedReader);
        log.info("生成代码结束");
    }
}
