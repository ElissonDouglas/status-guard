# 🚨 StatusGuard

O **StatusGuard** é um sistema de monitoramento automático de integridade de serviços web (APIs) desenvolvido em Spring Boot. O sistema realiza varreduras periódicas em URLs cadastradas, gerencia instabilidades de forma resiliente para evitar alarmes falsos e integra-se com Webhooks do Discord para notificações em tempo real.

## 🚀 Tecnologias Utilizadas

- **Java 25**
- **Spring Boot 4.0.6**
  - Spring Data JPA (Persistência de dados)
  - Spring Scheduler (Execução de rotinas assíncronas)
- **H2 Database** (Banco de dados em memória para ambiente de testes)
- **RestTemplate** (Comunicação HTTP e consumo de APIs externas)
- **Discord Webhooks** (Disparo de alertas em tempo real)

## 🛠️ Funcionalidades e Arquitetura

- **Transição de Estados Segura:** Os serviços são inicializados com o status `UNKNOWN`. O sistema evita alarmes falsos logo no primeiro ciclo caso a API cadastrada enfrente instabilidades momentâneas.
- **Mecanismo de Resiliência (Retry Pattern):** Um serviço monitorado só é marcado definitivamente como `OFFLINE` após registrar 3 falhas consecutivas na verificação.
- **Tratamento de Erros HTTP:** A camada de serviço captura especificamente exceções de resposta de erro vindas do servidor remoto (como HTTP 500), garantindo o incremento correto do contador de tentativas.
- **Alertas Inteligentes:** Notificações via Discord são acionadas apenas no momento exato em que a alteração crítica de status (de `ONLINE` para `OFFLINE`) é validada.
- **API REST Exposta:** Endpoint `/api/websites` configurado para retornar a lista completa, histórico de tentativas e a última verificação dos serviços.

## 📦 Como Executar o Projeto

### Pré-requisitos
- Java JDK 25 instalado
- Maven instalado (ou utilização do wrapper `./mvnw`)

### Configuração da Variável de Ambiente
A URL do Webhook do Discord é injetada dinamicamente por questões de segurança.

1. Configure a variável de ambiente `DISCORD_WEBHOOK_URL` no seu sistema operacional ou na sua IDE (IntelliJ IDEA: *Edit Configurations -> Environment Variables*).
2. Execute a aplicação via terminal:

```bash
./mvnw spring-boot:run