package com.gouuse.checkin.bean;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.Gson;

/**
 *
 * Created by zhengjiong on 15/10/16.
 */
public class CheckInResult implements Parcelable {
    /**
     * {
     "checkin_date": "2015-10-16",
     "checkin_time": 58560,
     "checkin_time_format": "16:16:45",
     "address": "四川省成都市成华区一环路东2段-28号",
     "longitude": 104.10832,
     "latitude": 30.66888,
     "from_type": 2,   //数据来源：1-考勤机导入 2-手机打卡 3-外勤打卡同步
     "img_url": "",
     "checkin_type": 2,  //上班，下班，1-上班，2-下班
     "unusual_type_id": 2, //打卡结果：0：正常，1迟到 2-早退
     "have_extra_work_action": 0, //是否申请了加班
     "have_outwork_action": 0  //是否申请了外勤
     is_work_day 1-工昨日 0-休息日
     }
     */

    public String checkin_date;
    public String checkin_time_format;
    public String checkin_time;
    public String address;
    public double longitude;
    public double latitude;
    public int from_type;
    public String img_url;
    public int checkin_type;
    public int unusual_type_id;
    public int have_extra_work_action;
    public int have_outwork_action;
    public int status;
    public int is_work_day; //1-工昨日 0-休息日
    public int isSync;//0不同步, 1同步


    public static CheckInResult json2Obj(String json) {
        return new Gson().fromJson(json, CheckInResult.class);
    }

    public String getCheckin_time() {
        return checkin_time;
    }

    public void setCheckin_time(String checkin_time) {
        this.checkin_time = checkin_time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public int getFrom_type() {
        return from_type;
    }

    public void setFrom_type(int from_type) {
        this.from_type = from_type;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    public int getCheckin_type() {
        return checkin_type;
    }

    public void setCheckin_type(int checkin_type) {
        this.checkin_type = checkin_type;
    }

    public int getUnusual_type_id() {
        return unusual_type_id;
    }

    public void setUnusual_type_id(int unusual_type_id) {
        this.unusual_type_id = unusual_type_id;
    }

    public int getHave_extra_work_action() {
        return have_extra_work_action;
    }

    public void setHave_extra_work_action(int have_extra_work_action) {
        this.have_extra_work_action = have_extra_work_action;
    }

    public int getHave_outwork_action() {
        return have_outwork_action;
    }

    public void setHave_outwork_action(int have_outwork_action) {
        this.have_outwork_action = have_outwork_action;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIs_work_day() {
        return is_work_day;
    }

    public void setIs_work_day(int is_work_day) {
        this.is_work_day = is_work_day;
    }

    public int getIsSync() {
        return isSync;
    }

    public void setIsSync(int isSync) {
        this.isSync = isSync;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.checkin_date);
        dest.writeString(this.checkin_time_format);
        dest.writeString(this.checkin_time);
        dest.writeString(this.address);
        dest.writeDouble(this.longitude);
        dest.writeDouble(this.latitude);
        dest.writeInt(this.from_type);
        dest.writeString(this.img_url);
        dest.writeInt(this.checkin_type);
        dest.writeInt(this.unusual_type_id);
        dest.writeInt(this.have_extra_work_action);
        dest.writeInt(this.have_outwork_action);
        dest.writeInt(this.status);
        dest.writeInt(this.is_work_day);
        dest.writeInt(this.isSync);
    }

    public CheckInResult() {
    }

    protected CheckInResult(Parcel in) {
        this.checkin_date = in.readString();
        this.checkin_time_format = in.readString();
        this.checkin_time = in.readString();
        this.address = in.readString();
        this.longitude = in.readDouble();
        this.latitude = in.readDouble();
        this.from_type = in.readInt();
        this.img_url = in.readString();
        this.checkin_type = in.readInt();
        this.unusual_type_id = in.readInt();
        this.have_extra_work_action = in.readInt();
        this.have_outwork_action = in.readInt();
        this.status = in.readInt();
        this.is_work_day = in.readInt();
        this.isSync = in.readInt();
    }

    public static final Creator<CheckInResult> CREATOR = new Creator<CheckInResult>() {
        public CheckInResult createFromParcel(Parcel source) {
            return new CheckInResult(source);
        }

        public CheckInResult[] newArray(int size) {
            return new CheckInResult[size];
        }
    };
}
