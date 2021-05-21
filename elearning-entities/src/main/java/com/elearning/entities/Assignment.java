package com.elearning.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String createdBy;

    @JsonProperty
    @Column(unique = true)
    private String externalId;

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "batch_id")
    @JsonBackReference
    private Batch associatedBatch;

    private String url;
}
