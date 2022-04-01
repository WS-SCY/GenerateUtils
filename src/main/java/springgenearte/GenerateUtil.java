package springgenearte;


import lombok.RequiredArgsConstructor;
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

    public static void generateServiceImpl() throws IOException {


        File file = new File(Constants.parentOutputPath, Constants.serviceImplFile);
        FileWriter fileWriter = new FileWriter(file);

        fileWriter.write("@Slf4j\n");
        fileWriter.write("@Service\n");
        fileWriter.write("public class " + entityCapitalName + "ServiceImpl  implements I" + entityCapitalName + "Service {\n\n");


        fileWriter.write("@Autowired\n");
        fileWriter.write("private " + entityCapitalName + "Repository " + entityName + "Repository;\n\n");
        fileWriter.write("@Autowired\n");
        fileWriter.write("private " + entityCapitalName + "Assembler " + entityName + "Assembler;\n\n");

        fileWriter.write("@Override\n");
        fileWriter.write("public void add(" + entityCapitalName + "DTO dto) {\n");
        fileWriter.write(entityCapitalName + "Entity entity =  " + entityName + "Assembler.D2E(dto);\n");
        fileWriter.write("if(entity.getId()!=null){\n");
        fileWriter.write("entity.setId(null);\n");
        fileWriter.write("log.info(\"id自动设置为null\");\n");
        fileWriter.write("}\n");
        fileWriter.write(entityName + "Repository.save(entity);\n");
        fileWriter.write("}\n\n");


        fileWriter.write("@Override\n");
        fileWriter.write("public void deleteById(Long id) {\n");
        fileWriter.write(entityName + "Repository.deleteById(id);\n");
        fileWriter.write("}\n\n");

        fileWriter.write("@Override\n");
        fileWriter.write("public void update(" + entityCapitalName + "DTO dto) {\n");
        fileWriter.write(entityCapitalName + "Entity entity = " + entityName + "Assembler.D2E(dto);\n");
        fileWriter.write("if(entity.getId()!=null){\n");
        fileWriter.write("throw new PbServiceException(\"更新失败，id不能为null\")\n");
        fileWriter.write("}\n");
        fileWriter.write(entityName + "Repository.save(entity);\n");
        fileWriter.write("}\n\n");

        fileWriter.write("@Override\n");
        fileWriter.write("public " + entityCapitalName + "VO getById(Long id) {\n");
        fileWriter.write(entityCapitalName + "Entity entity = " + entityName + "Repository.getById(id);\n");
        fileWriter.write(entityCapitalName + "VO vo = " + entityName + "Assembler.E2V(entity);\n");
        fileWriter.write("return vo;\n");
        fileWriter.write("}\n\n");


        fileWriter.write("}\n");
        fileWriter.close();

    }

    public static void generateService() throws IOException {

        File file = new File(Constants.parentOutputPath, Constants.serviceFile);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("public interface I" + entityCapitalName + "Service {\n\n");
        fileWriter.write("void add(" + entityCapitalName + "DTO dto);\n\n");
        fileWriter.write("void deleteById(Long id);\n\n");
        fileWriter.write("void update(" + entityCapitalName + "DTO dto);\n\n");
        fileWriter.write(entityCapitalName + "VO getById(Long id);\n\n");
        fileWriter.write("}\n");
        fileWriter.close();
    }

    public static void generateController() throws IOException {
        File file = new File(Constants.parentOutputPath, Constants.controllerFile);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("import com.pb.base.core.api.Message;\n");
        fileWriter.write("@Slf4j\n");
        fileWriter.write("@RestController\n");
        fileWriter.write("@RequestMapping(\""+entityName+"\")\n");
        fileWriter.write("@Api(tags = \""+entityDescription+"\")\n");
        fileWriter.write("@RequiredArgsConstructor\n");
        fileWriter.write("public class "+entityCapitalName+"Controller {\n\n");
        fileWriter.write("@Autowired\n");
        fileWriter.write("private I"+entityCapitalName+"Service "+entityName+"Service;"+"\n\n");

        fileWriter.write("@PostMapping(\"/add\")\n");
        fileWriter.write("@ApiOperation(\"新增\")\n");
        fileWriter.write("public Message add(@RequestBody "+entityCapitalName+"DTO dto){\n");
        fileWriter.write(entityName+"Service.add(dto);\n");
        fileWriter.write("return ResponseUtils.successResponse();\n");
        fileWriter.write("}\n\n");

        fileWriter.write("@PostMapping(\"/deleteById\")\n");
        fileWriter.write("@ApiOperation(\"根据id删除\")\n");
        fileWriter.write("public Message deleteById(Long id){\n");
        fileWriter.write(entityName+"Service.deleteById(id);\n");
        fileWriter.write("return ResponseUtils.successResponse();\n");
        fileWriter.write("}\n\n");

        fileWriter.write("@PostMapping(\"/update\")\n");
        fileWriter.write("@ApiOperation(\"更新\")\n");
        fileWriter.write("public Message update(@RequestBody "+entityCapitalName+"DTO dto){\n");
        fileWriter.write(entityName+"Service.update(dto);\n");
        fileWriter.write("return ResponseUtils.successResponse();\n");
        fileWriter.write("}\n\n");

        fileWriter.write("@PostMapping(\"/getById\")\n");
        fileWriter.write("@ApiOperation(\"根据id查询\")\n");
        fileWriter.write("public Message getById(Long id){\n");
        fileWriter.write(entityCapitalName+"VO vo ="+entityName+"Service.getById(id);\n");
        fileWriter.write("return ResponseUtils.successQueryOneResponse(vo);\n");
        fileWriter.write("}\n\n");

        fileWriter.write("}\n\n");
        fileWriter.close();
//
//
//
//
//            }
//
//            @PostMapping("delete")
//            @ApiModelProperty("删除")
//            public Message delete(Long id){
//                recommendationService.deleteById(id);
//                return ResponseUtils.successResponse();
//            }
//
//            @PostMapping("update")
//            @ApiModelProperty("修改")
//            public Message update(@RequestBody RecommendationDTO dto){
//                recommendationService.update(dto);
//                return ResponseUtils.successResponse();
//            }
//
//            @PostMapping("get")
//            @ApiModelProperty("根据管理员id查询")
//            public Message retrieve(){
//                RecommendationVO recommendationVO = recommendationService.getByAdminID();
//                return ResponseUtils.successQueryOneResponse(recommendationVO);
//            }
//
//        }

    }

    public static void generateRepository() throws IOException {
        File file = new File(Constants.parentOutputPath, Constants.repositoryFile);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("public interface " + entityCapitalName + "Repository extends JpaRepository<" + entityCapitalName + "Entity, Long>, " + entityCapitalName + "RepositoryCustom {\n\n");
        fileWriter.write("}\n");
        fileWriter.close();
    }

    public static void generateAssembler() throws IOException {
        File file = new File(Constants.parentOutputPath, Constants.assemblerFile);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("@Mapper(componentModel = \"spring\")\n");
        fileWriter.write("public interface " + entityCapitalName + "Assembler {\n");
        fileWriter.write("\n" + entityCapitalName + "Entity D2E (" + entityCapitalName + "DTO dto);\n\n");
        fileWriter.write(entityCapitalName + "VO E2V (" + entityCapitalName + "Entity entity);\n");
        fileWriter.write("\n}\n");
        fileWriter.close();
    }

    public static void generateVO() throws IOException {
        File file = new File(Constants.parentOutputPath, Constants.voFile);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("@Data\n");
        fileWriter.write("@ApiModel(description = \"" + entityDescription + "\")\n");
        fileWriter.write("public class " + entityCapitalName + "VO {\n");
        for (Param param : params) {
            fileWriter.write("@ApiModelProperty(value = \"" + param.getDescription() + "\")\n");
            fileWriter.write(param.getProperty() + "\n\n");
        }
        fileWriter.write("}\n");
        fileWriter.close();
    }

    public static void generateDTO() throws IOException {
        File file = new File(Constants.parentOutputPath, Constants.dtoFile);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("@Data\n");
        fileWriter.write("@ApiModel(description = \"" + entityDescription + "\")\n");
        fileWriter.write("public class " + entityCapitalName + "DTO {\n");
        for (Param param : params) {
            fileWriter.write("@ApiModelProperty(value = \"" + param.getDescription() + "\")\n");
            fileWriter.write(param.getProperty() + "\n\n");
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

    public static void generateCustom() throws IOException {
        File file = new File(Constants.parentOutputPath, Constants.customFile);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("public interface " + entityCapitalName + "RepositoryCustom {\n\n}");
        fileWriter.close();
    }

    public static void generateCustomImpl() throws IOException {

        File file = new File(Constants.parentOutputPath, Constants.customImplFile);
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("import javax.persistence.EntityManager;\n");
        fileWriter.write("public class " + entityCapitalName + "RepositoryCustomImpl implements " + entityCapitalName + "RepositoryCustom {\n\n");
        fileWriter.write("@Autowired\n");
        fileWriter.write("private EntityManager em;");
        fileWriter.write("\n\n}");
        fileWriter.close();
    }
}
