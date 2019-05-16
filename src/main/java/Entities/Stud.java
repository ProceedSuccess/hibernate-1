package Entities;

import javax.persistence.*;

@Entity
@Table(name = "students_new")
public class Stud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "score")
    private double score;

    public int getId(){return id;}
    public String getName(){return name;}
    public double getScore() {return score;}
}
