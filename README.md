# Calculadora de Custo — Portal da Fenda

> Ferramenta desktop em Java para calcular o custo de investimento em personagens, suas eidolons, cones de luz e sobreposições dentro do contexto do evento "Portal da Fenda", um evento de PvP dentro do jogo Honkai Star Rail .

---

## Sobre o Projeto

A **Calculadora de Custo — Portal da Fenda** é uma aplicação desktop desenvolvida em Java que auxilia jogadores de **Honkai: Star Rail** a calcularem o custo de investimento em seus personagens, levando em conta o cone de luz equipado e a classificação de tier de cada um dentro do contexto do evento "Portal da Fenda".

O sistema categoriza os personagens por tier de relevância no meta do jogo e permite associar cones de luz de diferentes raridades, calculando o custo total do investimento de forma prática e visual.

---

## Funcionalidades

- **Catálogo de personagens** — lista completa com personagens do jogo organizados por tier
- **Classificação por tier** — personagens categorizados em: `PilaresDoMeta`, `Brilhantes`, `Coringas`, `Neutros`, `QuebraGalho`, `FundoDoPoco` e `Error`
- **Seleção de cones de luz** — suporte a cones T3, T4, T5 e Cone da Loja da Herta
- **Cálculo de custo** — totalização do investimento com base no personagem e cone selecionados
- **Fotos dos personagens** — exibição visual dos personagens durante a seleção
- **Dados externos via `.txt`** — listas de personagens e cones carregadas de arquivos de texto, facilitando atualizações

---

## 🏆 Tiers de Personagens

| Tier | Descrição |
|------|-----------|
| `Error` | | Os melhores do jogo atualmente |
| `PilaresDoMeta` | Excelentes, muito fortes |
| `Brilhantes` | Fortes, mas que exigem de certas estratégias |
| `Coringas` | Versáteis, funcionam em vários times |
| `Neutros` | Sólidos, cumprem o papel |
| `QuebraGalho` | Servem em situações específicas |
| `FundoDoPoco` | Desempenho abaixo do esperado |

---

## 🔦 Cones de Luz Disponíveis

- Nada (sem cone)
- Cone T3
- Cone T4
- Cone T5
- Cone da Loja da Herta

---

## 🗂️ Estrutura do Projeto

```
Calculadora-de-Custo---Portal-da-Fenda/
├── src/                        # Código-fonte Java
├── Fotos personagens/          # Imagens dos personagens do jogo
├── ListaPersonagens.txt        # Lista de personagens e seus tiers
├── ListaCones.txt              # Lista de cones de luz disponíveis
├── .gitignore
└── LICENSE
```

---

## 🛠️ Tecnologias Utilizadas

- **Java** — linguagem principal
- **Java Swing / AWT** — interface gráfica desktop
- **Manipulação de arquivos** — leitura de dados via arquivos `.txt`

---

## 🚀 Como Executar

### Pré-requisitos

- [Java JDK](https://www.oracle.com/java/technologies/downloads/) 8 ou superior

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/Pauloisc/Calculadora-de-Custo---Portal-da-Fenda.git
   ```

2. Acesse a pasta do projeto:
   ```bash
   cd Calculadora-de-Custo---Portal-da-Fenda
   ```

3. Compile e execute via IDE ou terminal:
   ```bash
   javac src/*.java
   java -cp src Main
   ```

> **Atenção:** os arquivos `ListaPersonagens.txt`, `ListaCones.txt` e a pasta `Fotos personagens/` precisam estar no diretório raiz para que a aplicação funcione corretamente.

---

## 📄 Licença

Este projeto está licenciado sob a licença **MIT**. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 👤 Autor

Desenvolvido por **Paulo Ítalo Santos Costa**

[![GitHub](https://img.shields.io/badge/GitHub-Pauloisc-181717?style=flat&logo=github)](https://github.com/Pauloisc)
