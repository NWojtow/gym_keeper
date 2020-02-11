package Gym_keeper.entitiy;

import javax.persistence.*;

@Entity
@Table(name="exercise")
public class Exercise {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "exercise_id")

    private int exercise_id;
    private String exercise_name;
    private int training_id;

    public Exercise() {
    }

    public Exercise(int exercise_id, String exercise_name, int training_id) {
        this.exercise_id = exercise_id;
        this.exercise_name = exercise_name;
        this.training_id = training_id;
    }

    public int getExercise_id() {
        return exercise_id;
    }

    public void setExercise_id(int exercise_id) {
        this.exercise_id = exercise_id;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

    public int getTraining_id() {
        return training_id;
    }

    public void setTraining_id(int training_id) {
        this.training_id = training_id;
    }
}
