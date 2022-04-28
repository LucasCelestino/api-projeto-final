package com.hardware.api.Model;

// import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
// import javax.persistence.OneToMany;
import javax.persistence.Table;
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

    // @OneToMany
    // private List<Parts> parts; 

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
