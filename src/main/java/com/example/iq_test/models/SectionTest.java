package com.example.iq_test.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Test_section")
@Getter
@Setter
@NoArgsConstructor
public class SectionTest {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_test_section")
    private Long id;

    @Column(name = "Test_section_title")
    private String testSectionTitle;

    @OneToMany(mappedBy = "sectionTest", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Test> Tests;

    public SectionTest(String testSectionTitle) {
        this.testSectionTitle = testSectionTitle;
    }


}
