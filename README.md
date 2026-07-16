# 🎮 Tetris Terminal

Um jogo de **Tetris** desenvolvido em **Java** utilizando a biblioteca **Lanterna**, executado inteiramente no terminal.

O objetivo deste projeto foi praticar conceitos de Programação Orientada a Objetos, manipulação de matrizes, lógica de jogos, tratamento de entrada do usuário e renderização em modo texto.

---

## 📷 Demonstração

> Adicione aqui um GIF ou algumas imagens do jogo funcionando.

Exemplo:

```
assets/demo.gif
```

---

## ✨ Funcionalidades

- 🎲 Geração aleatória dos 7 tetraminós
- 🔄 Rotação das peças
- ⬅️➡️ Movimento lateral
- ⬇️ Gravidade automática
- 🚧 Colisão com paredes e outras peças
- 🧱 Fixação das peças ao tocar o chão
- 🗑️ Remoção automática de linhas completas
- 🏆 Sistema de pontuação (+100 pontos por linha)
- 👀 Visualização da próxima peça
- 🎨 Interface colorida no terminal
- 📋 Menu inicial
- 💀 Tela de Game Over

---

## 🛠 Tecnologias

- Java 21
- Maven
- Lanterna
- VS Code

---

## 📁 Estrutura do projeto

```
src
└── main
    └── java
        └── com
            └── anaflavia
                └── tetris
                    ├── config
                    ├── game
                    ├── render
                    └── Main.java
```

---

## ▶️ Como executar

### 1. Clone o repositório

```bash
git clone https://github.com/SEU_USUARIO/tetris.git
```

Entre na pasta:

```bash
cd tetris
```

---

### 2. Compile o projeto

```bash
mvn clean compile
```

---

### 3. Execute

```bash
mvn exec:java
```

Caso o plugin `exec-maven-plugin` não esteja configurado, utilize:

```bash
mvn package
```

Depois:

```bash
java -jar target/tetris.jar
```

---

## 🎮 Controles

| Tecla | Ação |
|-------|------|
| ← | Mover para esquerda |
| → | Mover para direita |
| ↓ | Descer mais rápido |
| ↑ | Rotacionar peça |
| ENTER | Iniciar/Reiniciar jogo |
| ESC | Sair |

---

## 📚 Conceitos praticados

- Programação Orientada a Objetos
- Matrizes
- Enum
- Renderização em terminal
- Game Loop
- Colisão
- Rotação de matrizes
- Controle de estado do jogo (Menu, Jogando e Game Over)
- Maven
- Biblioteca Lanterna

---

## 🚀 Melhorias futuras

- Sistema de níveis
- Aumento da velocidade
- Ghost Piece
- Hard Drop
- Hold Piece
- High Score salvo em arquivo
- Sons
- Animação na remoção das linhas

---

## 👩‍💻 Autora

Desenvolvido por **Ana Flávia** durante as férias como projeto de estudo em Java.

GitHub: https://github.com/Ana-hub2000

Instagram: @ana_almeida.dev