# Guia de Estética: Implementação de Imagens Dinâmicas no Java Swing

Este documento serve como um roteiro lógico para adicionar retratos de personagens que mudam conforme a seleção no `JComboBox`. Siga estes passos para implementar a funcionalidade sem a necessidade de copiar códigos prontos.

---

## 1. O Conceito Base
No Java Swing, imagens não são componentes independentes que você joga na tela. Elas funcionam como **ícones** que são "vestidos" por um componente, geralmente um `JLabel`.

### Peças do Quebra-Cabeça:
- **O Quadro:** Um `JLabel` dedicado apenas para a imagem.
- **O Arquivo:** Um objeto `ImageIcon` que aponta para o caminho da sua foto no computador.
- **O Motor:** O `ActionListener` do seu Dropdown que detecta quando a escolha mudou.

---

## 2. Passo a Passo da Implementação

### Fase A: Preparação do Layout (Escalabilidade)
Como sua aplicação possui 8 slots (4 por time), você precisará de 8 quadros de imagem independentes.
1. **Arrays de Labels:** Assim como você usa vetores para os `JComboBox`, crie vetores para os `JLabel` (ex: `labelsImagensT1` e `labelsImagensT2`).
2. **Criação no Laço (Loop):** Dentro do seu método `CriarSlots`, você deve instanciar um novo `JLabel` a cada iteração do loop. Isso garante que cada personagem tenha seu próprio quadro.
3. **Modificar o Grid:** Aumente o número de linhas do seu painel de slot (ex: de 4 para 5) para que o `JLabel` fique no topo, acima do seletor de personagem.

### Fase B: O Processamento da Imagem
Imagens brutas raramente cabem perfeitamente no espaço do Swing. Você precisará manipulá-las:
1. **Carregamento:** Crie um `ImageIcon` passando o caminho da pasta + nome do personagem + extensão (ex: `.png`).
2. **Conversão:** Transforme esse ícone em uma `Image` comum para poder usar funções de desenho.
3. **Redimensionamento:** Use a função de escala para definir uma largura e altura fixas (ex: 120x120 pixels).
4. **Qualidade:** Use a constante de escala "Suave" (Smooth) para evitar que a imagem fique serrilhada.
5. **Finalização:** Transforme o resultado de volta em um `ImageIcon` para que o Label consiga exibi-lo.

### Fase C: A Conexão Direta
O segredo para não confundir as imagens é a **vinculação**:
1. **Escuta Específica:** Quando você adicionar o `ActionListener` a um dropdown, faça com que ele aponte exatamente para o `JLabel` que está no mesmo slot.
2. **Troca Dinâmica:** Use a função de "setar ícone" do seu Label para aplicar a nova imagem processada sempre que a seleção mudar.

---

## 3. Principais Classes e Funções (Para Pesquisa)

| Objetivo | Classe | Função Principal |
| :--- | :--- | :--- |
| Exibir a imagem na tela | `JLabel` | `.setIcon(...)` |
| Carregar o arquivo do disco | `ImageIcon` | `new ImageIcon("caminho")` |
| Redimensionar a foto | `Image` | `.getScaledInstance(...)` |
| Obter a escolha do usuário | `JComboBox` | `.getSelectedItem()` |
| Garantir boa qualidade | `Image` | `Image.SCALE_SMOOTH` |

---

## 4. Dicas de Ouro para Múltiplos Slots
- **Loop Único:** Tente fazer tudo dentro do mesmo loop onde você cria os dropdowns. Isso evita que você perca a referência de qual label pertence a qual botão.
- **Nomenclatura:** Se o personagem selecionado for "Kafka", o programa deve procurar por "Kafka.png". Padronize seus arquivos!
- **Imagens Padrão:** Crie um Label que comece com uma imagem de "silhueta" ou interrogação. Assim, o layout já nasce com o tamanho correto antes mesmo do usuário escolher um boneco.

---
*Este guia foi atualizado para refletir a necessidade de múltiplos slots de imagem para os times.*
