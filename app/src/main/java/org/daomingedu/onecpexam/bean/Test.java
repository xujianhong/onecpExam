package org.daomingedu.onecpexam.bean;

import java.io.Serializable;

/**
 * Created by qin.
 */

public class Test implements Serializable {


    private String testName;

    private String testId;

    public String getTestName() {
        return testName;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }
}
