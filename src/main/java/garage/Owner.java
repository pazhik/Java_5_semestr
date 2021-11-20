package garage;

import java.util.Objects;

public class Owner {
    private final int id;
    private final String name;
    private final String lastName;
    private final int age;


    public Owner(int id, String name, String lastName, int age) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return id == owner.id &&
                age == owner.age &&
                name.equals(owner.name) &&
                lastName.equals(owner.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, age);
    }
}
