
import java.util.*;

interface Dao<T, K> {
    void insert(T obj);
    T findById(K id);
    List<T> findAll();
    void update(T obj);
    void delete(K id);
}

class Student {
    int id;
    String name;
    String department;

    Student(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
    }

    public String toString() {
        return id + " " + name + " " + department;
    }
}

class StudentDao implements Dao<Student, Integer> {

    List<Student> students = new ArrayList<>();

    public void insert(Student s) {
        students.add(s);
        System.out.println("Student inserted successfully");
    }

    public Student findById(Integer id) {
        for (Student s : students) {
            if (s.id == id)
                return s;
        }
        return null;
    }

    public List<Student> findAll() {
        return students;
    }

    public void update(Student s) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).id == s.id) {
                students.set(i, s);
                System.out.println("Student updated");
                return;
            }
        }
        System.out.println("Student not found");
    }

    public void delete(Integer id) {
        students.removeIf(s -> s.id == id);
        System.out.println("Student deleted");
    }
}

public class Main {
    public static void main(String[] args) {

        StudentDao dao = new StudentDao();

        Student s1 = new Student(101, "Bharath", "CSE");
        Student s2 = new Student(102, "Rahul", "ECE");

        dao.insert(s1);
        dao.insert(s2);

        System.out.println("Find Student:");
        System.out.println(dao.findById(101));

        System.out.println("All Students:");
        for (Student s : dao.findAll()) {
            System.out.println(s);
        }

        dao.update(new Student(101, "Bharath Reddy", "CSE"));

        dao.delete(102);
    }
}
