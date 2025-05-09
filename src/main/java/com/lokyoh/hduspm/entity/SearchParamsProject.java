package com.lokyoh.hduspm.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchParamsProject {
    List<Param> creators;
    List<Param> classes;
    List<Param> teachers;
}
