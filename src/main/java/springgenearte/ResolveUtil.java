package springgenearte;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
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

    public static void read(BufferedReader bufferedReader, String entityCapitalName,  List<Param> params) throws IOException {
        String lineText = null;
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
            }

        }

        if (showResultLog) {
            for (Param param : params) {
                log.info("param:{}", param);
            }
            log.info("entityCapitalName:{}", entityCapitalName);
        }

    }
}
