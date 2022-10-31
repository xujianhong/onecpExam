package org.daomingedu.onecpexam.http;

public class URLS {



    public static void setBASEURL(String BASEURL) {
        URLS.BASEURL = BASEURL;
    }

    public static String generateUrl(String url) {
        return BASEURL + url;
    }

    //基本请求地址
    public static String BASEURL = "http://onecp.4hand.com.cn/onecp/";
//    public static String BASEURL = "http://114.117.194.109/";



    //登陆地址
    public static final String LOGIN = "api/exams/teacherLogin.do";

    public static final String SAVE_TEST_RESULT ="api/exams/saveTestResult.do";

    //获取评语
    public static final String GET_COMMENT = "api/exams/queryCommentList.do";

    //获取分项评语
    public static final String QUERY_EXAMS_ITEM = "api/exams/queryExamsItem.do";

    public static final String TEACHER_TEST_SCHOOL_LIST = "api/exams/teacherTestSchoolList";

    //教师考试列表
    public static final String TEACHER_TEST_CLASS_LIST = "api/exams/teacherTestSchoolClassesList.do";

    public static final String TEST_ROOM_STUDENT_INFO_LIST = "api/exams/testRoomStudentInfoList.do";

    public static final String QUERY_STUDENT_TEST_INFO = "api/exams/queryStudentTestInfo.do";

    public static final String SEARCHTEACHERTESTLIST = "api/exams/searchTeacherTestList.do";


    public static final String GET_VERSION_INFO = "api/exams/getVersionInfo.do";

}
