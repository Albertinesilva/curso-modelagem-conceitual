package com.albertsilva.cursomc.config;

import java.util.TimeZone;

import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

/**
 * Classe de configuração responsável por definir o fuso horário padrão
 * da aplicação.
 *
 * <p>
 * Ao iniciar o contexto do Spring, esta configuração estabelece o timezone
 * padrão da JVM para {@code America/Sao_Paulo}. Isso garante consistência
 * no tratamento de datas e horários em toda a aplicação, incluindo:
 * </p>
 *
 * <ul>
 * <li>Persistência com JPA/Hibernate</li>
 * <li>Serialização e desserialização JSON</li>
 * <li>Operações com {@link java.util.Date} e APIs legadas de data</li>
 * </ul>
 *
 * <p>
 * Essa abordagem evita inconsistências causadas por diferenças entre
 * timezone do servidor, banco de dados e ambiente de execução.
 * </p>
 */
@Configuration
public class SpringTimezoneConfig {

  /**
   * Define o fuso horário padrão da JVM após a inicialização do contexto Spring.
   *
   * <p>
   * O método é executado automaticamente após a construção do bean,
   * devido à anotação {@link PostConstruct}.
   * </p>
   */
  @PostConstruct
  public void timezoneConfig() {
    TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
  }
}