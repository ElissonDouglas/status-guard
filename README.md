# 🚨 StatusGuard

O **StatusGuard** é um sistema de monitoramento automático de saúde de serviços web (APIs) desenvolvido em Spring Boot. Ele verifica periodicamente a disponibilidade de URLs cadastradas, gerencia falhas de conexão de forma resiliente para evitar alarmes falsos e envia notificações em tempo real para um canal do Discord caso um serviço fique totalmente offline.

## 🚀 Tecnologias Utilizadas

- **Java 25** (ou a versão indicada no seu pom.xml)
- **Spring Boot 4.0.6**
    - Spring Data JPA (Persistência de dados)
    - Spring Scheduler (Tarefas agendadas e assíncronas)
- **H2 Database** (Banco de dados em memória para testes)
- **RestTemplate** (Comunicação HTTP e integração com APIs externas)
- **Discord Webhooks** (Sistema de alertas em tempo real)

## 🛠️ Funcionalidades

- **Monitoramento Automatizado:** Varredura periódica configurada via `@Scheduled` para testar o status HTTP de múltiplos serviços.
- **Mecanismo de Resiliência (Retry):** O sistema tolera instabilidades temporárias de rede. Um serviço só é marcado como `OFFLINE` se falhar 3 vezes consecutivas.
- **Alertas Inteligentes:** Integração com o Discord que dispara notificações contendo a identificação do serviço instável apenas no momento exato da alteração do status.
- **API REST Exposta:** Endpoint `/api/websites` que permite consultar o estado atual e o histórico de todas as aplicações monitoradas.

## 📦 Como Executar o Projeto

### Pré-requisitos
- Java JDK instalado
- Maven instalado (ou utilizar o wrapper `./mvnw`)

### Configuração da Variável de Ambiente
Para manter a segurança do sistema, a URL do Webhook do Discord é injetada dinamicamente através de variáveis de ambiente.

1. Configure a variável `DISCORD_WEBHOOK_URL` no seu sistema operacional ou na sua IDE (ex: IntelliJ IDEA em *Edit Configurations -> Environment Variables*).
2. Execute a aplicação utilizando o Maven:

```bash
./mvnw spring-boot:run