package com.example.iq_test.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "child_groups")
@Getter
@Setter
@NoArgsConstructor
public class ChildGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id_group")
    private  long id;

    @Column(name = "Group_name")
    private String groupName;

    @ManyToMany(mappedBy = "childGroups", fetch = FetchType.LAZY)
    private List<User> children;

    @ManyToOne
    @JoinColumn(name = "Id_teacher")
    private User teacher;

    public ChildGroup(String groupName)
    {
        this.groupName = groupName;
    }

}
