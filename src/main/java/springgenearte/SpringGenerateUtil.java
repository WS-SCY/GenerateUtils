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

    //第一个 entityCapitalName，第二个 entityName，第三个 EntityDescription
    private static List<String> entityInfos = new LinkedList<String>();
    private static List<Param> params = new LinkedList<Param>();


    public static void generate() throws IOException {
        log.info("生成代码开始");
        read();
        initial();
        generateDTO();
        generateVO();
        generateAssembler();
        generateRepository();
        generateController();
        generateService();
        generateServiceImpl();
        log.info("生成代码结束");
    }

    private static void initial() {
        log.info("初始化");
        GenerateUtil.initial(entityInfos, params);
    }

    private static void generateServiceImpl() {
        log.info("生成ServiceImpl");
        GenerateUtil.generateServiceImpl();
    }

    private static void generateService() {
        log.info("生成Service");
        GenerateUtil.generateService();
    }

    private static void generateController() {
        log.info("生成Controller");
        GenerateUtil.generateController();
    }

    private static void generateRepository() {
        log.info("生成Repository");
        GenerateUtil.generateRepository();
    }

    private static void generateAssembler() {
        log.info("生成Assembler");
        GenerateUtil.generateAssembler();
    }

    private static void generateVO() {
        log.info("生成VO");
        GenerateUtil.generateVO();
    }


    public static void read() throws IOException {
        log.info("解析文本信息");
        ResolveUtil.read(params,entityInfos);
    }


    private static void generateDTO() {
        log.info("生成DTO");
        try{
            GenerateUtil.generateDTO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
