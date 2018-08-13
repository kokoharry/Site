import java.util.List;

/**
 *  service 层接口
 */
public interface IPeopleService {

    /**
     * 分页获取
     * @param fristNum
     * @param limitNum
     * @param  people
     * @return
     */
    List<People> getPeopleForPage(int fristNum, int limitNum, People people);

    /**
     * 获取所有
     * @param  people
     * @return
     */
    List<People> getPeopleForAll(People people);
    /**
     *计算数量
     * @return
     */
    int getPeopleCount(People people);

    /**
     * 添加
     * @param people
     * @return
     */
    People addPeople(People people);

    /**
     * 删除
     * @param id
     * @return
     */
    int deletePeople(long id);

    /**
     * 编辑
     * @param people
     * @return
     */
    int editPeople(People people);

}