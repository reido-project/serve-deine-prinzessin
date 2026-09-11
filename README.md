# Serve Deine Prinzessin

> **A small Java CLI dating / interaction game with ASCII art, dialogue, gifts, and questionable life decisions. 😂**

**Serve Deine Prinzessin** is a simple command-line game where you interact with a character through dialogue and several activities.

This repository contains the **CLI version** of the game — the simplest version of *Serve Deine Prinzessin*.

The game is intentionally built around a lightweight Java architecture with no external dependencies.

---

## 🎮 Gameplay

You start by entering your player name and choosing a character to interact with.

Currently, the only available character is:

* **Kyoko**

Once the interaction begins, you can:

| Action   | Key | Description                                        |
| -------- | --- | -------------------------------------------------- |
| 🎁 Gift  | `G` | Give Kyoko an item                                 |
| 😏 Tease | `T` | Tease Kyoko and trigger a random dialogue sequence |
| 🍴 Feed  | `F` | Feed the current character                         |

The game uses dialogue and character states to determine which ASCII sprite is displayed.

---

## 🖥️ CLI Version

This version runs entirely inside the terminal.

Character sprites are rendered using **ASCII art**, while dialogue is displayed with a typewriter-style effect.

### Screenshots

#### Home / Character Interaction

#### Dialogue

#### Gift Selection

---

## ✨ Features

* 🧑 Custom player name
* 👸 Character selection
* 💬 Dialogue system
* 🎲 Randomized dialogue responses
* 🎁 Gift system with different reactions
* 😏 Teasing interaction
* 🍴 Feeding interaction
* ❤️ Character affection system
* 🎭 Character states with different ASCII sprites
* ⌨️ Fully keyboard-driven CLI gameplay
* ☕ Written in Java

---

## 🕹️ Controls

After entering the game, the main interaction menu is:

```text
[G] Gift   [T] Tease   [F] Feed
>
```

### Gift

Press `G` to open the gift selection.

Example:

```text
================================================================================
1. Some Gift
2. Another Gift
3. Something Else
================================================================================

Select Gift:
```

The character's response depends on the item's impact.

Possible reactions include:

* **Satisfied**
* **Likes**
* **Neutral**
* **Disappointed**

Some responses are randomized, so giving the same type of gift does not necessarily produce exactly the same dialogue every time.

### Tease

Press `T` to tease the current character.

The game randomly selects:

1. A player teasing line
2. A character response

Both are then displayed as a dialogue sequence.

### Feed

Press `F` to feed the current character.

---

## 🧩 Project Structure

```text
.
├── docs/
│   ├── dialogue.PNG
│   ├── gift.PNG
│   ├── hero.png
│   └── hidden/
│       └── note.txt
│
├── src/
│   ├── com/
│   │   └── nub/
│   │       └── app/
│   │           ├── AppController.java
│   │           ├── AppState.java
│   │           ├── Main.java
│   │           ├── UI.java
│   │           │
│   │           ├── dialogues/
│   │           │   ├── CharacterState.java
│   │           │   ├── Dialogue.java
│   │           │   ├── DialogueBank.java
│   │           │   └── KyokoDialogue.java
│   │           │
│   │           ├── misc/
│   │           │   ├── ItemList.java
│   │           │   └── Items.java
│   │           │
│   │           └── models/
│   │               ├── Characters.java
│   │               ├── Kyoko.java
│   │               ├── KyokoSprite.java
│   │               └── Player.java
│   │
│   └── META-INF/
│       └── MANIFEST.MF
│
├── Serve Deine Prinzessin.jar
├── README.md
└── .gitignore
```

### Main Components

#### `AppController`

The main game controller.

It handles:

* Game states
* Character selection
* Gameplay input
* Dialogue flow
* Gift interactions
* Teasing
* Feeding

#### `AppState`

Defines the current state of the game.

The current flow consists of states such as:

```text
HOME
  ↓
CHARSELECT
  ↓
IDLE
  ├── INVENTORY
  └── EXIT
```

#### `dialogues/`

Contains the dialogue system and character-specific dialogue.

`KyokoDialogue` contains Kyoko's dialogue bank, including:

* Prologue
* Gift reactions
* Tease dialogue
* Tease responses

#### `models/`

Contains the game's core models.

This includes:

* `Player`
* `Characters`
* `Kyoko`
* `KyokoSprite`

`KyokoSprite` is responsible for the ASCII-art representation of Kyoko.

#### `misc/`

Contains item-related classes used by the gift system.

---

## 🏗️ Architecture

The CLI version uses a simple state-driven architecture.

```text
                 ┌─────────────┐
                 │    HOME     │
                 └──────┬──────┘
                        │
                        ▼
                 ┌─────────────┐
                 │ CHARSELECT  │
                 └──────┬──────┘
                        │
                        ▼
                 ┌─────────────┐
                 │    IDLE     │
                 └──────┬──────┘
                        │
          ┌─────────────┼─────────────┐
          ▼             ▼             ▼
       [ G ]          [ T ]         [ F ]
          │             │             │
          ▼             ▼             ▼
      Inventory       Tease          Feed
          │             │             │
          └─────────────┴─────────────┘
                        │
                        ▼
                       IDLE
```

Dialogue is represented through the `Dialogue` model and contains information such as:

* Speaker
* Dialogue line
* Character state

The controller then uses the character state to select and render the appropriate ASCII sprite.

---

## 🎭 Character States

Characters can have different states during dialogue.

For example:

```text
NEUTRAL
```

The character's current state determines which sprite is displayed before the dialogue line.

This makes it possible to expand the game later with additional expressions and emotional states.

---

## 🎁 Gift System

Each character has an item list containing gifts with different impacts.

An item's impact determines which dialogue pool is selected.

```text
Item Impact
    │
    ├──  2  → Satisfied
    ├──  1  → Likes
    ├──  0  → Neutral
    └── -3  → Disappointed
```

The exact response is randomized from the corresponding dialogue pool.

---

## ❤️ Affection

Characters have an affection value that can be modified through interactions.

The current CLI version keeps this system intentionally simple, but it provides a foundation for future gameplay mechanics.

---

## 🚀 Running the Game

### Option 1 — Run the JAR

A compiled JAR is included in the repository:

```text
Serve Deine Prinzessin.jar
```

Run it with:

```bash
java -jar "Serve Deine Prinzessin.jar"
```

Make sure Java is installed and available through your system's `PATH`.

### Option 2 — Run from Source

Clone the repository:

```bash
git clone <repository-url>
cd <repository-directory>
```

Then open the project using an IDE such as IntelliJ IDEA.

Run:

```text
com.nub.app.Main
```

---

## ☕ Requirements

* Java **17+**
* A terminal capable of displaying standard Unicode characters

Java 17 is recommended because the project uses modern Java syntax such as text blocks and switch expressions.

---

## 📦 Dependencies

None.

The CLI version is built entirely using the Java standard library.

---

## 📝 Development Notes

This project started as a small random Java project and gradually evolved into a game.

The CLI version focuses on experimenting with:

* Object-oriented programming
* State management
* Dialogue systems
* Basic game architecture
* Randomized interactions
* Character models
* ASCII-based rendering

It is intentionally small and straightforward.

---

## 🔮 Future Versions

*Serve Deine Prinzessin* is planned as a multi-version project.

The **CLI version** is the simplest implementation and serves as the foundation for future versions with more advanced interfaces and gameplay.

Possible future improvements include:

* [ ] More characters
* [ ] More character states / expressions
* [ ] More gifts
* [ ] Expanded dialogue
* [ ] More interaction types
* [ ] More meaningful affection mechanics
* [ ] Save / load system
* [ ] Graphical interface
* [ ] Additional game content

---

## 📜 License

This project is currently for personal / experimental use.

No license has been specified yet.
