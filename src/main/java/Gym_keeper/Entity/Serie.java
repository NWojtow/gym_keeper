package Gym_keeper.Entity;

import javax.persistence.*;

@Entity
@Table(name = "serie")
public class Serie {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "rep_id")

    private int rep_id;
    private int reps;
    private float weight;
    private int exercise_id;

    public Serie() {
    }

    public Serie(int rep_id, int reps, float weight, int exercise_id) {
        this.rep_id = rep_id;
        this.reps = reps;
        this.weight = weight;
        this.exercise_id = exercise_id;
    }

    public int getRep_id() {
        return rep_id;
    }

    public void setRep_id(int rep_id) {
        this.rep_id = rep_id;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }
}
