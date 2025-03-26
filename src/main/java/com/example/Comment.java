package com.example;

// Record (recurso do Java 16+) que cria automaticamente métodos como equals(), hashCode(), toString() e getters
// Representa a estrutura de um comentário no sistema
public record Comment(
        // Identificador único do comentário
        Long id,
        // Identificador do post ao qual o comentário pertence
        Long postId,
        // O texto do comentário
        String text
) {
}