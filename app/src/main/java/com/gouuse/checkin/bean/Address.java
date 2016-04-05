package com.gouuse.checkin.bean;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 打卡位置
 *
 * @author zhengjiong
 * @time 2014-3-24 下午9:07:25
 */
public class Address {


    public ArrayList<Place> place_list = new ArrayList<Place>();
    public List<String> work_time = new ArrayList<>();
    public List<String> work_day_list = new ArrayList<>();
    public int checkin_distance;
    public int need_take_photo;
    public int outwork_need_take_photo;
    public int close_mobile_checkin;
    public int ask_cover_today_start;    //询问是否覆盖今天上班打卡记录 1:打过卡, 0:没有打过卡
    public int ask_cover_today_end;        //询问是否覆盖今天下班打卡记录 1:打过卡, 0:没有打过卡
    public int ask_chose_yesterday_end;    //是否设为昨天下班打卡       1:昨天晚上没有打过卡, 0:昨天晚上打过卡

    /**
     * "ask_cover_today_start": 1,//询问是否覆盖今天上班打卡记录
     * "ask_cover_today_end": 0,//询问是否覆盖今天下班打卡记录
     * "ask_chose_yesterday_end": 1//是否设为昨天下班打卡
     */
    public class Place {
        public String checkin_place_id;
        public String company_id;
        public String title;
        public String address;
        public double longitude;
        public double latitude;
        public String last_edit_time;
        public String last_edit_date;
        public float distance = 10000;            //离我的距离
        public boolean isSelected;

        public String fullSpell;//全部拼音字母(如:成都就是chengdu)
        public String firstSpell;//拼音首字母(如:成都就是cd)
        public String sortLetters;
    }
}
