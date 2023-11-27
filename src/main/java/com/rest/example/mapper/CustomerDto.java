package com.rest.example.mapper;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerDto implements Serializable {

  @NotNull
  private Integer ref;
  
  @NotEmpty
  private String name;

  @NotEmpty
  private String addressLineOne;

  private String addressLineTwo;
  private String town;
  private String county;
  private String country;

  @NotEmpty
  private String postcode;

}
