import java.util.List;

/**
 *  dao 层接口 mybatis mapper映射接口
 */
public interface PeopleMapper {

    /**
     * 根据唯一主键查询
     * @param id
     * @return
     */
    People selectByPrimaryKey(long id);

    /**
     * 根据条件查询
     * @param condition
     * @return
     */
    List<People> selectByCondition(People condition);

    /**
     * 根据条件分页查询
     * @param condition
     * @param firstNum 记录开始数字 从0开始
     * @param limitNum 每页限制条数
     * @return
     */
    List<People> selectByConditionForPage(People condition,@Param("firstNum")int firstNum,@Param("limitNum")int limitNum);

    /**
     * 根据条件查询数量
     * @param condition
     * @return
     */
    int selectCountByCondition(People condition);

    /**
     * 根据唯一主键删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(@Param("id")Long id);

    /**
     * 添加
     * @param record
     * @return
     */
    int insert(People record);

    /**
     * 根据条件修改
     * @param record
     * @return
     */
    int updateByCondition(People record);

}