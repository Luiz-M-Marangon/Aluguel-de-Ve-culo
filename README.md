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

src/
├── main/
│   └── java/
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
│                       └── hibernate.cfg.xml


🎨Temas FlatLaf
* **Tema Claro**: Interface para uso diurno
* **Tema Escuro**: Reduzir fagida e em ambientes com pouca iluminação
* **Alternância em tempo real**: Botão no canto superior direito que permite ao usuário alternar entre os dois modos

🛠Tecnologias Utilizadas
* FlatLaf 3.6.2
* Hibernate 7.2.0
* Maven
* Swing

🎯Observações\
⚠️Para conseguir associar um usuário cadastrado a um veículo, deve-se primeiro clicar em "🔄Atualizar Listas"

🔧Melhorias Futuras
* Persistência em banco de dados real
* Atualizção automática das listas (sem a necessidade de utilizar o "🔄Atualizar Listas")
* Sistema de logins e permissões
* Relatórios em PDF

👨‍💻 Desenvolvida por
Luiz Mário Marangon - luizmarangonzz11@gmail.com
