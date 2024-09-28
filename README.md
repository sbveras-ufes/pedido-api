Aqui está a especificação completa da API em formato Markdown:

```markdown
# API de Pedidos com Descontos

## Requisitos de Ambiente

### Ferramentas Necessárias

- **Java 17**
- **Maven**
- **Docker** 
- **Postman**

## Execução Local

### 1. Clonar o Repositório

Clone o repositório contendo o código-fonte da API :

```
git clone https://github.com/sbveras-ufes/pedido-api.git
cd pedido-api
```

### 2. Construir a Imagem Docker Localmente

Dentro da raiz do projeto, execute o comando para criar a imagem Docker:

```bash
docker build -t pedido-api:latest .
```

### 3. Executar a API usando Docker

Após a imagem ser criada, execute o comando para iniciar um container com a API:

```bash
docker run -d -p 8080:8080 --name pedido-api pedido-api:latest
```

### 4. Verificar se o Container Está em Execução

Você pode verificar se o container está em execução com o seguinte comando:

```bash
docker ps
```

## Consumo da API com Postman

### 1. Importar a Coleção Postman

- Baixe a coleção Postman fornecida.
- No Postman, vá para **File** > **Import**. Selecione o arquivo `.json` da coleção e clique em **Import**.

### 2. Descrição dos Endpoints

#### **Criar Cliente**
- **Método**: `POST`
- **URL**: `/clientes`
- **Corpo (JSON)**:
  ```json
  {
      "nome": "",
      "tipo": "",
      "fidelidade": 0.0,
      "logradouro": "",
      "bairro": "",
      "cidade": ""
  }
  ```

#### **Criar Pedido**
- **Método**: `POST`
- **URL**: `/pedidos?clienteId={id}`
- **Corpo (JSON)**:
  ```json
  {
      "taxaEntrega": 10.0,
      "itens": [
          {
              "nome": "Pizza",
              "quantidade": 1,
              "valorUnitario": 20.0,
              "tipo": "Alimentação"
          }
      ]
  }
  ```

#### **Processar Descontos**
- **Método**: `POST`
- **URL**: `/pedidos/{pedidoId}/processar-descontos`
- **Sem corpo**

#### **Consultar Pedido**
- **Método**: `GET`
- **URL**: `/pedidos/{pedidoId}`
