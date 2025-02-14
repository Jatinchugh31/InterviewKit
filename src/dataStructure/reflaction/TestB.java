package src.dataStructure.reflaction;

import java.util.List;

public class TestB {
    private String myName;
    private List<TestC> testCList;

    public TestB() {
    }

    public TestB(String myName, List<TestC> testCList) {
        this.myName = myName;
        this.testCList = testCList;
    }

    public String getMyName() {
        return myName;
    }

    public void setMyName(String myName) {
        this.myName = myName;
    }

    public List<TestC> getTestCList() {
        return testCList;
    }

    public void setTestCList(List<TestC> testCList) {
        this.testCList = testCList;
    }
}
