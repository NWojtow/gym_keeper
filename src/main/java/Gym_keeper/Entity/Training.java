package Gym_keeper.Entity;

import javax.persistence.*;

@Entity
@Table(name="training")
public class Training {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name="training_id")

    private int training_id;
    private int id;
    private float weight;

    public Training(int training_id, int id, float weight) {
        this.training_id = training_id;
        this.id = id;
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
