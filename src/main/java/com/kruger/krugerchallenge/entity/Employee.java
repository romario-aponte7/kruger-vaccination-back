package com.kruger.krugerchallenge.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "employees")
@Getter
@Setter
@EqualsAndHashCode(of = "employeeId")
@ToString(of = "employeeId")
@Builder
@AllArgsConstructor
public class Employee {
    @Id
    @GeneratedValue
    @Column(name = "employee_id")
    private UUID employeeId;

    @NotNull
    @Column(unique = true)
    private String dni;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String mail;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dateOfBirth;

    private String address;

    private String phone;

    private String status;

    @OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
    @Builder.Default
    private Set<EmployeeVaccine> employeeVaccines = new HashSet<>();


    private Boolean active;

}
