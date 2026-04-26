---
title: "Snake Xenzia Revival:  Recreation of Nokia's Iconic Arcade Classic"
author: "Providence (Thusa) Thusabantu and Munyaradzi (Munya) Masvaure"
date: "2026-04-26"
format: 
  html:
    toc: true
    toc-depth: 2
    number-sections: true
***

## Team Members

- **Munya** – UI/Graphics Specialist
- **Thusa** – Testing & Documentation Lead

***

## Short Project Description

Inspiration: Snake Xenzia is a iconic,arcade game originating from Nokia mobile phones, where players navigate a growing snake to consume food items while avoiding walls and the snake's own tail. The goal is to maximize the score by growing the longest snake possible at increasingly faster speeds

**Snake Xenzia Revival** recreates the legendary Nokia Snake game where players control a growing snake that eats food pellets while avoiding walls and self-collision, with speed increasing as the snake grows longer for maximum score.

***

## Anticipated Areas of Practice and Growth 

- **Nostalgia Factor**: Snake Xenzia defined mobile gaming for a generation—50+ million Nokia phones shipped with it
- **Perfect Learning Project**: Teaches game loops, collision detection, event handling, and data structures in accessible Python
- **Technical Merit**: Real-time input processing and 60 FPS rendering demonstrate core programming proficiency
- **Portfolio Quality**: Polished, playable game showcases both coding skills and UI/UX understanding
- **Universal Appeal**: Simple to learn, impossible to master—perfect for demoing to any audience

***

## Technical Implementation Plan

| Component | Technologies | Key Challenges |
|-----------|--------------|---------------|
| **Game Loop** | `pygame`, 60 FPS timer | Smooth animation, responsive controls |
| **Snake Movement** | List/deque of (x,y) coordinates | Efficient body tracking, collision with self |
| **Collision Detection** | Rectangle overlap checks | Walls, self, food boundaries |
| **Food Generation** | Random spawn in free space | Avoid spawning on snake body |
| **UI/Scoring** | Pygame fonts, sprites | High score persistence to file |
| **Controls** | Arrow keys/WASD | Real-time input without lag |


***

## Communication Plan

| Element | Details |
|---------|---------|
| **Primary Channel** | Group Chat|
| **Code Collaboration** | Shared GitHub repo with Issues & Projects |
| **Weekly Check-ins** | Wednesdays 7PM (30 mins) |
| **Response Time** | 24 hours weekdays, 48 hours weekends |
| **Progress Tracking** | GitHub Project board with milestones |

***

## Roles

| Role | Team Member | Responsibilities |
|------|-------------|------------------|
| **Graphics/UI** | **Munya** | Visual design, sprites, smooth animations |
| **Testing/Docs** | **Thusa** | Bug hunting, documentation, deployment |

***

## Rough Implementation Plan

### Phase 1: Core Mechanics (Week 1)
| Task | Due Date | Lead |
|------|----------|------|
| Set up pygame environment & game window | Apr 28 | Thusa |
| Implement basic snake movement | Apr 29 | Thusa |
| Add food generation & eating | Apr 30 | Thusa |

### Phase 2: Game Logic (Week 2)
| Task | Due Date | Lead |
|------|----------|------|
| Collision detection (walls/self) | May 1 | Munya |
| Progressive speed increases | May 2 | Munya |
| Game over/restart logic | May 3 | Thusa |

### Phase 3: Polish & Features (Week 3)
| Task | Due Date | Lead |
|------|----------|------|
| High score persistence | May 5 | Munya |
| Smooth graphics & animations | May 6 | Munya |
| Sound effects (optional) | May 7 | All |

### Phase 4: Demo & Documentation
| Task | Due Date | Lead |
|------|----------|------|
| Playtesting & bug fixes | May 8 | Munya |
| README with screenshots | May 9 | Thusa |
| Demo video recording | May 10 | Thusa |

