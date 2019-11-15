package Gym_keeper.Entity;

import javax.persistence.*;

@Entity
@Table(name="user_data")
public class User_data {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_data_id")

    private int user_data_id;
    private int id;
    private float weight;
    private int age;


    public User_data(int user_data_id, int id, float weight, int age) {
        this.user_data_id = user_data_id;
        this.id = id;
        this.weight = weight;
        this.age = age;
    }

    public int getUser_data_id() {
        return user_data_id;
    }

    public void setUser_data_id(int user_data_id) {
        this.user_data_id = user_data_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
