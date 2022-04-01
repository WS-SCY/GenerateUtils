package springgenearte;


import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;

/**
 * @Author: SunCY
 * @Description: 输入到文件
 * @DateTime: 2022/4/1 9:32
 **/

@Slf4j
public class GenerateUtil {

    private static String entityCapitalName;
    private static String entityName;
    private static String entityDescription;
    private static List<Param> params;

    public static void generateServiceImpl( ) {
    }

    public static void generateService( ) {
    }

    public static void generateController( ) {
    }

    public static void generateRepository( ) {
    }

    public static void generateAssembler( ) {
    }

    public static void generateVO( ) {
    }

    public static void generateDTO( ) throws IOException {
        File file = new File(Constants.parentOutputPath,Constants.dtoFile);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("@Data\n");
        fileWriter.write("@ApiModel(description = \""+entityDescription+"\")\n");
        fileWriter.write("public class "+entityCapitalName+"DTO {\n");
        for(Param param:params){
            fileWriter.write("@ApiModelProperty(value = \""+param.getDescription()+"\")\n");
            fileWriter.write(param.getProperty()+"\n\n");
        }
        fileWriter.write("}\n");
        fileWriter.close();
    }

    public static void initial(List<String> entityInfos, List<Param> paramList) {
        //第一个 entityCapitalName，第二个 entityName，第三个 EntityDescription
        entityCapitalName = entityInfos.get(0);
        entityName = entityInfos.get(1);
        entityDescription = entityInfos.get(2);
        params = paramList;
    }
}
