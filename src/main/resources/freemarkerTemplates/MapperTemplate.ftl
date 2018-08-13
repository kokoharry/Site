import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * ${class.comment} dao 层接口 mybatis mapper映射接口
 */
public interface ${class.classname}Mapper extends BaseMapper<${class.classname}>{

    /**
     * 根据唯一主键查询${class.comment}
     * @param id
     * @return
     */
    ${class.classname} selectByPrimaryKey(long id);

    /**
     * 根据条件查询${class.comment}
     * @param condition
     * @return
     */
    List<${class.classname}> selectByCondition(${class.classname} condition);

    /**
     * 根据条件分页查询${class.comment}
     * @param condition
     * @param firstNum 记录开始数字 从0开始
     * @param limitNum 每页限制条数
     * @return
     */
    List<${class.classname}> selectByConditionForPage(${class.classname} condition,@Param("firstNum")int firstNum,@Param("limitNum")int limitNum);

    /**
     * 根据条件查询${class.comment}数量
     * @param condition
     * @return
     */
    int selectCountByCondition(${class.classname} condition);

    /**
     * 根据唯一主键删除${class.comment}
     * @param id
     * @return
     */
    int deleteByPrimaryKey(@Param("id")Long id);

    /**
     * 添加${class.comment}
     * @param record
     * @return
     */
    int insert(${class.classname} record);

    /**
     * 根据条件修改${class.comment}
     * @param record
     * @return
     */
    int updateByCondition(${class.classname} record);

}