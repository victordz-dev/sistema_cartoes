# Sistema Cartões

Um sistema CRUD de cartões, implementado em Java, utilizando JDBC e DAO, com banco de dados MySQL.

---

## 🛠 Tecnologias

- Java  
- JDBC  
- DAO (Data Access Object) – separação da lógica de acesso a dados  
- MySQL – banco de dados  
- Maven – gerenciamento de dependências e build  

---

## 📁 Estrutura do Projeto

```
sistema_cartoes/
├── src/
│   └── main/
│       └── java/
│           └── br/com/fiap/
│               ├── dao/         ← classes DAO para CRUD  
│               ├── model/       ← classes de modelo (Cartão, etc.)  
│               ├── util/        ← classes utilitárias, conexões, etc.  
│               └── Main.java    ← ponto de entrada do sistema  
├── pom.xml               ← configuração Maven  
├── README.md             ← este arquivo  
└── LICENSE               ← licença do projeto  
```

---

## 🚀 Funcionalidades

- Inserir novo cartão  
- Buscar cartão por ID  
- Listar todos os cartões  
- Atualizar dados de cartão  
- Remover cartão  

---

## 🔧 Requisitos

- Java JDK 8 ou superior  
- MySQL (ou outro servidor compatível)  
- Maven  
