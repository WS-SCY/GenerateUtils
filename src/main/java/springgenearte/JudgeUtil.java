package springgenearte;

/**
 * @Author: SunCY
 * @Description: TODO
 * @DateTime: 2022/3/31 16:55
 **/
public class JudgeUtil {
    public static boolean isDescription(String lineTextTrimed) {
        return lineTextTrimed.length()>2 && lineTextTrimed.charAt(0)=='*' && lineTextTrimed.charAt(1)!='/'&& lineTextTrimed.charAt(2)!='@'&& lineTextTrimed.charAt(1)!='*';
    }

    public static boolean isPrivateProperty(String lineTextTrimed) {
        return lineTextTrimed.startsWith("private");
    }

    public static boolean isEntityName(String lineTextTrimed) {
        return lineTextTrimed.startsWith("public class");
    }

    public static boolean isEntityDescription(String lineTextTrimed) {
        return lineTextTrimed.startsWith("* @Description");
    }
}
