import java.util.Objects;

public class Dog {
    private String name;
    private int age;
    private String furColor;

    public Dog(String name, int age, String furColor) {
        this.name = name;
        this.age = age;
        this.furColor = furColor;
    }

    @Override
    public boolean equals(Object other){
        return true;
    }

    @Override
    public int hashCode(){
        return 0;
    }

    @Override
    public String toString() {
        return "Dog: Name: " + name + ", Age: " + age + ", Fur color: " +furColor;
    }
}
