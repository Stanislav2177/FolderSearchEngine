package com.string.search.via.web.String.search.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "requests")
public class RequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "client_ip")
    private String ip;

    @Column(name = "method_type")
    private String methodType;

    @Column(name = "location")
    private String location;

    @Column(name = "text")
    private String text;

    @Column(name = "type")
    private String type;

    @Column(name = "time")
    private LocalDateTime localDateTime;
}
