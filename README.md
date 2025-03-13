# 📞 Call Center Support System

## 📝 Descrição do Projeto

Este projeto é um trabalho semestral da matéria de Programação de Computadores II. Ele consiste em um sistema de suporte para call center, permitindo a criação e gerenciamento de tickets de suporte, com funcionalidades dedicadas para clientes e administradores.

## 🛠️ Tecnologias Utilizadas

- **Maven** (Gerenciamento de dependências e build)

- **Java 21**

- **JavaFX** (Interface gráfica)

- **Hibernate** (JPA) (Integração com Banco de Dados)

- **MySql** (Banco de dados local)

- **WebView** (Visualização de anexos em PDF)





## 🌟 Funcionalidades Principais

### 📚 FAQ

  - Os clientes podem acessar uma seção de perguntas frequentes (FAQ)

  - O administrador pode criar FAQs para ajudar os clientes a resolverem dúvidas comuns

### 🎯 Para Clientes

- Criação de tickets de suporte

- Acompanhamento do status dos tickets

- Adição de anexos (PDF)

- Interação com o administrador via chat dentro do ticket

### 🔧 Para Administradores

- Acesso ao painel de controle

- Visualização de todos os tickets

- Resposta e acompanhamento dos tickets

- Visualização dos dados dos clientes

- Gerenciamento das interações

## 🔨 Instalação e Execução

1. Clone o repositório:
      ``` git clone https://github.com/LeonardoGirardi/SistemaAtendimento.git ```

3. Configure o banco de dados (MySql):

    - Crie um banco de dados 
  
    - Configure o persistence.xml com as credenciais do banco:
    ```
      <property name="hibernate.connection.url" value="jdbc:mysql:thin:@hostname:1521:dbname"/>
      <property name="hibernate.connection.username" value="seu_usuario"/>
      <property name="hibernate.connection.password" value="sua_senha"/>
    ```
3. Compile e execute o projeto:
      - No IntelliJ, carregue o projeto como Maven e execute o arquivo Application.java.

4. Login:
      - Cliente: Crie uma conta diretamente no sistema
      - Administrador: Login com usuário padrão: ```Admin```, senha: ```admin123```

## 🔥 Melhorias Futuras

- Implementação de notificações em tempo real

- Suporte para mais tipos de anexos

- Exportação de tickets para PDF

## 📌 Licença

Este projeto é de uso livre para fins educacionais.

### 📧 Autor: Leonardo Girardi

