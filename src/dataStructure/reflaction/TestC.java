package src.dataStructure.reflaction;

import java.util.List;

public class TestC {
    private String fieldA;
    private List<TestD> testDList;

    public TestC() {
    }

    public TestC(String fieldA, List<TestD> testDList) {
        this.fieldA = fieldA;
        this.testDList = testDList;
    }

    public String getFieldA() {
        return fieldA;
    }

    public void setFieldA(String fieldA) {
        this.fieldA = fieldA;
    }

    public List<TestD> getTestDList() {
        return testDList;
    }

    public void setTestDList(List<TestD> testDList) {
        this.testDList = testDList;
    }
}
