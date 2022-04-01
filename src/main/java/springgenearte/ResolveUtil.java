package springgenearte;


import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.List;

/**
 * @Author: SunCY
 * @Description: 解析文本
 * @DateTime: 2022/3/31 17:45
 **/

@Slf4j
public class ResolveUtil {

    /**
     * 解析过程日志
     */
    private static boolean showResolveLog = false;

    /**
     * 解析结果日志
     */
    private static boolean showResultLog = true;

    /**
     * 输入文件日志
     */
    private static boolean showInputLog = false;


    /**
     * 从Entity中读取文本信息，解析出参数列表，返回
     *
     * @param params      带回参数列表
     * @param entityInfos 带回Entity信息列表
     * @return EntityName
     * @throws IOException
     */
    public static void read(List<Param> params, List<String> entityInfos) throws IOException {
        InputStreamReader read = null;
        BufferedReader bufferedReader = null;
        String lineText = null, entityCapitalName = null, entityName = null, entityDescription = null;
        File file = new File(Constants.parentInputPath, Constants.inputFile);
        if (file.isFile() && file.exists()) {
            read = new InputStreamReader(new FileInputStream(file), Constants.encoding);
            bufferedReader = new BufferedReader(read);
        } else {
            System.out.println("找不到指定的文件");
        }

        String lastDescription = null, lastProperty = null;
        while ((lineText = bufferedReader.readLine()) != null) {
            String lineTextTrimed = lineText.trim();
            if (showInputLog) {
                System.out.println(lineTextTrimed);
            }
            if (JudgeUtil.isDescription(lineTextTrimed)) {
                lastDescription = lineTextTrimed.substring(1).trim();
                if (showResolveLog) {
                    log.info("isDescription：{}", lastDescription);
                }
            } else if (JudgeUtil.isPrivateProperty(lineTextTrimed)) {
                lastProperty = lineTextTrimed;
                params.add(new Param(lastDescription, lastProperty));
                lastDescription = null;
                if (showResolveLog) {
                    log.info("isPrivateProperty:{}", lastProperty);
                }
            } else if (JudgeUtil.isEntityName(lineTextTrimed)) {
                entityCapitalName = lineTextTrimed.substring("public class ".length(), lineTextTrimed.length() - "Entity {".length());
                if (showResolveLog) {
                    log.info("isEntityName：{}", entityCapitalName);
                }
            } else if (JudgeUtil.isEntityDescription(lineTextTrimed)) {
                entityDescription = lineTextTrimed.substring("* @Description ".length()).trim();
                if (showResolveLog) {
                    log.info("isEntityName：{}", entityCapitalName);
                }
            }
        }

        if (showResultLog) {
            for (Param param : params) {
                log.info("param:{}", param);
            }
            log.info("entityCapitalName:{}", entityCapitalName);
        }
        entityName = entityCapitalName.toLowerCase().substring(0, 1) + entityCapitalName.substring(1, entityCapitalName.length());

        //第一个 entityCapitalName，第二个 entityName，第三个 EntityDescription
        entityInfos.add(entityCapitalName);
        entityInfos.add(entityName);
        entityInfos.add(entityDescription);

        read.close();
    }
}
