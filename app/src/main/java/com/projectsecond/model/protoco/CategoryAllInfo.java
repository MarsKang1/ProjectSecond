package com.projectsecond.model.protoco;

/**
 * Created by Administrator on 2017/4/5.(接口返回数据)
 * {
 * "msg": "success",
 * "result": {
 * "categoryInfo": {
 * "ctgId": "0010001001",
 * "name": "全部菜谱"
 * },
 * "childs": [
 * {
 * "categoryInfo": {
 * "ctgId": "0010001006",
 * "name": "按功能选择菜谱",
 * "parentId": "0010001001"
 * },
 * "childs": [
 * {
 * "categoryInfo": {
 * "ctgId": "0010001056",
 * "name": "减肥",
 * "parentId": "0010001006"
 * }
 * },
 * {
 * "categoryInfo": {
 * "ctgId": "0010001065",
 * "name": "润肺",
 * "parentId": "0010001006"
 * }
 * }
 * ]
 * }
 * ]
 * },
 * "retCode": "200"
 * }
 */
public class CategoryAllInfo {

    private String msg;//返回信息 成功为success
    private String retCode;//网络状态码
    private CategoryGroup result;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public CategoryGroup getResult() {
        return result;
    }

    public void setResult(CategoryGroup result) {
        this.result = result;
    }
}
