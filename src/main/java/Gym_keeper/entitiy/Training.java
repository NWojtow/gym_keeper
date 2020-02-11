package Gym_keeper.entitiy;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="training")
public class Training {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name="training_id")

    private int training_id;
    private int id;
    private Date date;
    private float weight;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Training() {
    }

    public Training(int training_id, int id, Date date, float weight) {
        this.training_id = training_id;
        this.id = id;
        this.date = date;
        this.weight = weight;
    }

    public int getTraining_id() {
        return training_id;
    }

    public void setTraining_id(int training_id) {
        this.training_id = training_id;
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
}
