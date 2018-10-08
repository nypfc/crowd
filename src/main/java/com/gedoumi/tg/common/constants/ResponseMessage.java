package com.gedoumi.tg.common.constants;

/**
 * 返回信息常量
 *
 * @author Minced
 */
public interface ResponseMessage {

    String SUCCESS = "请求成功";

    String SERVER_ERROR = "服务器错误";

    String NO_LOGIN = "用户未登录";

    String ALREADY_POINTED = "今日已经助力";

    String NOT_ENOUGH_POINT = "积分不足";

    String ALREADY_AWARDED = "不能重复抽奖";

    String AWARD_NOT_EXIST = "奖品不存在";

}
