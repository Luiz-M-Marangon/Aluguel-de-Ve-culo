🚗 Sistema de Locadora de Automóveis
*Projeto Desenvolvido em Aula de **Programação Orientada a Objetos***

Sobre o Projeto
Este trabalho foi desenvolvido como parte da disciplina de Programação Orientada a Objetos II (POOII), com foco principal na implementação de validações robustas, interface gráfica intuitiva e relacionamento entre entidades utilizando um banco de dados simples em memória.

O sistema gerencia uma locadora de automóveis, permitindo o cadastro de usuários, veículos e controle completo de aluguéis.

✅Funcionalidades
* **Validação de Email**: Verifica formato digitado pelo usuário (usuario@dominio.com)
* **Validação de Telefone**: Aceita diferentes formados diitados, com ou sem espaço, com parênteses ou sem ((54) 99999-9999, 54 999999999, 54999999999)
* **Validação de Placas**: Suporta formato de placas Mercosul(AAA-0A00) e formato tradicional brasileiro (AAA-0000)
* **Validação de Ano**: Garante que o veículo tenha data entre 1900-2030
* **Validação de Quilometragem**: Verifica consistência entre valores de km inicial/final

🎨Interface Gráfica Swing
* **Interface dividida em abas**: 0rganizadas para maior clareza
* **Tabelas Interativas**: Permitem ao usuário fazer diferentes ordenações
* **Formulário intuitivos e feedback visual**: Validação em tempo real com mensagens de erro e sucesso

🏗️Estrutura do Projeto

```
📦 locadora
src\
├── main/
│    └── java/
│       └── com/
│           └── mycompany/
│               └── locadora/
│                   ├── Locadora.java                 # Classe principal
|                   ├── dao/                        
│                   │   ├── AluguelDAO.java
│                   │   ├── GenericDao.java
│                   │   ├── UsuarioDAO.java
│                   │   ├── VeiculoDAO.java
|                   ├── model/
│                   │   ├── Aluguel.java
│                   │   ├── Usuario.java
│                   │   ├── Veiculo.java
|                   ├── service/
│                   │   ├── AluguelService.java
│                   │   ├── UsuarioService.java
│                   │   ├── VeiculoService.java
│                   ├── telas/
│                   │   ├── TelaPrincipal.java        # Janela principal com abas
│                   │   ├── TelaUsuarios.java         # CRUD de usuários
│                   │   ├── TelaVeiculos.java         # CRUD de veículos
│                   │   └── TelaAlugueis.java         # Controle de aluguéis
│                   └── util/
│                       └── Validadores.java          # Centralizador de validações
│                   └── resources/
│                       └── hibernate.cfg.xml        # Arquivo configuração de hibernate
```



🎨Temas FlatLaf
* **Tema Claro**: Interface para uso diurno
* **Tema Escuro**: Reduzir fagida e em ambientes com pouca iluminação
* **Alternância em tempo real**: Botão no canto superior direito que permite ao usuário alternar entre os dois modos

🛠Tecnologias Utilizadas
* FlatLaf 3.6.2
* Hibernate 7.2.0
* Maven
* Swing
* IDE NetBeans
* Uso de inteligência artificial para auxílio com FlatLaf, BackEnd/FronEnd e ao aplicar hibernate de forma mais coesa 

🎯Observações\
⚠️Para conseguir associar um usuário cadastrado a um veículo recém cadastrados, deve-se primeiro clicar em "🔄Atualizar Listas" para atualizar o registro em Alugueis

🔧Melhorias Futuras
* Persistência em banco de dados real
* Atualizção automática das listas (sem a necessidade de utilizar o "🔄Atualizar Listas")
* Sistema de logins e permissões
* Relatórios em PDF

📷 Tela Principal - Tema Claro
<img width="985" height="690" alt="image" src="https://github.com/user-attachments/assets/3e5882be-ce9d-473d-879a-20272c4ea43b" />

📷 Tela Principal - Tema Escuro
<img width="984" height="693" alt="image" src="https://github.com/user-attachments/assets/8f62b0e7-d9bb-4780-90c4-83b8aaaa3736" />

📷 Cadastro Usuário - Validação campo Email
<img width="952" height="356" alt="image" src="https://github.com/user-attachments/assets/baef30a5-a682-4a80-aa22-4b2882c45771" />

📷 Cadastro Veículos - Validação campo Placa
<img width="957" height="372" alt="image" src="https://github.com/user-attachments/assets/af185529-e75f-43cb-b784-20d26868e5dd" />

📷 Tela Aluguel - Associação entre Usuário e Veículo
<img width="979" height="688" alt="image" src="https://github.com/user-attachments/assets/06bf3131-a74f-4e52-acb5-40e262403bab" />

📷 Tela Fechamento de Aluguel - Usuário é obrigado a informar km final maior que a inicial
<img width="267" height="242" alt="image" src="https://github.com/user-attachments/assets/c59a34c0-fb2c-4e8b-9fc2-b04fad2134f3" />


\
👨‍💻 Desenvolvida por
Luiz Mário Marangon - luizmarangonzz11@gmail.com
