package Gym_keeper.entitiy;

import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name="training")
public class Training {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name="training_id")

    @Expose
    private int training_id;
    @Expose
    private Date date;
    @Expose
    private float weight;

    @ManyToOne
    @JoinColumn(name ="id")
    private DaoUser user;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Training() {
    }

    public Training(Date date, float weight) {
        this.date = date;
        this.weight = weight;
    }

    public int getTraining_id() {
        return training_id;
    }

    public void setTraining_id(int training_id) {
        this.training_id = training_id;
    }

    public void setUser(DaoUser user) {
        this.user = user;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }
}
