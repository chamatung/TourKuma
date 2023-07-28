package com.tour.kuma.domain.nation.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.tour.kuma.domain.nation.entity.Nation;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class NationRegistDTO {


    @JsonProperty("nation_code")
    private String nationCode;
    @JsonProperty("nation_name")
    private String nationName;
    @JsonProperty("eng_nation_name")
    private String engNationName;

    public Nation toNation() {
        return Nation.builder()
                .twoNationCode(this.nationCode)
                .korName(this.nationName)
                .engName(this.engNationName)
                .build();
    }
}
