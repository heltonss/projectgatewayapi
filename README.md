# Configurando o Ambiente de Desenvolvimento

## Para configurar o ambiente de desenvolvimento e editar o código, siga estes passos:

1. **Pré requisito**
  - ambientes
    - Java Development Kit (JDK) 21
    - Gradle
    - Docker (em execução)
    - container kong gateway (em execução)
    - Postman


2. **Clonar o Repositório**:
  - Clone o repositório do projeto para sua máquina local usando Git.
    ```sh
    git clone https://github.com/heltonss/projectgatewayapi.git
    ```

3. **Instalar Dependências**:
  - Certifique-se de ter as dependências necessárias instaladas. Este projeto usa Gradle para automação de build. Se você não tiver o Gradle instalado, pode usar os scripts wrapper fornecidos.
    ```sh
    chmod +x ./gradlew build
    ./gradlew build
    ```

4. **Executar o Ambiente de Desenvolvimento**:
  - Esses scripts configurarão as variáveis de ambiente necessárias e iniciarão a aplicação, tem o objetivo para desenvolvedores ao fornecer um hot reload
    ```sh
    chmod +x ./start_developer_environment.sh
    ./start_developer_environment.sh 
    ```

5. **Executar a Aplicação**:
  - Esse comando é para executar a aplicação e testar o crud do app java, sem nenhuma dependência de gateway:
    ```sh
    chmod +x ./start_app.sh
    ./start_app.sh
    ```

# Scripts para executação e teste da aplicação com o kong gateway configurado
## Script para execução com o Kong
6. **Criação do ambiente com o Kong e execução do app**:
  - Esse comando cria todo o ambiente para teste, lembrando que o container do kong-gateway já deve estar em execução no docker, este script cria:
    - Service no kong
    - Adiciona duas rotas no kong
    - Adiciona plugin para tratamento de resposta do gateway
    - Executa o ambiente da aplicação java
    
    ```sh
    chmod +x ./start_environment.sh
    ./start_environment.sh
    ```

## Collection do postman
7. **Usando a collection do postman para teste**:
  - Import a collection TrabalhoGatewayIntegracao.postman_collection.json disponível no repositório para postman e execute as requests fornecidas

## Script para parar a aplicação e ambiente kong 
8. **Parar**:
  -Script para parar a aplicação e remover as services, routes e plugins usados nesse projeto:
    ```sh
   chmod +x ./halt_and_catch_fire.sh
   ./halt_and_catch_fire.sh
    ```
