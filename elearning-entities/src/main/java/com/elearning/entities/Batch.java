package com.elearning.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String info;
    private Long timeSlotOfDay;

    @JsonProperty
    @Column(unique = true)
    private String externalId;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinTable(name = "BatchToStudent",
            joinColumns = {@JoinColumn(name = "batch_id")}, inverseJoinColumns = {@JoinColumn(name = "student_id")})
    @JsonManagedReference
    private List<Student> enrolledStudents;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_id")
    @JsonBackReference
    private Instructor instructor;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "associatedBatch")
    private List<Assignment> assignments;
}
