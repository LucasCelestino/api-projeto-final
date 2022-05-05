package com.hardware.api.Model;

// import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
// import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "parts")
public class Part extends AbstractEntity
{
    @Column(name = "name", length = 75)
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "url", length = 200)
    private String url;

    @ManyToOne
    @JoinColumn(name = "id_brand", nullable = false)
    private Brand brand;
}
