package com.example;

// Record (recurso do Java 16+) que cria automaticamente métodos como equals(), hashCode(), toString() e getters
// Representa a estrutura de um post no sistema
public record Post (
        // Identificador único do post
        Long id,
        // O texto do post
        String text
){

}
