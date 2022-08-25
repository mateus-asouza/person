package com.ms.personApi.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DynamicUpdate
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "SECURITY_GROUP")
public class SecurityGroup implements GrantedAuthority {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ManyToMany(mappedBy= "securityGroups",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    @Override
    public String getAuthority() {
        return name;
    }
}
