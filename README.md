# BANTADS - Orquestrador SAGA

Este microserviço é o componente central responsável pela **orquestração de transações distribuídas** no sistema BANTADS. Ele implementa o padrão **SAGA Orquestrado** para garantir a consistência eventual entre os diversos microserviços do ecossistema (Cliente, Conta, Gerente, etc.).

## 🚀 Tecnologias Utilizadas

O projeto foi construído utilizando as seguintes tecnologias:

* **Java 17+** (JDK)
* **Spring Boot 3.x**
    * Spring Data JPA (Persistência)
    * Spring Web (API REST)
* **RabbitMQ** (Mensageria para comunicação assíncrona entre microserviços)
* **Maven** (Gestão de dependências)

## 🏗️ Arquitetura e Estrutura

A lógica central da saga reside no pacote `br.net.dac.saga`. A estrutura segue as melhores práticas para sistemas orientados a eventos:

* **Producers**: Responsáveis por disparar comandos para as filas do RabbitMQ destinadas aos outros serviços.
* **Consumers**: Escutam as filas de resposta/confirmação para decidir o próximo passo da transação.
* **Services**: Contêm a máquina de estados que define o fluxo de sucesso e os fluxos de **compensação** (rollback lógico) em caso de erro.
* **DTOs**: Objetos de transferência para padronizar a comunicação entre serviços.

### Pré-requisitos
* Docker e Docker Compose (para o RabbitMQ e Banco de Dados).
* Maven 3.x+.
* JDK 17+.
