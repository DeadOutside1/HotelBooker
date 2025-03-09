package com.hotelbooker.model;

import jakarta.persistence.*;
import lombok.*;

import com.hotelbooker.model.User;

import java.awt.*;

@Entity
@Table(name = "rooms")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor


public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roomId;

    @ManyToOne
    @JoinColumn(name = "hotel_id", nullable = false)
    private Hotel hotel;

    @Column (nullable = false)
    String roomNumber;

    @Column(nullable = false)
    private int floor;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private double price;

    private boolean isAvailable;
}
