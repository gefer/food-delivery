# **Food Delivery App - Resumo de Funcionalidades e Arquitetura**

O **Food Delivery** é um aplicativo de **Prova de Conceito (POC)** que segue práticas recomendadas de desenvolvimento no Android, utilizando **Clean Architecture**, **SOLID**, **Single Responsibility Principle (SRP)** e **Modularização**. O objetivo é criar uma plataforma robusta e escalável para a listagem de produtos, carrinho de compras e, futuramente, rastreamento de entregas e histórico de pedidos.

## **Funcionalidades Implementadas**

### **1. Listagem de Produtos**
- A funcionalidade principal é a **listagem de produtos** oriundos de um servidor remoto. Para isso, foi utilizado o padrão **MVVM (Model-View-ViewModel)** com o **Jetpack Compose** para a construção da interface.
- A tela exibe uma lista de produtos, onde cada item contém informações como nome, preço e imagem. 
- A **API** do servidor é consumida para trazer a lista de produtos, que é exibida dinamicamente na tela utilizando o **LazyColumn** do Jetpack Compose.

## **Arquitetura Utilizada**

A arquitetura do **Food Delivery** segue os princípios de **Clean Architecture**, com base em **camadas bem definidas** que asseguram uma estrutura escalável e fácil de testar. Além disso, são seguidos os princípios do design de software como o **SOLID**, promovendo um código limpo e de fácil manutenção.

### **1. Clean Architecture**
O design do sistema foi baseado na **Clean Architecture**, que visa separar a lógica de negócios das implementações específicas de interface e acesso a dados. A arquitetura é composta pelas seguintes camadas:

- **Camada de Domínio (Core Business)**: A camada central que contém a lógica de negócios e os **casos de uso**. Aqui, as regras que definem como listar os produtos ou adicionar ao carrinho são gerenciadas.
  
- **Camada de Apresentação (UI)**: Contém as telas e a lógica que interage diretamente com o usuário. No **Food Delivery**, essa camada é composta por **ViewModels** e **Composables** que são responsáveis por exibir dados e gerenciar estados de UI, como a lista de produtos.

- **Camada de Dados**: Responsável por fazer a comunicação com fontes de dados externas, como a API do servidor ou banco de dados local. Utiliza **Repositories** para abstrair os detalhes da fonte de dados (por exemplo, Retrofit para comunicação com APIs).

- **Camada de Infraestrutura**: Envolve todas as dependências externas como **Hilt** para injeção de dependências, **Retrofit** para chamadas de rede, e outras bibliotecas utilizadas no projeto.

### **2. Princípios SOLID**

A aplicação adota os princípios **SOLID**, que garantem um código mais modular, reutilizável e fácil de manter:

- **S - Single Responsibility Principle (SRP)**: Cada classe tem uma única responsabilidade. O **ViewModel** é responsável por gerenciar o estado da UI, enquanto os **Use Cases** implementam a lógica de negócios.
  
- **O - Open/Closed Principle**: O sistema é projetado para ser **aberto para extensões** e **fechado para modificações**. Por exemplo, ao adicionar novas funcionalidades, como o rastreamento de entregas, o código existente não precisa ser alterado.

- **L - Liskov Substitution Principle**: Subtipos podem ser substituídos por seus tipos base sem afetar a execução do programa. Isso é mantido ao criar interfaces para repositórios e serviços de dados, o que permite substituir implementações sem modificar a lógica da aplicação.

- **I - Interface Segregation Principle**: A aplicação segue este princípio criando **interfaces pequenas e específicas** para cada tipo de operação (como listar produtos, adicionar ao carrinho, etc.), facilitando modificações futuras.

- **D - Dependency Inversion Principle**: A dependência entre as camadas é invertida, de modo que as camadas superiores (como a camada de domínio) não dependem diretamente de implementações específicas. Isso é alcançado usando **injeção de dependências** com **Hilt**.

### **3. Modularização**

A modularização ajuda a manter o código organizado e facilita a escalabilidade do projeto. O sistema foi dividido nos seguintes módulos:

- **Módulo de Apresentação (UI)**: Contém os **Composables** e **ViewModels** que gerenciam a interação do usuário com o sistema. Ele é responsável por exibir as telas e reagir a mudanças de estado.

- **Módulo de Domínio**: Contém a lógica de negócios, que é independente das implementações específicas da UI ou camada de dados. Isso inclui casos de uso como listar produtos ou adicionar ao carrinho.

- **Módulo de Dados**: Responsável pela comunicação com fontes de dados externas (APIs, banco de dados), utilizando **Repositories** para fornecer dados para a camada de domínio.

### **Vantagens da Modularização**
- **Escalabilidade**: Novas funcionalidades podem ser adicionadas sem afetar os módulos existentes. Por exemplo, funcionalidades como "visualização do carrinho de compras" ou "rastreamento de entrega" podem ser implementadas em módulos separados.
  
- **Facilidade de Testes**: Cada módulo pode ser testado de forma independente, garantindo que as alterações em uma parte do código não quebrem outras partes do sistema.

## **Implementações Futuras**

Algumas funcionalidades ainda precisam ser implementadas no app, como:

### **1. Listagem de Pedidos Efetuados**
- A listagem de pedidos realizados é uma funcionalidade essencial para o aplicativo. O usuário poderá visualizar os pedidos anteriores, incluindo detalhes como o status do pedido, produtos comprados e o valor total.

### **2. Visualização do Carrinho de Compras**
- A tela de carrinho permitirá que o usuário visualize os produtos que foram adicionados ao carrinho, altere quantidades e finalize a compra.

### **3. Rastreamento de Entrega**
- Implementação de uma tela para o rastreamento em tempo real da entrega dos produtos, permitindo ao usuário acompanhar o status da entrega (em trânsito, entregue, etc.).

## **Conclusão**

O **Food Delivery** foi construído seguindo as melhores práticas do mercado Android, utilizando **Clean Architecture** e os princípios **SOLID** para garantir um código escalável, testável e de fácil manutenção. A modularização também desempenha um papel crucial, permitindo uma implementação e expansão eficiente do sistema. A estrutura está preparada para implementar funcionalidades futuras como a visualização do carrinho, histórico de pedidos e rastreamento de entregas, sem comprometer a estabilidade e performance do aplicativo.