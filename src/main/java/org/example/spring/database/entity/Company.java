package org.example.spring.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "company")
public class Company implements BaseEntity<Integer>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "company_locales", joinColumns = @JoinColumn(name = "company_id"))
    @MapKeyColumn(name = "lang")
    @Column(name = "description")
    private Map<String, String> locales = new HashMap<>();
}
