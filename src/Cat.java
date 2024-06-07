import java.util.Objects;

public class Cat {

    private String name;
    private int age;
    private String furColor;

    public Cat(String name, int age, String furColor) {
        this.name = name;
        this.age = age;
        this.furColor = furColor;
    }

    @Override
    public boolean equals(Object other){
        if(other == this) return true;
        if(other.getClass() != this.getClass() || other == null) return false;
        Cat otherCat = (Cat) other;
        return name.equals(otherCat.name)
                && age == otherCat.age
                && furColor.equals(otherCat.furColor);
    }

    @Override
    public int hashCode(){
        return Objects.hash(name, age, furColor);
    }

    @Override
    public String toString() {
        return "Cat: Name: " + name + ", Age: " + age + ", Fur color: " +furColor;
    }
}
