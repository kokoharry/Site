package com.kokoharry.site.people.service;

import com.kokoharry.site.people.bean.People;

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
     * 分页获取总数
     * @param  people
     * @return
     */
    int getPeopleCountForPage(People people);


}