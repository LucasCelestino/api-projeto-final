package com.hardware.api.Model;

import java.util.List;

import javax.persistence.CascadeType;

// import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
// import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "brands")
public class Brand extends AbstractEntity
{
    @Column(name = "name", length = 75)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "brand", cascade = CascadeType.ALL)
    private List<Part> parts;
}
