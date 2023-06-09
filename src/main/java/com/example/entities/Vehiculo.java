package com.example.entities;
import com.example.entities.enums.Color;
import com.example.entities.enums.TipoVehiculo;
import com.example.entities.enums.Combustible;
import jakarta.persistence.*;
import lombok.*;

@Getter      //Métodos Getter
@Setter      //Métodos Setter
@NoArgsConstructor  //Constructor sin párametros
@AllArgsConstructor // Constructor con párametros
@ToString  // Método ToString
@Builder  // Permite objetos dinámicos con concatenación
@Entity
public class Vehiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private String marca;
    private TipoVehiculo tipo;
    private String matricula;
    private Color color;
    private Combustible combustible;
    private Integer year;

}