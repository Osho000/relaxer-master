package com.example.relaxer.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(schema = "users_schema", name = "t_users")
public class Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false, name = "c_user")
    String username;
    @Column(nullable = false, name = "c_password")
    String password;

    @ManyToOne
    @JoinColumn(name = "c_role_id")
    Role role;

    @OneToOne
    @JoinColumn(name = "c_user_id", referencedColumnName = "id")
    User user;
}