package com.example.iq_test.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Test_Result")
@Getter
@Setter
@NoArgsConstructor
public class TestResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id_result")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "Id_test")
    private Test test;

    @Column(name = "Count_points")
    private String points;

}
