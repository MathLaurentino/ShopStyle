package com.shopstyle.ms_catalog.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class  CategoryReqDto {

    @NotBlank
    private String name;

    @NotBlank
    @Pattern(regexp = "true|false")
    private String active;

    @JsonProperty("parentId")
    @Min(1)
    private Long parentCategory;

}
