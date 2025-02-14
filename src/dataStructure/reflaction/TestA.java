package src.dataStructure.reflaction;

public class TestA {
    private String name;
    private TestB testB;

    public TestA() {
    }

    public TestA(String name, TestB testB) {
        this.name = name;
        this.testB = testB;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TestB getTestB() {
        return testB;
    }

    public void setTestB(TestB testB) {
        this.testB = testB;
    }
}
