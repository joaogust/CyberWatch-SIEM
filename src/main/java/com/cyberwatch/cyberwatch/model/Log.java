package com.cyberwatch.cyberwatch.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

// @Entity: Diz ao Spring/Hibernate que esta classe representa uma tabela no banco de dados.
@Entity
//@Table(...): Define o nome da tabela como logs e cria índices de performance. Em segurança da informação, indexar as colunas timestamp, ip e username é indispensável, pois seu motor de regras fará milhares de buscas cruzando exatamente esses três dados em tempo real.
@Table(name = "logs", indexes = {
        @Index(name = "idx_logs_timestamp", columnList = "timestamp"),
        @Index(name = "idx_logs_ip", columnList = "ip"),
        @Index(name = "idx_logs_username", columnList = "username")
})
//@Data, @NoArgsConstructor, @AllArgsConstructor, @Builder: Anotações do Lombok que geram automaticamente os Getters, Setters, Construtores e o padrão Builder (útil para criar objetos de log nos testes de forma fluida).
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false, length = 45) // Tamanho 45 suporta IPv4 e IPv6 perfeitamente
    private String ip;

    @Enumerated(EnumType.STRING) // Salva o nome do texto no banco (ex: "LOGIN_FAILED") em vez do número do índice
    @Column(name = "event_type", nullable = false)
    private EventType eventType;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    // Ciclo de vida do JPA: se nenhum timestamp for enviado, preenche automaticamente com o horário atual do servidor
    // @PrePersist: Garante o princípio do monitoramento contínuo: se o evento de log coletado não vier com uma data/hora explícita, a aplicação carimba o momento exato em que ele entrou no SIEM.
    @PrePersist
    protected void onCreate() {
        if (this.timestamp == null) {
            this.timestamp = LocalDateTime.now();
        }
    }
}