package org.daomingedu.onecpexam.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jianhongxu on 3/26/21
 * Description
 */
public class ExamItem implements Serializable {
    String name;
    int orderNo;

    List<ExamItemComment> examsItemComment;

    public List<ExamItemComment> getExamsItemComment() {
        return examsItemComment;
    }

    public void setExamsItemComment(List<ExamItemComment> examsItemComment) {
        this.examsItemComment = examsItemComment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }


}


