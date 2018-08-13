import java.util.List;

/**
 * ${class.comment} service 层接口
 */
public interface I${class.classname}Service {

    /**
     * 分页获取${class.comment}
     * @param fristNum
     * @param limitNum
     * @param  ${class.classname?uncap_first}
     * @return
     */
    List<${class.classname}> get${class.classname}ForPage(int fristNum, int limitNum, ${class.classname} ${class.classname?uncap_first});

    /**
     * 获取所有${class.comment}
     * @param  ${class.classname?uncap_first}
     * @return
     */
    List<${class.classname}> get${class.classname}ForAll(${class.classname} ${class.classname?uncap_first});
    /**
     *计算${class.comment}数量
     * @return
     */
    int get${class.classname}Count(${class.classname} ${class.classname?uncap_first});

    /**
     * 添加${class.comment}
     * @param ${class.classname?uncap_first}
     * @return
     */
    ${class.classname} add${class.classname}(${class.classname} ${class.classname?uncap_first});

    /**
     * 删除${class.comment}
     * @param id
     * @return
     */
    int delete${class.classname}(long id);

    /**
     * 编辑${class.comment}
     * @param ${class.classname?uncap_first}
     * @return
     */
    int edit${class.classname}(${class.classname} ${class.classname?uncap_first});

}