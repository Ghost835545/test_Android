package com.example.ivan.loginapp.util;

public class Variables {
    private static String USER_LOGIN;
    private static String FIO;
    private static String TEST_NAME;
    private static Float MARK;
    private static Integer ID_TEST;
    private static Integer ID_RESULT;
    private static Integer ID_DIRECTION;
    private static Integer ID_USER;
    private static Boolean FLAG_RESULTS;
    private static Boolean CLOCK_END;
    private static Integer ID_ROLE;
    private static Boolean RIGHT_ANSWERS;

    public static Boolean getRightAnswers() {
        return RIGHT_ANSWERS;
    }

    public static void setRightAnswers(Boolean rightAnswers) {
        RIGHT_ANSWERS = rightAnswers;
    }

    public static Integer getIdRole() {
        return ID_ROLE;
    }

    public static void setIdRole(Integer idRole) {
        ID_ROLE = idRole;
    }

    public static Boolean getClockEnd() {
        return CLOCK_END;
    }

    public static void setClockEnd(Boolean clockEnd) {
        CLOCK_END = clockEnd;
    }

    public static Boolean getFlagResults() {
        return FLAG_RESULTS;
    }

    public static void setFlagResults(Boolean flagResults) {
        FLAG_RESULTS = flagResults;
    }

    public static Integer getIdUser() {
        return ID_USER;
    }

    public static void setIdUser(Integer idUser) {
        ID_USER = idUser;
    }

    public static Integer getIdDirection() {
        return ID_DIRECTION;
    }

    public static void setIdDirection(Integer idDirection) {
        ID_DIRECTION = idDirection;
    }

    public static Integer getIdResult() {
        return ID_RESULT;
    }

    public static void setIdResult(Integer idResult) {
        ID_RESULT = idResult;
    }

    public static Float getMARK() {
        return MARK;
    }

    public static void setMARK(Float MARK) {
        Variables.MARK = MARK;
    }

    public static Integer getIdTest() {
        return ID_TEST;
    }

    public static void setIdTest(Integer idTest) {
        ID_TEST = idTest;
    }

    public static String getFIO() {
        return FIO;
    }

    public static void setFIO(String FIO) {
        Variables.FIO = FIO;
    }

    public static String getTestName() {
        return TEST_NAME;
    }

    public static void setTestName(String testName) {
        TEST_NAME = testName;
    }

    public static String getUserLogin() {
        return USER_LOGIN;
    }

    public static void setUserLogin(String userLogin) {
        USER_LOGIN = userLogin;
    }


}
