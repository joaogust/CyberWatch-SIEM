package com.cyberwatch.cyberwatch.repository;

import com.cyberwatch.cyberwatch.model.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository: indica ao Spring que esta classe é um componente de acesso a dados. O Spring vai geri-la e permitir que a injete noutras classes (como no LogService na Fase 5).
@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    // Por agora, não precisa de adicionar nenhum metodo aqui dentro.
    // O JpaRepository já traz o .save() e o .findAll() prontos a usar.
}

/*
Um vislumbre do futuro (Fase 9 — Motor de Regras)
A grande vantagem de usar o Spring Data JPA num projeto de SIEM é que, mais tarde, quando precisar de criar as regras de correlação de eventos
(como detetar Brute Force: 5 falhas do mesmo IP no último minuto), não precisará de escrever queries SQL complexas.
Poderá simplesmente declarar métodos baseados no nome (Derived Queries) diretamente nesta interface.

Por exemplo, na Fase 9, poderá adicionar um metodo assim:
------------------------------------------------------------------------------------------------------------
    // O Spring transforma isto automaticamente em SQL de contagem com filtros de tempo e IP
    long countByIpAndEventTypeAndTimestampAfter(String ip, EventType eventType, LocalDateTime timestamp);
------------------------------------------------------------------------------------------------------------
 */