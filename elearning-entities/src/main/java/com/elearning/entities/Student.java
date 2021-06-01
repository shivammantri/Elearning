package com.elearning.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.common.collect.Lists;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.List;

@Data
@Entity
public class Student extends Person{
    private String standard;
    private String location;
    private Double walletBalance;


    @ManyToMany(mappedBy = "enrolledStudents",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Batch> enrolledBatches = Lists.newArrayList();
}
