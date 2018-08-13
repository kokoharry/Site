package com.kokoharry.site.people.service.impl;

import com.kokoharry.site.people.bean.People;
import com.kokoharry.site.people.dao.PeopleMapper;
import com.kokoharry.site.people.service.IPeopleService;
import com.kokoharry.site.system.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by luyb on 2017/9/6.
 */
@Service("peopleService")
public class PeopleServiceImpl implements IPeopleService {

    @Autowired
    private PeopleMapper peopleMapper;


    /**
     * 分页获取
     *
     * @param fristNum
     * @param limitNum
     * @param people
     * @return
     */
    @Override
    public List<People> getPeopleForPage(int fristNum, int limitNum, People people) {
        return peopleMapper.getListForPage(people,fristNum,limitNum);
    }

    /**
     * 分页获取总数
     *
     * @param people
     * @return
     */
    @Override
    public int getPeopleCountForPage(People people) {
        return peopleMapper.getTotalCountForPage(people);
    }
}
