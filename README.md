# Documentação para Execução da API de Pedidos



## Passo a Passo para Execução Local

### 1. Clonar o Repositório do Código-Fonte

Clone o repositório contendo o código-fonte da API para sua máquina local:

```bash
git clone https://github.com/sbveras-ufes/pedido-api.git
cd pedido-api
```

### 2. Construir a Imagem Docker Localmente

Para construir a imagem Docker da aplicação, você deve garantir que o Docker esteja instalado e funcionando corretamente.

Dentro da raiz do projeto, execute o seguinte comando para criar a imagem Docker:

```bash
docker build -t pedido-api:latest .
```

Este comando criará uma imagem Docker chamada `pedido-api` com a tag `latest`.

### 3. Executar a API usando Docker

Após a imagem ser criada, execute o seguinte comando para iniciar um container com a API:

```bash
docker run -d -p 8080:8080 --name pedido-api pedido-api:latest
```

Esse comando executa a aplicação em um container Docker e a expõe na porta `8080`.

Você pode verificar se o container está em execução com o seguinte comando:

```bash
docker ps
```

Para ver os logs da aplicação, execute:

```bash
docker logs pedido-api
```

Se precisar parar o container:

```bash
docker stop pedido-api
```

Se precisar remover o container:

```bash
docker rm pedido-api
```

## Consumo da API com Postman

### 1. Importar a Coleção Postman

- Baixe a coleção Postman fornecida, que contém todos os endpoints configurados para testes.
- No Postman, vá para o menu **File** e escolha **Import**. Selecione o arquivo `.json` da coleção e clique em **Import**.

### 2. Descrição dos Cenários de Teste

1. **Criar Cliente**:
   - Endpoint: `POST /clientes`
   - Envie um JSON contendo as informações do cliente (nome, tipo, fidelidade, etc.).

2. **Criar Pedido**:
   - Endpoint: `POST /pedidos`
   - Envie um JSON contendo os detalhes do pedido e o ID do cliente associado.

3. **Processar Descontos**:
   - Endpoint: `POST /pedidos/{pedidoId}/processar-descontos`
   - Envie o `pedidoId` como parâmetro para aplicar os cupons de desconto ao pedido.

4. **Consultar Pedido**:
   - Endpoint: `GET /pedidos/{pedidoId}`
   - Envie o `pedidoId` para consultar os detalhes do pedido, incluindo os itens e descontos aplicados.

### 3. Executar as Requisições

Após importar a coleção, você poderá executar os cenários de teste diretamente pelo Postman. Basta clicar no nome do endpoint desejado e clicar em **Send**.

## Execução da API usando Docker Hub

### 1. Puxar a Imagem do Docker Hub

Caso a imagem Docker da API esteja publicada no Docker Hub, use o seguinte comando para baixá-la:

```bash
docker pull seu-usuario/pedido-api:latest
```

### 2. Iniciar o Container da API

Após puxar a imagem do Docker Hub, execute o seguinte comando para iniciar o container:

```bash
docker run -d -p 8080:8080 --name pedido-api seu-usuario/pedido-api:latest
```

A API estará disponível em `http://localhost:8080`. Agora, você pode consumir a API usando Postman ou qualquer outra ferramenta de sua preferência.
