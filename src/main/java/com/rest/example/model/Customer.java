package com.rest.example.model;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Customer")
public class Customer {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;;

  @Column
  private String name;

  @Column
  private String addressLineOne;

  @Column
  private String addressLineTwo;

  @Column
  private String town;

  @Column
  private String county;

  @Column
  private String country;

  @Column
  private String postcode;

}
