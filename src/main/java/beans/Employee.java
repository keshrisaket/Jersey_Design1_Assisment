package beans;


import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Employee {
    private String name;
    private float age;
    private double sal;
    private String address;

    public Employee()
    {
    }

    public Employee(String name, float age, double sal, String address) {
        this.name = name;
        this.age = age;
        this.sal = sal;
        this.address = address;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAge() {
        return age;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public double getSal() {
        return sal;
    }

    public void setSal(double sal) {
        this.sal = sal;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sal=" + sal +
                ", address='" + address + '\'' +
                '}';
    }


}
