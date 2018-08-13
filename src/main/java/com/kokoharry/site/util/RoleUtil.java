package com.kokoharry.site.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luyb on 2017/9/27.
 */
public class RoleUtil {

    public static List<String> setPermissions(int operationAuthority){

        List<String> list = new ArrayList<>();
        if((operationAuthority&1)>0){
            list.add("C");
        }
        if((operationAuthority&2)>0){
            list.add("R");
        }
        if((operationAuthority&4)>0){
            list.add("U");
        }
        if((operationAuthority&8)>0){
            list.add("D");
        }
        return list;
    }
}
