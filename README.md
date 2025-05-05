Universidade Federal da Paraíba – UFPB
Centro de Informática
Departamento de Computação Científica – DCC

Sistema de Gerenciamento Comercial

Evandro Policarpo, João Veloso, Lucas Franca, Pedro Benício

https://github.com/Plcrp/CommercialManagementSys
João Pessoa -PB
2025

Introdução

	Comércios precisam gerenciar de forma eficiente três pilares fundamentais: pessoas, produtos e estoque. Pelo lado das pessoas, é essencial controlar dados de funcionários (cargos, admissões, salários) e clientes (cadastros, histórico de compras, programas de fidelidade). Já no que diz respeito aos produtos, deve‑se registrar atributos como código, descrição, custo, preço, datas de fabricação e vencimento. Por fim, o estoque precisa refletir em tempo real as quantidades disponíveis, gerando alertas para reposição e mantendo um histórico de entradas e saídas auditável.
Este sistema de gerenciamento, desenvolvido em Java com uma arquitetura em camadas (Model-View-Controller) oferece uma plataforma para que gestores de supermercado tenham controle total sobre operações diárias, reduzindo desperdícios, prevenindo rupturas de estoque e melhorando a experiência tanto do cliente quanto da equipe interna.

Modelagem do problema

Relacionamentos:
• PID é uma superclasse abstrata de Cliente e Funcionário
• Cliente herda de PID
• Funcionario herda de PID
• Produto é chave em Estoque
• Estoque é uma composição de vários produtos
• Cargo são tipos fixos para atributo cargo em Funcionario
• Validavel é uma interface implementada por Cliente e Funcionário

Classes:
• PID (nome: String, ID: String, dataNasc: Date)
• Cliente (totalGasto: Double, dataCadastro: Date)
• Funcionario (joiningDate: Date, carco: Cargo, salario: Double)
• Produto (name: String, ID: String, preco: double, dataCadastro, fabricacao, vencimento: Date)
• Estoque (estoque: Map<Produto, Integer>)
• Cargo (GERENTE, SUPERVISOR, EMPREGADO)
• Validavel (validar(): boolean)

Ferramentas utilizadas
InteliJJ: IDE utilizada pelos quatro membros do grupo para a construção e teste dos códigos.
GIT: Github foi usado para comunicação e compartilhamento dos códigos pelos membros do grupo
Maven: Maven foi utilizado para a construção da Build do projeto.

Resultados e considerações finais
Com o programa atingimos nosso resultado esperado, conseguimos montar um programa com cadastro, visualização de dados, exclusão de dados e estrutura de camadas que permite o gerenciamento de informações de um comércio, utilizando alguns conceitos de POO.
Algumas dificuldades encontradas foram com o uso de Polimorfismo, o tratamento de datas exigiu um pouco de cuidado extra, lidar manualmente com BufferReader / BufferWriter demonstrou-se propenso a erros, e a falta de um CRUD completo, com a falta da parte Update, impedindo atualização de informações.
Esse projeto foi a parte final do aprendizado de OO, colocando todas os conceitos para uso obrigatório, foi uma boa prática embora não tenha sido feita de forma completa devido a falta de polimorfismo.
	
 


