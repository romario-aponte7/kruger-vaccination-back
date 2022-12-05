package com.kruger.krugerchallenge.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name = "employees_vaccines")
@Getter
@Setter
@EqualsAndHashCode(of = "employeeVaccineId")
@ToString(of = "employeeVaccineId")
@Builder
@AllArgsConstructor
public class EmployeeVaccine {
    @Id
    @GeneratedValue
    @Column(name = "employee_vaccine_id")
    private UUID employeeVaccineId;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;

    private String dose;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @NotNull
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "vaccine_id")
    @NotNull
    private Vaccine vaccine;


}
