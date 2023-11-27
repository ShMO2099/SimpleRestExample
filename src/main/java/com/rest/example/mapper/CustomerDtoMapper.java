package com.rest.example.mapper;

import com.rest.example.model.Customer;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


public class CustomerDtoMapper {


  public static CustomerDto toDto(Customer customer){
    return CustomerDto.builder()
        .ref(customer.getId())
        .name(customer.getName())
        .addressLineOne(customer.getAddressLineOne())
        .addressLineTwo(customer.getAddressLineTwo())
        .town(customer.getTown())
        .county(customer.getCounty())
        .country(customer.getCountry())
        .postcode(customer.getPostcode())
        .build();
  }

  public static Customer toEntity(CustomerDto dto){
    return Customer.builder()
        .id(dto.getRef())
        .name(dto.getName())
        .addressLineOne(dto.getAddressLineOne())
        .addressLineTwo(dto.getAddressLineTwo())
        .town(dto.getTown())
        .county(dto.getCounty())
        .country(dto.getCountry())
        .postcode(dto.getPostcode())
        .build();
  }

}
