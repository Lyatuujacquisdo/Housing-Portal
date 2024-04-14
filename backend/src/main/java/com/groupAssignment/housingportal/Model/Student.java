package com.groupAssignment.housingportal.Model;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Setter
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="students")
public class Student implements UserDetails {
    @Id
    @Column(name="student_id", length=45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="regNo", length=20)
    private String registrationNo;

    @Column(name="email",unique = true, length=50)
    private String email;

    @Column(name="first_name", length=20)
    private String firstName;

    @Column(name="last_name", length=20)
    private String lastName;

    @Column(name="gender", length=8)
    private String gender;

    @Column(name="password", length=200)
    private String password;

    @Column(name="roomStatus", length=10)
    private RoomStatus roomStatus;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(targetEntity = Student.class, cascade = CascadeType.ALL)
    @JoinColumn(name="selectedBy", referencedColumnName = "student_id")
    private List<Student> students;

    private String roomID;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //should return a list of roles
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return registrationNo;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
