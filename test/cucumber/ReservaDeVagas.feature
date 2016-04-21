Feature: reserva de vagas de estacionamento
  As um usuário do sistema de estacionamento
  I quero ser capaz de reservar uma vaga de estacionamento
  so that eu possa me dirigir diretamente para a vaga reservada

  Scenario: reserva com estacionamento cheio
    Given todas as vagas estão ocupadas
    When Eu tento reservar uma vaga
    Then O sistema não faz nenhuma reserva

