public class Student {
    private String name;
    private int id;
    private String major;

    public Student(String name, int id, String major) {
        this.name = name;
        this.id = id;
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", major='" + major + '\'' +
                '}';
    }
}
