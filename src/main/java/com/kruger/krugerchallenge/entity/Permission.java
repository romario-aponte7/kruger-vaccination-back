package com.kruger.krugerchallenge.entity;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Table(name ="permissions")
@Getter
@Setter
@EqualsAndHashCode(of = "permissionId")
@ToString(of = "permissionId")
@Builder
@AllArgsConstructor
public class Permission {

    @Id
    @GeneratedValue
    private UUID permissionId;
    @Column(unique = true)
    @NotNull
    private String name;
    @NotNull
    private String domainAction;
    @ManyToMany(mappedBy = "permissions", cascade = {CascadeType.ALL})
    @Builder.Default
    private Set<Role> roles= new HashSet<>();
}
