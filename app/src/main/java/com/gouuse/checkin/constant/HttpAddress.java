package com.gouuse.checkin.constant;


import com.gouuse.checkin.BuildConfig;

public class HttpAddress {

    public static final String GL_ADDRESS = BuildConfig.ADDRESS;
    public static final String APPKEY = BuildConfig.APPKEY;
    public static final String APPTOKEN = BuildConfig.APPTOKEN;


    public final static String GOUUSE_MISHU = GL_ADDRESS + "/api/message/init_message";
    public final static String GET_EXTRA_WORK = GL_ADDRESS + "/api/attendence/extra_work/mine";//加班剩余时间，剩余调休劵
    public final static String EXTRA_WORK_CHANGE_DO = GL_ADDRESS + "/api/attendence/extra_work/change_do";//兑换调休劵
    public final static String ACCOUNT_UPDATE_DO = GL_ADDRESS + "/api/profile/account_update_do";
    public final static String PROFILE_UPDATE_DO = GL_ADDRESS + "api/profile/profile_update_do";
    public final static String MESSAGE_REPUSH = GL_ADDRESS + "api/message/message_repush";
    public final static String VERSION_CHECK = GL_ADDRESS + "api/version/getVerInfo?app=android";
    public final static String LOGIN = GL_ADDRESS + "api/account/login";
    public final static String MAIN_TOPNEWSLIST = GL_ADDRESS + "api/message/top_new_list";
    public final static String INSTALLED = GL_ADDRESS + "api/app/installed";
    public final static String DEPARTMENT = GL_ADDRESS + "api/department/department_list";// 获取部门列表
    public final static String DEPARTMENT_MENBER = GL_ADDRESS + "api/department/department_member";
    public final static String GROUP_LIST = GL_ADDRESS + "api/group/group_list";
    public final static String GROUP_LIST_MEMBERS = GL_ADDRESS + "api/group/group_member_list";// 群组人员列表
    public final static String LOGIN_OUT = GL_ADDRESS + "api/account/logout";
    public final static String SEARTECH_PERSON = GL_ADDRESS + "api/member/search";
    public final static String GET_USERINFO = GL_ADDRESS + "api/member/getUserInfo";
    public final static String GET_USERINFO_NUMS = GL_ADDRESS + "api/member/all_message_count";// 获取顶部消息条目数量
    public final static String SETTING_BACKGROUND = GL_ADDRESS + "api/settings/background";
    public final static String SETTING_AVATAR = GL_ADDRESS + "api/settings/avatar";
    public final static String MINE = GL_ADDRESS + "api/pm/mine";// 我的私信列表（人的关系）
    public final static String CHAT = GL_ADDRESS + "api/pm/chat";// 我的私信对话列表（和某人的聊天记录）
    public final static String DELETE = GL_ADDRESS + "api/pm/delete";// 刪除和某人私信中的某一条
    public final static String MESSAGE_SET_READED = GL_ADDRESS + "api/pm/set_read";// 将与某人对话的未读私信设为已读
    public final static String DELETE_ALL = GL_ADDRESS + "api/pm/delete_all";// 删除和某人的所有私信
    public final static String SETTING_HELP = GL_ADDRESS + "api/help"; // 获取帮助列表
    public final static String MESSAGE_MESSAGE_LIST = GL_ADDRESS + "api/message/message_list";// 具体应用消息列表
    public final static String MESSAGE_DELETE = GL_ADDRESS + "api/message/message_delete";// 删除已读信息
    public final static String ANNOUCE_LIST = GL_ADDRESS + "api/announce/lists";// 获取公告列表
    public final static String ANNOUCE_PUBLISH = GL_ADDRESS + "api/announce/create";// 发布公告
    public final static String ANNOUCE_CONTENT = GL_ADDRESS + "api/announce/get_content";// 获取公告内容
    public final static String RANKINGSCORE = GL_ADDRESS + "api/member/rankingScore";// 获取积分排行
    public final static String PERSON_PEREF_BG = GL_ADDRESS + "api/settings/defaultBackground";// 设置个人中心背景
    public final static String PERSON_PEREF_AVATAR = GL_ADDRESS + "api/settings/defaultAvatar";// 设置系统头像
    public final static String MICRO_CHAT_LIST = GL_ADDRESS + "api/ichat/ichats";// 获取微聊列表
    public final static String MICRO_NEWCHAT_PUBLISH = GL_ADDRESS + "api/ichat/submit";// 转发,发布
    public final static String MICRO_CHAT_DETAIL = GL_ADDRESS + "api/ichat/replys";// 获取评论列表
    public final static String MICRO_CHAT_PRAISE = GL_ADDRESS + "api/ichat/dodig";// 微博赞
    public final static String MICRO_CHAT_WEILIAO = GL_ADDRESS + "api/ichat/detail";// 微博详情
    public final static String MICRO_CHAT_PRAISE_CANCEL = GL_ADDRESS + "api/ichat/canceldig";// 取消赞
    public final static String MICRO_CHAT_REPLY = GL_ADDRESS + "api/ichat/reply_do";// 微聊回复,评论
    public final static String MICRO_CHAT_FAVORITES = GL_ADDRESS + "api/ichat/dofav";// 收藏
    public final static String MICRO_CHAT_DELETE = GL_ADDRESS + "api/ichat/delete";// 删除微聊
    public final static String MICRO_CHAT_DELETE_REPLY = GL_ADDRESS + "api/ichat/del_reply";// 删除评论
    public final static String MICRO_CHAT_FAVORITES_CANCEL = GL_ADDRESS + "api/ichat/cancelfav";// 取消收藏
    public final static String SEND_LETTER = GL_ADDRESS + "api/pm/send";// 发送私信
    public final static String MICRO_CHAT_ATTENTION = GL_ADDRESS + "api/ichat/do_attention";// 关注
    public final static String MICRO_CHAT_ATTENTION_CANCEL = GL_ADDRESS + "api/ichat/check_attention";// 取消关注
    public final static String MICRO_CHAT_ATTENTION_LIST = GL_ADDRESS + "api/ichat/fans";// 关注列表
    public final static String GL_ADDRESS_CLOCKING = "http://10.2.11.160:8866/ProjectKWebService/";
    // public final static String GL_ADDRESS_CLOCKING =
    // "http://192.168.1.100:8866/ProjectKWebService/";
    // public final static String CLOCKING_EXTRA_WORK = GL_ADDRESS_CLOCKING +
    // "api/getExtraWork";//加班记录
    public final static String CLOCKING_EXTRA_WORK = GL_ADDRESS + "api/attendence/leave/leave_list";// 加班记录
    public final static String CLOCKING_HAVE_BEEN_LIST = GL_ADDRESS + "api/attendence/leave/my_audit_list";// 已审批
    // public final static String CLOCKING_WORK_RECORD = GL_ADDRESS_CLOCKING +
    // "api/getWorkRecord";//考勤记录
    public final static String CLOCKING_WORK_RECORD = GL_ADDRESS + "api/attendence/statistics/checking_record";// 考勤记录
    public final static String CLOCKING_GET_CHILD_MEMBERLIST = GL_ADDRESS + "api/member/getSuperiorMemberList";// 传领导id
    // 返回下级人员列表
    public final static String CLOCKING_LEAVE_WORK_APPLY_TYPE = GL_ADDRESS + "/api/attendence/options/get_company_type";// 获取请假类型列表
    // (必填参数：token)
    public final static String CLOCKING_APPLY = GL_ADDRESS + "/api/attendence/leave/create_do";// 申请请假、加班、补卡
    public final static String CLOCKING_CREATE_DO = GL_ADDRESS + "/api/attendence/action/create_do";// 外勤申请
    public final static String CLOCKING_LEAVE_TYPE = GL_ADDRESS + "/api/attendence/options/leave_type"; // 公司请假类型
    public final static String CLOCKING_APPROVAL = GL_ADDRESS + "/api/attendence/action/audit_do";// 审批
    public final static String CLOCKING_OVERVIEW = GL_ADDRESS + "/api/attendence/statistics/monthly_report";// 考勤概况
    public final static String CLOCKING_CHECK_IN_PLACE = GL_ADDRESS + "/api/attendence/mobile_checkin/checkin_place";// 打卡地址列表
    public final static String CLOCKING_RECORD_DETAIL = GL_ADDRESS + "api/attendence/leave/action_info"; // 补卡详情
    public final static String CLOCKING_CHECK_IN = GL_ADDRESS + "api/attendence/mobile_checkin"; // 手机打卡
    public final static String CLOCKING_CHECK_IN_OUTWORK = GL_ADDRESS + "api/attendence/outwork_checkin"; // 手机打卡
    public final static String CLOCKING_DELETE_ACTION = GL_ADDRESS + "api/attendence/action/delete_action";
    public final static String CLOCKING_GET_LEAVE_HOUR = GL_ADDRESS + "/api/attendence/action/get_leave_total_hour";// 獲取請假時間
    public final static String GET_AUTH_CODE = GL_ADDRESS + "/api/system/get_auth_code"; // 获取auth_code
    // 用于有时间限制的请求，比如手机打卡，外勤打卡
    public final static String CLOCKING_FILE_UPLOAD_DO = GL_ADDRESS + "/api/attendence/action/file_upload_do"; // 获取auth_code
    public final static String CLOCKING_FILE_DEL_DO = GL_ADDRESS + "/api/attendence/action/file_delete_do"; // 获取auth_code
    // 用于有时间限制的请求，比如手机打卡，外勤打卡
    public final static String CLOCKING_INFO = GL_ADDRESS + "/api/attendence/action/info"; // 动态信息

    public final static String KAOQIN_EXTRA_WORK = GL_ADDRESS + "api/kaoqin/leave/leave_list";// 加班记录
    public final static String KAOQIN_HAVE_BEEN_LIST = GL_ADDRESS + "api/kaoqin/leave/my_audit_list";// 已审批
    // public final static String CLOCKING_WORK_RECORD = GL_ADDRESS_CLOCKING +

    public final static String KAOQIN_WORK_RECORD_POINT = GL_ADDRESS
            + "/api/attendence/statistics/monthly_unusual_record";// 考勤记录
    public final static String KAOQIN_WORK_RECORD_DAY = GL_ADDRESS + "/api/attendence/statistics/daily_record";// 某日考勤记录
    public final static String KAOQIN_MINE = GL_ADDRESS + "/api/attendence/action/mine"; // 我/我的下属
    // 的申请列表
    public final static String KAOQIN_AUDIT_LIST = GL_ADDRESS + "/api/attendence/action/audit_list";// 我的待/已审列表
    public final static String MOBILE_CHECKIN_RECORD = GL_ADDRESS
            + "/api/attendence/statistics/daily_mobile_checking_record";// 我的待/已审列表

    public final static String KAOQIN_GET_CHILD_MEMBERLIST = GL_ADDRESS + "api/member/getSuperiorMemberList";// 传领导id
    // 返回下级人员列表
    public final static String KAOQIN_LEAVE_WORK_APPLY_TYPE = GL_ADDRESS + "/api/kaoqin/options/get_company_type";// 获取请假类型列表
    // (必填参数：token)
    public final static String CLOCK_SET_REMIND = GL_ADDRESS + "/api/attendence/action/send_remind";//
    public final static String KAOQIN_APPROVAL = GL_ADDRESS + "api/kaoqin/leave/audit_do";// 审批
    public final static String KAOQIN_OVERVIEW = GL_ADDRESS + "api/kaoqin/statistics/monthly_report";// 考勤概况
    public final static String KAOQIN_CHECK_IN_PLACE = GL_ADDRESS + "api/kaoqin/mobile_checkin/checkin_place";// 打卡地址列表
    public final static String KAOQIN_RECORD_DETAIL = GL_ADDRESS + "api/kaoqin/leave/action_info"; // 补卡详情
    public final static String KAOQIN_CHECK_IN = GL_ADDRESS + "api/kaoqin/mobile_checkin"; // 手机打卡
    public final static String KAOQIN_CHECK_IN_OUTWORK = GL_ADDRESS + "api/kaoqin/outwork_checkin"; // 手机打卡
    public final static String KAOQIN_DELETE_ACTION = GL_ADDRESS + "api/kaoqin/leave/delete_action";// 请假撤销功能
    public final static String KAOQIN_GET_LEAVE_HOUR = GL_ADDRESS + "api/kaoqin/leave/get_leave_total_hour";// 獲取請假時間
    public final static String KAOQIN_GET_CHECKIN_POINT = GL_ADDRESS + "api/kaoqin/options/get_checkin_point"; // 获取某日考勤点
    public final static String KAOQIN_GET_DYNAMICALLY_MEMBER = GL_ADDRESS + "api/kaoqin/options/get_dynamically_member";// 获取公司门店人员列表
    public final static String KAOQIN_GET_MONTH_CHECKIN_POINT = GL_ADDRESS
            + "api/kaoqin/options/get_month_checkin_point";// 获取某月考勤点
    public final static String KAOQIN_GET_MY_RECORD = GL_ADDRESS + "api/kaoqin/mobile_checkin/my_record"; // 登录用户手机打卡记录

    public final static String MESSAGE_CONFIG = GL_ADDRESS + "/api/socket/message_config"; // 获取message_id,channel号
    public final static String GET_NOW_LEAVE_LIST = GL_ADDRESS + "api/attendence/action/get_now_leave_list";// 获取今日缺席数据
    public final static String MESSAGE_UPDATE = GL_ADDRESS + "/api/message/message_update"; // 未读消息状态更新接口
    public final static String MICRO_MEDIAUPLOAD = GL_ADDRESS + "api/ichat/upload_media";// 微聊上传多媒体
    public final static String MICRO_PHOTOUPLOAD = GL_ADDRESS + "api/ichat/upload_more";// 微聊上传图片

    public final static String DOCUMENT_LIST = GL_ADDRESS + "api/documents/documents_list";// 获取文档列表
    public final static String DOCUMENT_CONTENT = GL_ADDRESS + "api/documents/documents_content";// 文档详情
    public final static String DOCUMENT_FAOVRIT = GL_ADDRESS + "api/documents/documents_favorite_do";// 收藏
    public final static String DOCUMENT_DOWNLOAD = GL_ADDRESS + "api/documents/documents_download";// 下载文档
    public final static String DOCUMENT_AUTH_LIST = GL_ADDRESS + "api/documents/documents_auth_list";// 权限列表
    public final static String SUBMIT_COMMENT_DOCUMENT = GL_ADDRESS + "api/documents/documents_comment_do";// 文档评论提交
    public final static String COMMENT_DOCUMENT_LIST = GL_ADDRESS + "api/documents/documents_comment_list";// 文档评论列表
    public final static String DOCUMENT_UPLOAD = GL_ADDRESS + "api/documents/upload";// 文档上传
    public final static String DOCUMENT_SUCCEED_AUTH_ADD = GL_ADDRESS + "api/documents/documents_succeed_auth_add";// 文档继承当前文件夹权限
    public final static String DOCUMENT_AUTH_DELETE = GL_ADDRESS + "api/documents/documents_auth_delete_do";// 文档权限删除
    public final static String DOCUMENT_AUTH_ADD = GL_ADDRESS + "api/documents/documents_auth_add";// 文档权限添加
    public final static String DOCUMENT_AUTH_UPDATE = GL_ADDRESS + "api/documents/documents_auth_update";// 文档权限修改
    public final static String DOCUMENT_FOLDER_DO = GL_ADDRESS + "api/documents/documents_folder_do";// 文档件建创建
    public final static String DOCUMENT_DELETE_DO = GL_ADDRESS + "api/documents/documents_delete_do";// 文档删除
    public final static String DOCUMENT_COPY_M0VE_DO = GL_ADDRESS + "api/documents/documents_copy_or_move_do";// 文档移动、复制
    public final static String DOCUMENT_SHARE = GL_ADDRESS + "api/documents/documents_share";// 文档分享
    public final static String DOCUMENT_SHARE_DO = GL_ADDRESS + "api/documents/documents_share_do";// 文档分享提交处理

    public final static String WEIYOU_LIST = GL_ADDRESS + "api/micromail/index";// 微邮列表
    public final static String WEIYOU_SEND = GL_ADDRESS + "api/micromail/sendmail";// 微邮发送
    public final static String WEIYOU_SAVEDRAFT = GL_ADDRESS + "api/micromail/save_draft";// 微邮发送
    public final static String WEIYOU_DETIAL = GL_ADDRESS + "api/micromail/detail";// 微邮详情
    public final static String WEIYOU_DELETE = GL_ADDRESS + "api/micromail/delete";// 删除微邮
    public final static String WEIYOU_UNREADED = GL_ADDRESS + "api/micromail/unread";// 获取未读微邮
    public final static String WEIYOU_ACCOUNT_OUTSIDE = GL_ADDRESS + "api/micromail/account_outside";// 外部邮箱

    public final static String SALARYSHEET_LIST = GL_ADDRESS + "api/wage/index";// 工资条列表
    public final static String SALARYSHEET_DETAIL = GL_ADDRESS + "api/wage/detail";// 工资条详情
    public final static String SALARYSHEET_DELETE = GL_ADDRESS + "api/wage/delete";// 删除工资条

    public final static String VOTE = GL_ADDRESS + "api/vote/do_vote"; // 投票
    public final static String VOTELIST_RESULT = GL_ADDRESS + "api/vote/detail"; // 投票详情

    /**
     * 周报日报请求URL
     */

    public final static String REPORT_LEADER = GL_ADDRESS + "api/member/getUserInfo";// 获取领导信息
    public final static String REPORT = GL_ADDRESS + "api/report/index"; // 周报日报列表
    public final static String REPORT_DETAIL = GL_ADDRESS + "api/report/detail"; // 周报日报详情
    public final static String REPORT_WRITE = GL_ADDRESS + "api/report/submit"; // 写周报日报
    public final static String REPORT_SUBMEMBER = GL_ADDRESS + "api/report/getMembers"; // 下属列表
    public final static String REPORT_COMMENTLIST = GL_ADDRESS + "api/report/comment_list"; // 下属列表
    public final static String REPORT_NEWCOMMENT = GL_ADDRESS + "api/report/comment_do";// 新评论
    /**
     * 待办请求URl
     */
    public final static String TODOLIST = GL_ADDRESS + "api/todolist/index"; // 待办事项列表
    public final static String TODOLIST_ADD = GL_ADDRESS + "api/todolist/todo_add_do"; // 添加待办
    public final static String TODOLIST_SET = GL_ADDRESS + "api/todolist/todo_update_do";// 待办设置
    public final static String TODOLIST_TOFINISH = GL_ADDRESS + "api/todolist/todo_finish_do";// 设置完成
    public final static String TODOLIST_TOUNFINISH = GL_ADDRESS + "api/todolist/finish_todo_do";// 设置未完成
    public final static String TODOLIST_DELETE = GL_ADDRESS + "api/todolist/todo_delete_do"; // 待办删除

    /**
     * 任务
     */
    public final static String TASKINDEX = GL_ADDRESS + "/api/task/index";// 任务跟催列表
    public final static String DELAY = GL_ADDRESS + "api/task/delay";// 设置延时
    public final static String SEND_TIP = GL_ADDRESS + "api/task/send_tip";// 发送提醒
    public final static String TASKSETTING = GL_ADDRESS + "api/task/complete";// 设置完成
    public final static String DELETETASK = GL_ADDRESS + "api/task/delete";// 取消任务
    public final static String NEWTASK = GL_ADDRESS + "api/task/new_task";// 新建任务

    // 2015年7月20 任务
    public final static String CREATNEWTASK = GL_ADDRESS + "api/task_v3/new_task";// 发起任务
    public final static String GETDETAILTASK = GL_ADDRESS + "api/task/detail";// 任务详情

    public final static String TASKINDEX_V3 = GL_ADDRESS + "api/task_v3/index";// 任务跟催列表
    public final static String TASK_V3_GET_COMMENTS = GL_ADDRESS + "api/task/get_comments";// 任务跟催列表
    public final static String TASK_V3_COMMENT_DO = GL_ADDRESS + "api/task/comment_do"; // 提交评论
    /**
     * 会议管理
     */
    public final static String MEETING_INDEX = GL_ADDRESS + "api/meeting/index";// 会议列表
    public final static String MEETING_INVITE_DO = GL_ADDRESS + "api/meeting/meeting_receipt_do";// 会议同意、拒绝
    public final static String MEETING_DELAY_DO = GL_ADDRESS + "api/meeting/meeting_delay_do";// 会议延期
    public final static String MEETING_CANCEL_DO = GL_ADDRESS + "api/meeting/meeting_revoke_do";// 会议取消处理
    public final static String MEETING_DELETE_DO = GL_ADDRESS + "api/meeting/meeting_delete_do";// 会议取消处理
    public final static String MEETING_DETIAL = GL_ADDRESS + "api/meeting/meeting_info";// 会议详情
    public final static String MEETING_ADD_DO = GL_ADDRESS + "api/meeting/meeting_add_do";// 会议发起
    public final static String MEETING_UPDATE_DO = GL_ADDRESS + "api/meeting/meeting_update_do";// 修改会议
    public final static String MEETING_SUMMARYLIST = GL_ADDRESS + "api/meeting/meeting_summary_list";// 纪要列表
    public final static String MEETING_SUMMARYDO = GL_ADDRESS + "api/meeting/summary_audit_do";// 纪要操作
    public final static String MEETING_REVOKE = GL_ADDRESS + "api/meeting/summary_revoke_do";// 纪要操作
    public final static String MEETING_GET_NETWORK_MEETINGID = GL_ADDRESS + "api/meeting/meeting_get_netid";// 获取会议id
    public final static String MEETING_JOIN_BY_ID = GL_ADDRESS + "api/meeting_join/join_do";// 通过id加入会议
    public final static String MEETING_UPLOAD_FILE = GL_ADDRESS + "api/meeting/upload";// 上传文件
    /**
     * 流程
     */
    public final static String APPROVALPROCESS_FINANCELIST = GL_ADDRESS + "api/finance/apply/get_flow_list";// 财务审批流程列表
    public final static String APPROVALPROCESS_APPLYLIST = GL_ADDRESS + "api/finance/apply/get_action_list";// 发起列表
    public final static String APPROVALPROCESS_AUDITLIST = GL_ADDRESS + "api/finance/audit/get_action_list";// 待审
    public final static String APPROVALPROCESS_CREAT = GL_ADDRESS + "api/finance/apply/create_do";// 发起财务流程
    public final static String APPROVALPROCESS_APPROVE = GL_ADDRESS + "api/finance/audit/audit_do";// 审核
    public final static String APPROVALPROCESS_APPROVEINFO = GL_ADDRESS + "api/finance/apply/info";// 申请流程详情
    public final static String APPROVALPROCESS_AUDITINFO = GL_ADDRESS + "api/finance/audit/info";// 审核流程详情

    public final static String PROJECT_PROJECTNAMES = GL_ADDRESS + "api/project/project_name_list";// 项目列表
    public final static String GET_FEE_TYPE = GL_ADDRESS + "api/finance/apply/get_category";// 获取费用类型
    public final static String UPLOAD_IMAGE_FINANCE = GL_ADDRESS + "api/finance/apply/file_upload";// 上传图片

    /*******************************
     * 日程start
     ***************************************/
    public final static String GET_SCHEDUEL_DATA = GL_ADDRESS + "api/calendar/index_do";// 獲取日程數據
    public final static String NEW_SCHEDUEL = GL_ADDRESS + "api/calendar/calendar_add_do";// 添加、修改接口：
    public final static String DEL_SCHEDULE = GL_ADDRESS + "api/calendar/calendar_delete_do";// 刪除日程
    public final static String GET_CALENDAR_AUTH_MEMBER = GL_ADDRESS + "api/calendar/calendar_auth_member_do";// 獲取可查看日程的用戶
    /******************************* 日程end ***************************************/

    /*******************************
     * 注册start
     ***************************************/
    public final static String REGISTER_GETAUTHCODE = GL_ADDRESS + "api/register/send_code_do";// 获取验证码
    public final static String REGISTER_AUTH = GL_ADDRESS + "api/register/check_mobile_do";// 验证手机号
    public final static String REGISTER_DO = GL_ADDRESS + "api/register/register_do";// 注册
    public final static String REGISTER_UPLOADMEMBERS = GL_ADDRESS + "api/member/invite_member_do";// 上传公司成员
    public final static String REGISTER_SETLEADER = GL_ADDRESS + "api/member/report_add_do";// 上传公司成员
    public final static String REGISTER_GETOFTWARE = GL_ADDRESS + "api/app/all";// 获取插件
    public final static String REGISTER_OPENOFTWARE = GL_ADDRESS + "api/app/add_do";// 安装插件
    public final static String MODIFYPW_GETAUTHCODE = GL_ADDRESS + "api/findpassword/send_code_do";// 获取修改密码验证码
    public final static String MODIFYPW_AUTH = GL_ADDRESS + "api/findpassword/check_mobile_do";// 修改密码验证
    public final static String MODIFYPW_SETPW = GL_ADDRESS + "api/findpassword/reset_do";// 重设密码
    public final static String SEND_CODE_TO = GL_ADDRESS + "api/findpassword/send_code_do";// 获取验证码
    public final static String CHECK_DO = GL_ADDRESS + "api/findpassword/check_do";// 验证手机号、邮箱、账号
    public final static String RESET_DO = GL_ADDRESS + "api/findpassword/reset_do";// 修改密码
    public final static String SEND_CODE_FOR_MOBILE_UPDATE = GL_ADDRESS + "api/profile/send_code_for_mobile_update";
    public final static String SEND_CODE_FOR_EMAIL_UPDATE = GL_ADDRESS + "api/profile/send_email_for_email_update";
    public final static String MOBILE_UPDATE_DO = GL_ADDRESS + "api/profile/mobile_update_do";// 修改手机号
    public final static String EMAIL_UPDATE_DO = GL_ADDRESS + "api/profile/email_update_do";// 修改邮箱

    /******************************* 注册end ***************************************/

    /*******************************
     * 外勤start
     ***************************************/
    public final static String WORK_ORDER_LIST = GL_ADDRESS + "api/outwork/ticket/mine";
    public final static String WORK_ORDER_OPERATE = GL_ADDRESS + "api/outwork/ticket/deal_do";
    public final static String REPORT_LIST = GL_ADDRESS + "api/outwork/report/mine";
    public final static String NEW_REPORT = GL_ADDRESS + "api/outwork/report/report_do";
    public final static String OUTWORK_MESSAGE = GL_ADDRESS + "api/outwork/message/mine";
    public final static String TRACK = GL_ADDRESS + "api/outwork/point/track";
    public final static String OUTWORK_MESSAGE_REPLY_LIST = GL_ADDRESS + "api/outwork/message/reply_list";
    public final static String OUTWORK_MESSAGE_REPLY = GL_ADDRESS + "api/outwork/message/reply_do";
    public final static String TRACK_UPLOAD = GL_ADDRESS + "api/outwork/point/add_do";
    public final static String CHECK_TRACK_SETTINGS = GL_ADDRESS + "api/outwork/report/auto_report_info";
    /******************************* 外勤end ***************************************/

    /*******************************
     * 有问有答START
     ***************************************/
    public final static String QUESTION_LIST = GL_ADDRESS + "api/ask/index";// 问题列表
    public final static String QUESTION_COMMENTLIST = GL_ADDRESS + "api/ask/reply_list";// 评论列表
    public final static String QUESTION_PRAISE = GL_ADDRESS + "api/ask/commend_do";// ZAN
    public final static String QUESTION_COLLECT = GL_ADDRESS + "api/ask/fav_do";// 搜藏
    public final static String QUESTION_CATEGORYS = GL_ADDRESS + "api/ask/categorys";// 分类
    public final static String QUESTION_SUBMIT = GL_ADDRESS + "api/ask/submit";// 提交内容
    public final static String QUESTION_MY = GL_ADDRESS + "api/ask/my_ask";// 提交内容
    public final static String QUESTION_ADETIAL = GL_ADDRESS + "api/ask/answer_details";// 回答详情
    public final static String QUESTION_QDETIAL = GL_ADDRESS + "api/ask/question_details";// 提问详情
    /******************************* 有问有答END ***************************************/

    /*******************************
     * 外部通訊錄
     **********************************************/
    public final static String EXTERNAL_CONTACT_IMPORT = GL_ADDRESS + "api/out_contact/import";// 外部通訊錄導入
    public final static String GET_EXTERNAL_CONTACT = GL_ADDRESS + "api/out_contact/get_contacts";// 外部联系人列表
    public final static String ADD_EXTERNAL_CONTACT = GL_ADDRESS + "api/out_contact/add_to_mycontact";// 添加外部联系人
    public final static String CONTACT_AGREE_OR_CANCEL = GL_ADDRESS + "api/out_contact/operate";// 同意取消

    /*******************************
     * 人事管理START
     ***************************************/
    public final static String PERSONELMANAGER_CARD = GL_ADDRESS + "api/hr/member_info";// 人事卡片
    public final static String PERSONELMANAGER_POSITIONCHANGE = GL_ADDRESS + "api/hr/position_change_record";// 人事职位变迁
    public final static String PERSONELMANAGER_WAGECHANGE = GL_ADDRESS + "api/hr/wage_change_record";// 工资变动
    /******************************* 人事管理END ***************************************/

    /*******************************
     * 固定資產start
     ***************************************/
    public final static String GET_MY_DEVICES = GL_ADDRESS + "api/assets/get_goods_list";
    public final static String NEWAPPLY = GL_ADDRESS + "api/assets/apply_do";// 发起申领流程申请接口
    public final static String GET_ASSET_TYPE = GL_ADDRESS + "api/assets/get_types"; // 获取资产类型列表接口
    public final static String GET_APPLY_RECORD = GL_ADDRESS + "api/assets/get_apply_list";// 获取申领记录列表接口
    public final static String GET_RETURN_RECORD = GL_ADDRESS + "api/assets/get_return_list";// 获取归还记录列表接口
    public final static String GET_TRANSFER_RECORD = GL_ADDRESS + "api/assets/get_transfer_list";// 获取转移记录列表接口
    public final static String GET_SCRAP_RECORD = GL_ADDRESS + "api/assets/get_scrap_list";// 获取报废记录列表接口
    public final static String GET_AUDIT_RECORD = GL_ADDRESS + "api/assets/get_audit_list";// 获取我的审批接口
    public final static String GET_MY_UNDERLING = GL_ADDRESS + "api/assets/get_follow_list";// 获取我的下属接口
    public final static String APPLY_DO = GL_ADDRESS + "api/assets/audit_do";// 流程审批
    /******************************* 固定資產end ***************************************/

    /*******************************
     * 项目管理START
     ***************************************/
    public final static String PROJECTMANAGER_PROLIST = GL_ADDRESS + "api/project/project_list";// 项目列表
    public final static String PROJECTMANAGER_TASKLIST = GL_ADDRESS + "api/project/project_task_list";// 任务列表
    public final static String PROJECT_DYNAMIC_LIST = GL_ADDRESS + "api/project/dynamic_list";// 動態列表
    public final static String PROJECT_DYNAMIC_COMMENT_LIST = GL_ADDRESS + "api/project/dynamic_listcomment_do";// 動態詳情評論
    public final static String PROJECT_DYNAMIC_COMMENT_SUBMIT = GL_ADDRESS + "api/project/dynamic_addcomment_do";// 動態詳情評論
    public final static String PROJECT_DYNAMIC_EDIT = GL_ADDRESS + "api/project/dynamic_update_do";// 動態詳情編輯
    public final static String PROJECT_DYNAMIC_DEL = GL_ADDRESS + "api/project/dynamic_delete_do";// 動態詳情刪除
    public final static String PROJECT_DYNAMIC_ADD = GL_ADDRESS + "api/project/dynamic_add_do";// 動態添加
    public final static String PROJECT_DETAIL = GL_ADDRESS + "api/project/project_info";// 動態添加

    /******************************* 项目END ***************************************/

    /*******************************
     * 日程Start
     ***************************************/
    public final static String CALENDAR_V3_LIST = GL_ADDRESS + "api/calendar_v3/index_do";
    public final static String CALENDAR_V3_DO = GL_ADDRESS + "api/calendar/calendar_add_do";
    public final static String CALENDAR_V3_FINISH = GL_ADDRESS + "api/calendar_v3/calendar_finish_do";
    public final static String CALENDAR_V3_DEL = GL_ADDRESS + "api/calendar_v3/calendar_delete_do";
    /*******************************
     * 日程END
     ***************************************/

    public final static String CALENDAR_LIST = GL_ADDRESS + "api/calendar/index_do";
    public final static String CALENDAR_DO = GL_ADDRESS + "api/calendar/calendar_add_do";
    public final static String CALENDAR_DEL = GL_ADDRESS + "api/calendar/calendar_delete_do";
    public final static String CALENDAR_FINISH = GL_ADDRESS + "api/calendar/calendar_finish_do";
    public final static String CALENDAR_DETAIL = GL_ADDRESS + "api/calendar/calendar_info_do";

    /*******************************
     * 会议室start
     ***************************************/
    public final static String MEETING_ROOM_LIST = GL_ADDRESS + "api/meeting_room/index";
    public final static String MEETING_ROOM_DETAIL = GL_ADDRESS + "api/meeting_room/room";
    public final static String MEETING_ROOM_ADD = GL_ADDRESS + "api/meeting_room/room_add_do";
    public final static String MEETING_ROOM_UPDATE = GL_ADDRESS + "api/meeting_room/room_update_do";
    public final static String MEETING_MY = GL_ADDRESS + "api/meeting_room/room_reserve";
    public final static String MEETING_ROOM_CANCEL = GL_ADDRESS + "api/meeting_room/room_reserve_delete_do";
    /******************************* 会议室END ***************************************/

    /*********************************
     * 新网
     ***********************************/
    // 公告
    public final static String NOTIC_GET_LIST = GL_ADDRESS + "api/announce/get_list";// 公告列表
    public final static String NOTIC_SET_READ = GL_ADDRESS + "api/announce/set_read_do";// 公告设为已读
    public final static String NOTIC_CREATE_NEW = GL_ADDRESS + "api/announce/create_do";// 创建公告
    public final static String NOTIC_UPLOAD_FILE = GL_ADDRESS + "api/announce/file_upload_do";// 上传文件
    public final static String NOTIC_DELETE_FILE = GL_ADDRESS + "api/announce/file_delete_do";// 删除文件
    public final static String NOTIC_DETAIL = GL_ADDRESS + "api/announce/get_content";// 公告详情
    public final static String NOTIC_DELETE = GL_ADDRESS + "api/announce/delete_do";// 删除公告
    public final static String NOTIC_MODIFY = GL_ADDRESS + "api/announce/edit_do";// 编辑公告
    public final static String NOTIC_READPEOPLE = GL_ADDRESS + "api/announce/get_readed_member";// 查看阅读人员

    public final static String IM_SEND_DO = GL_ADDRESS + "api/im/send_do";// 发送消息

    public final static String MEMBER_SEARCH = GL_ADDRESS + "api/member/search"; // 获取员工信息

    // 通讯录
    public final static String CONTACT_SEACH = GL_ADDRESS + "api/member/search";// 通讯录搜索(常用联系人，全部联系人等)
    public final static String CONTACT_SEACH_DEPART = GL_ADDRESS + "api/department/department_list";// 部门结构
    public final static String CONTACT_SEACH_BY_DEPARTID = GL_ADDRESS + "api/department/department_member";// 获取部门下的人员
    public final static String CONTACT_FANS = GL_ADDRESS + "api/ichat/fans";// 关注的人:0,1我关注的
    public final static String CONTACT_GET_FRIENDREQUEST = GL_ADDRESS + "api/friend/get_my_request";// 好友请求列表
    // 通过手机查找注册过的人、上传外部联系人
    public final static String UPLOAD_CONTACTS = GL_ADDRESS + "api/using_match/find_do";// 通过手机查找注册过的人
    public final static String GET_DIFERENT_COMPANYEMPLOYEE = GL_ADDRESS + "api/member/get_more_member_info";// 取不同公司员工数据
    public final static String ADD_FRIENDS_CONTACT = GL_ADDRESS + "api/friend/deal_request";// 处理加好友请求

    public final static String DELETE_FRIEND = GL_ADDRESS + "api/friend/delete_friend";// 删除好友请求
    public final static String SEND_FRIEND_REQUEST = GL_ADDRESS + "api/friend/send_request";// 发送好友请求
    public final static String GET_MEMBER_AND_DEPARTMENT = GL_ADDRESS + "api/member/get_member_and_department";// 获取公司内用户信息，和部门信息
    public final static String GET_FRIEND = GL_ADDRESS + "api/friend/get_my_friend";// 获取好友
    public final static String GET_GROUP_DATA = GL_ADDRESS + "api/group/group_data";// 群组列表基础数据集
    public final static String GET_GROUP = GL_ADDRESS + "api/group/group_list"; // 群组列表
    public final static String CREATE_GROUP = GL_ADDRESS + "api/group/group_add_do";// 创建群组
    public final static String GROUP_MEMBER_ADD = GL_ADDRESS + "api/group/group_member_add_do";// 群组添加成员
    public final static String GROUP_JOIN_LIST = GL_ADDRESS + "api/group/group_join_list";// 申请加入群组人员列表：
    public final static String GROUP_JOIN_AGREE_DO = GL_ADDRESS + "api/group/group_join_agree_do";// 管理员确认用户加入群组
    public final static String GROUP_SEARCH = GL_ADDRESS + "api/group/group_search_forid";// 按群组id查询
    public final static String GROUP_JOIN_DO = GL_ADDRESS + "api/group/group_join_do";// 申请加入群组：
    public final static String GROUP_EDIT = GL_ADDRESS + "api/group/group_edit_do";// 群组修改
    public final static String GROUP_DELETE_MEMBER = GL_ADDRESS + "api/group/group_member_delete_do";// 群组删除成
    public final static String GROUP_LOGOUT_DO = GL_ADDRESS + "api/group/group_logout_do";// 主动退出群
    public final static String GROUP_DELETE_DO = GL_ADDRESS + "api/group/group_delete_do"; // 群组删除

    // 标签_微聊
    public final static String DELETE_LABEL = GL_ADDRESS + "api/ichat/del_tag";// 删除标签
    public final static String ADD_OR_MODIFY_LABEL = GL_ADDRESS + "api/ichat/add_tag";// 添加或修改标签
    public final static String LIST_LABEL = GL_ADDRESS + "api/ichat/tags";// 标签列表
    public final static String LIST_SMALLTALK_FORWARDS = GL_ADDRESS + "api/ichat/ichat_forwards";// .获取单条微聊转发列表
    public final static String LIST_SMALLTALK_DIGS = GL_ADDRESS + "api/ichat/ichat_digs";// 获取单条微聊赞列表
    public final static String LIST_SMALLTALK_REPLYS = GL_ADDRESS + "api/ichat/ichat_replys";// 获取单条微聊评论列表
    public final static String LIST_SMALLTALKDETAIL_NUMS = GL_ADDRESS + "api/ichat/ichatdetailnums";// 获取单条微聊评论、转发数、浏览数、赞数等

    // 考试
    public final static String TEST_LIST = GL_ADDRESS + "api/exam/exam_list";// 考试列表
    public final static String TEST_DETAIL = GL_ADDRESS + "api/exam/exam_detail";// 考试详情
    public final static String TEST_JOIN = GL_ADDRESS + "api/exam/test";// 参加考试
    public final static String TEST_SUBMIT = GL_ADDRESS + "api/exam/submit";// 提交考试
    public final static String TEST_DRAW = GL_ADDRESS + "api/exam/prize_do";// 抽奖
    public final static String JUDGE_IS_EXIST_COMPANY = GL_ADDRESS + "api/register/check_company";// 判断公司是否存在
    public final static String SEND_CODE_REGISTER = GL_ADDRESS + "api/register/send_code_do";// 注册发送短信验证码
    public final static String CHECK_COMPANY_CREATE = GL_ADDRESS + "api/register/check_company_name";// 创建时判断公司是否存在
    public final static String CHECK_PHONE = GL_ADDRESS + "api/register/check_mobile_do";// 验证手机
    public final static String CHECK_PASSWORD = GL_ADDRESS + "api/register/check_password_do";// 验证密码
    public final static String SUBMIT_REGISTER = GL_ADDRESS + "api/register/register_do";// 验证密码
    public final static String SERVICE_TOP = GL_ADDRESS + "index/agreement";// 服务条款

    public final static String TEST_SHOW_ANWSER = GL_ADDRESS + "api/exam/show_answers";// 查看答案

    public final static String GET_HISTORY_MESSAGE_LIST = GL_ADDRESS + "api/message/message_list";// 获取用户消息列表接口
    // 我的日报、周报、月报：
    public final static String MY_DAILY_STATE = GL_ADDRESS + "/api/report/index";
    // 我收到的日报、周报、月报：
    public final static String RECIEVE_DAILY_STATE = GL_ADDRESS + "/api/report/report_my";
    // 日报、周报、月报详情
    public final static String WORK_STATE_DETAIL = GL_ADDRESS + "/api/report/report_detail";
    // 添加日报、周报、月报
    public final static String WORK_STATE_ADD = GL_ADDRESS + "/api/report/report_add";
    // 添加日报、周报、月报上传附件
    public final static String UPLOAD_ADJUNCT = GL_ADDRESS + "/api/report/upload";

    // 日报、周报、月报删除
    public final static String REPORT_DELETE = GL_ADDRESS + "/api/report/report_delete";
    // 标记已读
    public final static String MARKET_READ = GL_ADDRESS + "/api/report/report_set_read";
    // 写日报、周报、月报评论
    public final static String REPORT_COMMENT = GL_ADDRESS + "/api/report/report_comment";
    // 日报、周报、月报评论列表
    public final static String REPORT_COMMENT_LIST = GL_ADDRESS + "/api/report/report_comment_list";
    // 删除评论
    public final static String REPORT_COMMENT_DELETE = GL_ADDRESS + "/api/report/report_comment_delete";

    // 公司信息
    public final static String COMPANY_INFOMATION = GL_ADDRESS + "api/company/company_info";
    public final static String INFOMATION_LIST = GL_ADDRESS + "api/member_join/index";// 邀请列表
    public final static String INFOMATION_DETAIL = GL_ADDRESS + "api/member_join/member_info";// 邀请详情
    public final static String INFOMATION_REFUSED = GL_ADDRESS + "api/member_join/refuse_do";// 邀请拒绝
    public final static String INFOMATION_AGREED = GL_ADDRESS + "api/member_join/agree_do";// 邀请同意
    public final static String INFOMATION_SEND_SMS = GL_ADDRESS + "api/member_invite/invite_member_do";// 短信邀请
    public final static String INFOMATION_SEND_EMAIL = GL_ADDRESS + "api/member_invite/invite_member_by_email";// 邮箱邀请
    public final static String INFOMATION_SEND_WEIXIN = GL_ADDRESS + "api/member_invite/get_share_url";// 微信邀请（获取短连接）
    // 人事档案用户列表
    public final static String PERSON_MANANGE_MEMBERS_LIST = GL_ADDRESS + "api/hr/member_list";
    // 人事档案 - 用户详情
    public final static String PERSON_MANANGE_DETAILS = GL_ADDRESS + "api/hr/member_info";
    // 职位工资变动 变动类型 1:岗位，2:工资 3:社保 4:公积金 5:补贴
    public final static String PERSON_CHANGE = GL_ADDRESS + "api/hr/change_record";

    /**
     * 快速体验接口
     */
    // 快速体验 - 发送验证码
    public final static String EXPERIENCE_SEND_CODE = GL_ADDRESS + "/api/trial/send_code";
    // 快速体验-获取token
    public final static String EXPERIENCE_GET_TOKEN = GL_ADDRESS + "/api/trial/get_token";

    // 费用申请

    public final static String COST_APPLY_TO_ME = GL_ADDRESS + "api/finance/action/apply2me";// 员工可申请流程
    public static final String GET_COST_DTAIL_TYPE = GL_ADDRESS + "api/finance/action/get_category";// 获取分类
    public static final String GET_HAS_APPLY_COST = GL_ADDRESS + "api/finance/action/mine";// 员工已申请的报销，预支
    public static final String SEND_APPLY_TO_SERVE = GL_ADDRESS + "api/finance/action/create_do";// 员工发起申请，保存进草稿箱
    public static final String DELETE_APPLY = GL_ADDRESS + "api/finance/action/delete_do";// 员工删除自己发起的流程
    public static final String SEARCH_FLOW_DETAIL = GL_ADDRESS + "api/finance/action/info";// 查看流程具体信息
    public static final String REBACK_APPLY = GL_ADDRESS + "api/finance/action/back_do";// 员工撤销申请
    public static final String SEARCH_MY_APPROVE = GL_ADDRESS + "api/finance/audit/mine";// 我的待审/已审列表/
    public static final String PUBLISH_FROM_DRAFT = GL_ADDRESS + "api/finance/action/publish_do";// 发布流程（从草稿箱里发布）/
    public static final String AUDIT_FLOW_ACTIOIN = GL_ADDRESS + "api/finance/audit/audit_do";// 审核员工的流程申请
    public static final String UPLOAD_IMAGE_FILE = GL_ADDRESS + "api/finance/action/file_upload_do";// 上传文件
    public static final String SEND_PREMOTE_INFO = GL_ADDRESS + "api/finance/action/send_remind";// 发送审批提醒

}
