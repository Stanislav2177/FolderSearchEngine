package com.string.search.via.web.String.search.dto;

import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JsonResponse {
    private String location;
    private List<Integer> idx;
}
