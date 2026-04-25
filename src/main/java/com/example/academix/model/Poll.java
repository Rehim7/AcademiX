package com.example.academix.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

@Entity
@Data
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long groupId;
    private String question;

    @OneToMany(mappedBy = "poll", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PollOption> options;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "poll_voted_users", joinColumns = @JoinColumn(name = "poll_id"))
    @Column(name = "user_id")
    private Set<Long> votedUsers = new HashSet<>();
}
