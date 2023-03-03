package com.example.iq_test.models;

import lombok.*;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Test")
@Getter
@Setter
@NoArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_test")
    private  Long id;

    @Column(name = "Test_name")
    private String testName;

    @OneToMany(mappedBy = "test", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Question> questions;

    @ManyToOne
    @JoinColumn(name = "Id_test_section", nullable = false)
    private SectionTest sectionTest;

    @OneToMany(mappedBy = "test", fetch = FetchType.EAGER)
    private Set<TestResult> testResults;

    public Test(SectionTest sectionTest,String testName)
    {
        this.sectionTest = sectionTest;
        this.testName = testName;
    }

}
