# Serve Deine Prinzessin

> **A small Java interactive narrative / dating game with dialogue, gifts, questionable life decisions, and four Prinzessins. 😂**

---

## ⚠️ This Branch Is Not The Source Code

If you're looking for the actual source code, **switch to the branch corresponding to the version you want to inspect.**

This repository is intentionally organized as a **multi-version monorepo**:

| Branch    | Version             | Architecture | Purpose                                       |
| --------- | ------------------- | ------------ | --------------------------------------------- |
| `main`    | Releases / Builds   | —            | Runnable `.jar` files & project overview      |
| `cli`     | Prototype           | CLI          | Original prototype                            |
| `mvc-vsa` | Development Version | MVC + VSA    | First serious attempt at structuring the game |
| `rdmga`   | Remastered          | RDMGA        | Current specialized architecture              |

> **Recommended:** Start with `main`, then explore the branches from oldest to newest to see how the project evolved.

---

# About The Game

![Serve Deine Prinzessin](/docs/icon.png)

**Serve Deine Prinzessin** is a small single-player Java interactive narrative / dating game built around dialogue, gifts, resource management, questionable decisions, and multiple endings.

The premise is simple:

You meet a Prinzessin.

You talk to her.

You feed her.

You give her things.

You tease her.

You make questionable decisions.

And somehow, **three numbers determine your fate.**

### Core Stats

* **Hunger**
* **Affection**
* **Insanity**

Each Prinzessin has multiple possible endings based on the state of these statistics.

The game currently contains **five possible endings per Prinzessin**, triggered when one of the relevant stats reaches an extreme state.

The intended experience is less about "winning" and more about **figuring out what the hell is happening while trying not to accidentally reach an ending.**

---

# The Prinzessins

The project currently contains four planned/selectable characters:

* **Kyoko Kirigiri** — *Danganronpa: Trigger Happy Havoc*
* **Chiaki Nanami** — *Danganronpa 2: Goodbye Despair*
* **Sakagami Tomoyo** — *CLANNAD*
* **Migiwa Kazuha** — *Yosuga no Sora*

The game is essentially a crossover-style AU / fan project built around the question:

> **"What if I were dropped into these franchises, and somehow this was my problem now?"**

The protagonist is intentionally based on a fictionalized version of the developer/player rather than being a conventional predefined protagonist.

Character content is not equally complete across all versions. The remastered branch currently has the most developed content around **Kyoko**, while the other characters remain partially implemented / under development.

---

# Why This Repository Exists

This repository is not only a game project.

It is also a record of **how the code evolved**.

The game started as a tiny Java CLI experiment.

Then it became an attempt at applying conventional architecture.

Then that architecture became increasingly complicated.

Then the whole thing was reconsidered and reorganized into a specialized architecture designed specifically for this game.

So instead of deleting the old implementations, they are kept as separate branches.

The result is basically:

```text
Prototype
   │
   ▼
CLI
   │
   │  "This is getting bigger."
   ▼
MVC + VSA
   │
   │  "Why did I do this?"
   ▼
RDMGA
   │
   ▼
Current Remastered Architecture
```

This makes the repository useful as both a **game project** and an **architecture evolution case study**.

---

# Project Evolution

## 1. CLI Prototype

**Architecture:** Simple Java CLI

The project originally started as a small terminal-based interaction game.

The prototype focused almost entirely on proving that the gameplay loop worked:

```text
Dialogue
   ↓
Interaction
   ↓
Stat Changes
   ↓
More Dialogue
   ↓
Ending
```

The interface was intentionally simple:

* Terminal input
* ASCII art
* Dialogue
* Gifts
* Basic interaction
* Basic stat management

It was essentially a proof of concept.

No elaborate architecture.

No elaborate UI.

Just:

> **"Can I make this stupid idea into a functioning game?"**

Apparently, yes.

---

## 2. MVC + VSA

**Architecture:** MVC + VSA

Eventually the project grew beyond what the CLI prototype could comfortably handle.

The game gained:

* Graphical UI
* Dialogue presentation
* Inventory
* Character-specific content
* Audio
* Sprites
* Multiple interaction types
* More complex game state
* More content-specific behavior

At this point, a more structured architecture was introduced using a combination of **MVC** and **VSA**.

This was the first major attempt to turn the prototype into something resembling a properly structured application.

It worked.

Mostly.

And then the architecture started becoming its own problem.

The branch remains intentionally preserved because it represents an important stage of the project's development.

It is not here because it is the final architecture.

It is here because **it happened.**

---

## 3. RDMGA

**Architecture:** RDMGA
**Full name:** *Reido's Domain-oriented Monolith Game Architecture*

The remastered version takes a different approach.

Instead of trying to force the game into a generic architectural pattern, the architecture was redesigned around the actual domains and requirements of the game.

The result is **RDMGA**.

The remastered implementation separates major concerns such as:

```text
Content
Modules
Persistence
Shared
UI
```

with domain-specific organization for:

```text
Gameplay
Prinzessins
Stats
Dialogue
Inventory
Talk
Tease
Feed
```

The architecture is intentionally specialized for the project's requirements rather than being presented as a universal software architecture.

The detailed architecture documentation will live in the **RDMGA branch**.

---

# Evolution At A Glance

| Aspect            | CLI                         | MVC + VSA                | RDMGA                      |
| ----------------- | --------------------------- | ------------------------ | -------------------------- |
| Interface         | Terminal                    | Desktop GUI              | Desktop GUI                |
| Input             | Keyboard                    | Mouse + Keyboard         | Mouse + Keyboard           |
| Game State        | Simple                      | Structured               | Structured + persistent    |
| Dialogue          | Basic                       | Expanded                 | Domain-oriented            |
| Inventory         | Basic                       | Specialized              | Specialized                |
| Audio             | None                        | Dedicated audio modules  | Dedicated audio modules    |
| Character Content | Prototype                   | Expanded                 | Character-specific domains |
| Persistence       | —                           | None                     | Save & Load                |
| Architecture      | Simple procedural structure | "MVC + VSA"-ish          | RDMGA                      |
| Purpose           | Proof of concept            | Architectural experiment | Remastered implementation  |

---

# Current Main Branch

The `main` branch intentionally contains **build artifacts rather than the complete source tree**.

Current builds include:

### CLI Prototype

```text
Serve Deine Prinzessin CLI.jar
```

The original terminal-based prototype.

### MVC + VSA

```text
Serve Deine Prinzessin GUI.jar
```

The graphical implementation built around the MVC + VSA experiment.

### RDMGA Remastered

```text
Serve Deine Prinzessin RDMGA-Desktop.jar
```

The current remastered desktop implementation using RDMGA.

---

# How To Explore The Project

If you're primarily interested in the **game**, download one of the `.jar` builds from `main`.

If you're interested in the **development process**, explore the branches in this order:

```text
CLI
 ↓
MVC + VSA
 ↓
RDMGA
```

This order is recommended because the architecture makes much more sense when viewed as an evolution rather than as three unrelated implementations.

### Suggested exploration

**1. CLI**

Look at how the original gameplay was represented when the project was still tiny.

**2. MVC + VSA**

Look at what changed once the game acquired a real graphical interface and more complex content.

**3. RDMGA**

Look at how the project was reorganized after the previous architecture became too cumbersome for the actual domain.

---

# Gameplay Overview

The game revolves around maintaining three statistics:

```text
Hunger
Affection
Insanity
```

Interactions can modify these values.

The main interaction categories include:

### Talk

Talk to the selected Prinzessin and choose from available topics.

Some conversations become available based on the current game state.

### Tease

A repeatable interaction whose results depend on the current state of the relationship and hunger.

It can be useful.

It can also be a terrible idea.

### Give

Give an item to the Prinzessin.

Gifts are consumed when used and their effects depend on the character's preferences.

### Use

Use a consumable item.

This is the **chaos corner**.

The exact consequences are not always obvious.

### Feed

Feed the Prinzessin.

Feeding can affect the game state depending on the current Hunger and Affection values.

---

# Endings

Each Prinzessin currently has **five possible endings**.

The endings are triggered by extreme stat states.

```text
Hunger
 └── 0%   → Ending

Affection
 ├── 0%   → Ending
 └── 100% → Ending

Insanity
 ├── 0%   → Ending
 └── 100% → Ending
```

The game ends immediately when a relevant condition is reached.

The endings are intentionally not all straightforward.

One thing is certain:

> **Hunger reaching 0% is bad.**

For players who want to explore more content, the objective is therefore simple:

> **Keep the numbers away from the edges.**

---

# Controls

### Mouse

Used for:

* Home screen navigation
* Prinzessin selection
* Game interaction
* Inventory
* Dialogue choices
* Settings

### Keyboard

**Enter**

* Advance dialogue
* Confirm selections where applicable
* Hold Enter to fast-forward dialogue

There is no traditional pause requirement during gameplay. The game state does not continuously change while idle.

---

# Technical Information

| Category     | Details                 |
| ------------ | ----------------------- |
| Language     | Java 26                 |
| GUI          | Swing / Java2D          |
| Engine       | None                    |
| Build System | None                    |
| IDE          | IntelliJ IDEA           |
| Platform     | Windows / Linux         |
| Game Type    | Single-player / Offline |
| Architecture | Version-dependent       |
| Save System  | Available in RDMGA      |
| Audio Format | OGG Vorbis              |

The remastered version also uses:

* Jackson for persistence/data handling
* Java Vorbis support for `.ogg` audio

Dependencies are included with the project builds where applicable.

---

# Remastered Version

The RDMGA implementation is the current remastered version of the game.

Compared to the earlier implementations, it introduces:

* Reworked backend architecture
* Domain-oriented content organization
* Character-specific content modules
* Dedicated dialogue handling
* Dedicated interaction behavior
* Persistence layer
* Save / Load functionality
* Expanded asset management
* Dedicated audio channels
* More structured runtime data
* More scalable content organization

The gameplay itself remains recognizably the same.

The biggest difference is **how the game is implemented internally**.

---

# Architecture

Architecture documentation will be added to the RDMGA branch.

For now, the repository intentionally keeps the architectural history visible through the separate branches.

---

# Development

This project was developed solo.

```text
Me, Myself, and I.
```

It started as a small Java experiment and gradually became a larger project involving:

* GUI programming
* Game state management
* Dialogue systems
* Content organization
* Persistence
* Audio systems
* Asset management
* Architecture design
* Refactoring
* And several questionable architectural decisions

The project is primarily built for **fun, experimentation, and learning**, while also serving as a Java/Swing portfolio project.

---

# Why Keep The Old Versions?

Because deleting them would defeat the point.

The older versions demonstrate:

* What the project looked like before it became complicated
* Why certain abstractions were introduced
* Where those abstractions started becoming problematic
* How the architecture changed in response
* How the final architecture was derived from the game's actual requirements

In other words:

> **The branches are part of the documentation.**

The code itself tells the story.

---

# Copyright & Assets

This project contains copyrighted character names, designs, audio, sprites, and other assets derived from or inspired by existing franchises.

The respective characters and franchises belong to their original copyright holders.

This repository is **not an open-source asset pack** and the included game assets are not licensed for redistribution, reuse, or commercial exploitation.

---

# License

No open-source license is provided.

All rights to the original copyrighted assets and third-party character properties remain with their respective rights holders.

The original project code is provided for viewing and demonstration purposes only unless otherwise stated.

---

# Disclaimer

**Serve Deine Prinzessin** is an unofficial fan project and is not affiliated with, endorsed by, or sponsored by the respective copyright holders of the referenced franchises.

This project was created for experimentation, entertainment, and educational/portfolio purposes.

---

# Final Note

This repository is probably best understood as **three versions of the same joke becoming progressively more serious.**

It started with:

```text
"Let's make a dumb CLI game."
```

Then became:

```text
"Okay, let's architect this properly."
```

Then became:

```text
"Okay, the architecture is the problem."
```

And finally:

```text
"Fine. I'll build an architecture specifically for this thing."
```

That's **Serve Deine Prinzessin**.

A small Java game that accidentally became an architecture evolution project.

**Enjoy the descent. 😂**