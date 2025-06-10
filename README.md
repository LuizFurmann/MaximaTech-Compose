# MaximaTech-Compose

README do Projeto

Este é um aplicativo Android desenvolvido em Jetpack Compose que simula uma loja de calçados. Ele permite aos usuários navegar por uma variedade de produtos, visualizar detalhes, filtrar por categoria, adicionar itens ao carrinho e gerenciar seu perfil.

Funcionalidades Principais
Tela Home: Exibe uma lista de calçados disponíveis com opções de navegação.
Tela de Detalhes do Calçado: Mostra informações detalhadas sobre um calçado específico, incluindo descrição, preço e opções de adicionar ao carrinho/favoritar.
Filtros de Calçados: Permite filtrar a lista de calçados por categorias (e.g., Tênis, Botas).
Navegação Inferior (Bottom Navigation): Proporciona uma forma intuitiva de alternar entre as principais seções do aplicativo.
Arquitetura e Tecnologias Utilizadas
O projeto segue uma arquitetura moderna e utiliza as seguintes tecnologias:

Jetpack Compose: Toolkit moderno do Android para construir UIs nativas.
MVI (Model-View-Intent): Padrão arquitetural que garante um fluxo de dados unidirecional, tornando o estado da UI previsível e depurável.
Camada de Repository: Abstrai a fonte de dados, permitindo a fácil integração de diferentes fontes (e.g., API, banco de dados local).

Como Rodar o Projeto
Clone o Repositório:
Bash

git clone https://github.com/LuizFurmann/MaximaTech-Compose
Abra no Android Studio: Abra o projeto no Android Studio Arctic Fox (ou superior) para garantir compatibilidade com o Jetpack Compose.
Sincronize o Projeto: Deixe o Gradle sincronizar as dependências.
Execute em um Emulador/Dispositivo: Selecione um emulador Android ou conecte um dispositivo físico e execute o aplicativo.