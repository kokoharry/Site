import java.util.List;

/**
 * ${class.comment} service 层接口
 */
public interface ${class.classname}Service {

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

    /**
     * 通过主键获取${class.comment}
     * @param id
     * @return
     */
    ${class.classname} get${class.classname}ById(long id);

    /**
     * 通过条件获取${class.comment}
     * @param param
     * @return
     */
    List<${class.classname}> query${class.classname}ByConditions(Map<String,Object> param);

}