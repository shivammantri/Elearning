package com.elearning.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Instructor extends Person{
    private String qualification;
    private String experience;
    private Integer feesPerHour;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "instructor")
    private List<Batch> allocatedBatches;
}
