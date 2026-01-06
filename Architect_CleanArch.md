# 🏗️ Relacionamentos em Clean Architecture

> Guia prático para implementar relacionamentos entre entidades seguindo Clean Architecture

---

## 📚 Índice

1. [📖 Conceitos Fundamentais](#-conceitos-fundamentais)
2. [🔍 Análise do Projeto Atual](#-análise-do-projeto-atual)
3. [🛠️ Plano de Correção](#️-plano-de-correção)
4. [📝 Exemplos de Uso](#-exemplos-de-uso)

---

## 📖 Conceitos Fundamentais

### Fluxo da Clean Architecture

```
┌────────────────────────────────────────────────────────────────┐
│                         CLIENT (HTTP)                          │
└────────────────────────────┬───────────────────────────────────┘
                             │
                             ▼
┌────────────────────────────────────────────────────────────────┐
│  PRESENTATION LAYER (Controllers + DTOs)                       │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │ RestaurantController                                     │  │
│  │  • Recebe: CreateRestaurantRequest (DTO)                 │  │
│  │  • Converte: DTO → Parâmetros                            │  │
│  │  • Chama: Use Case                                       │  │
│  │  • Retorna: RestaurantResponse (DTO)                     │  │
│  └──────────────────────────────────────────────────────────┘  │
└────────────────────────────┬───────────────────────────────────┘
                             │
                             ▼
┌────────────────────────────────────────────────────────────────┐
│  APPLICATION LAYER (Use Cases + Ports)                         │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │ CreateRestaurantUseCase                                  │  │
│  │  1. Valida: userRepository.findById() para cada owner    │  │
│  │  2. Cria: Restaurant.create() (domain entity)            │  │
│  │  3. Persiste: restaurantRepository.save()                │  │
│  │  4. Retorna: Restaurant (domain)                         │  │
│  └──────────────────────────────────────────────────────────┘  │
│                                                                │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │ RestaurantRepository (PORT/Interface)                    │  │
│  │  • save(Restaurant): Restaurant                          │  │
│  │  • findById(Long): Restaurant                            │  │
│  └──────────────────────────────────────────────────────────┘  │
└────────────────────────────┬───────────────────────────────────┘
                             │
                             ▼
┌────────────────────────────────────────────────────────────────┐
│  DOMAIN LAYER (Entities + Business Rules)                      │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │ Restaurant.create()                                      │  │
│  │  • Valida: regras de negócio                             │  │
│  │  • Cria: instância com Set<User>, Set<OpeningHours>      │  │
│  │  • Zero dependência de frameworks                        │  │
│  └──────────────────────────────────────────────────────────┘  │
└────────────────────────────────────────────────────────────────┘
                             │
                             ▼
┌────────────────────────────────────────────────────────────────┐
│  INFRASTRUCTURE LAYER (Adapters + JPA + Database)              │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │ RestaurantRepositoryAdapter (implementa PORT)            │  │
│  │                                                          │  │
│  │  save(Restaurant):                                       │  │
│  │    1. Converte: Restaurant → RestaurantEntity            │  │
│  │    2. Salva: restaurantJpaRepository.save()              │  │
│  │    3. Salva: userRestaurantJpaRepository.save() (loop)   │  │
│  │    4. Salva: openingHoursJpaRepository.save() (loop)     │  │
│  │    5. Busca: findById() para retornar completo           │  │
│  │                                                          │  │
│  │  findById(Long):                                         │  │
│  │    1. Query: restaurantJpaRepository.findById()          │  │
│  │    2. Query: userRestaurantJpaRepository.findBy...()     │  │
│  │    3. Query: userJpaRepository.findById() (loop)         │  │
│  │    4. Query: openingHoursJpaRepository.findBy...()       │  │
│  │    5. Monta: Restaurant.create() com objetos completos   │  │
│  └──────────────────────────────────────────────────────────┘  │
│                                                                │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │ JPA Repositories                                         │  │
│  │  • RestaurantJpaRepository                               │  │
│  │  • UserRestaurantJpaRepository                           │  │
│  │  • OpeningHoursJpaRepository                             │  │
│  │  • UserJpaRepository                                     │  │
│  └──────────────────────────────────────────────────────────┘  │
│                                                                │
│  ┌──────────────────────────────────────────────────────────┐  │
│  │ JPA Entities (apenas dados + FKs)                        │  │
│  │  • RestaurantEntity (id, name, address, cuisine)         │  │
│  │  • UserRestaurantEntity (userId, restaurantId)           │  │
│  │  • OpeningHoursEntity (id, restaurantId, dayOfWeek...)   │  │
│  │  • UserEntity (id, name, email...)                       │  │
│  └──────────────────────────────────────────────────────────┘  │
└────────────────────────────┬───────────────────────────────────┘
                             │
                             ▼
┌────────────────────────────────────────────────────────────────┐
│                      DATABASE (PostgreSQL)                     │
│  • restaurants (id, name, address, cuisine_type)               │
│  • user_restaurant (id, user_id, restaurant_id)                │
│  • opening_hours (id, restaurant_id, day_of_week, ...)         │
│  • users (id, name, email, ...)                                │
└────────────────────────────────────────────────────────────────┘
```

### Regra de Ouro

```
┌─────────────────────────────────────────────────────────────┐
│ Domain (Entidade Principal)                                 │
│ └─> Tem OBJETOS COMPLETOS das entidades relacionadas        │
│                                                             │
│ Domain (Entidade Filha)                                     │
│ └─> Tem apenas ID da entidade pai (evita circular)          │
│                                                             │
│ Infrastructure (JPA)                                        │
│ └─> Tem apenas FOREIGN KEYS (sem @ManyToMany, @OneToMany)   │
│                                                             │
│ Repository                                                  │
│ └─> Faz MÚLTIPLAS QUERIES e monta objetos de domínio        │
└─────────────────────────────────────────────────────────────┘
```

### Por Camada

#### 1️⃣ Domain Layer

**Entidade Principal (Restaurant):**
```java
public class Restaurant {
    private Long id;
    private Set<User> restaurantOwners;        // ✅ Objetos completos
    private Set<OpeningHours> openingHoursSet; // ✅ Objetos completos
}
```

**Entidade Filha (OpeningHours):**
```java
public class OpeningHours {
    private Long id;
    private Long restaurantId;  // ✅ Apenas FK (evita circular)
    private DayOfWeek dayOfWeek;
    private LocalTime openTime;
}
```

**Por quê?**
- Principal tem objetos → permite regras de negócio ricas
- Filha tem FK → evita referência circular

---

#### 2️⃣ Infrastructure Layer

**JPA Entity (apenas dados e FKs):**
```java
@Entity
@Table(name = "restaurants")
public class RestaurantEntity {
    @Id
    private Long id;
    private String name;
    // ❌ SEM @ManyToMany, @OneToMany, @ManyToOne
}

@Entity
@Table(name = "opening_hours")
public class OpeningHoursEntity {
    @Id
    private Long id;
    
    @Column(name = "restaurant_id")
    private Long restaurantId;  // ✅ Apenas FK
    // ❌ SEM @ManyToOne
}

@Entity
@Table(name = "user_restaurant")  // Tabela de join
public class UserRestaurantEntity {
    @Id
    private Long id;
    private Long userId;
    private Long restaurantId;
}
```

**Por quê?**
- Evita LazyInitializationException
- Evita N+1 queries
- Desacopla de JPA

---

#### 3️⃣ Repository Layer

**Salvar (3 etapas):**
```java
@Transactional
public Restaurant save(Restaurant restaurant) {
    // 1. Salva dados básicos
    RestaurantEntity entity = new RestaurantEntity();
    entity.setName(restaurant.getName());
    RestaurantEntity saved = restaurantJpaRepository.save(entity);
    
    // 2. Salva relacionamentos
    for (User owner : restaurant.getRestaurantOwners()) {
        UserRestaurantEntity ur = new UserRestaurantEntity();
        ur.setUserId(owner.getId());
        ur.setRestaurantId(saved.getId());
        userRestaurantJpaRepository.save(ur);
    }
    
    // 3. Salva filhos
    for (OpeningHours oh : restaurant.getOpeningHoursSet()) {
        OpeningHoursEntity ohEntity = new OpeningHoursEntity();
        ohEntity.setRestaurantId(saved.getId());
        ohEntity.setDayOfWeek(oh.getDayOfWeek());
        openingHoursJpaRepository.save(ohEntity);
    }
    
    return findById(saved.getId());
}
```

**Buscar (4 queries):**
```java
public Restaurant findById(Long id) {
    // 1. Busca Restaurant
    RestaurantEntity entity = restaurantJpaRepository.findById(id).orElseThrow();
    
    // 2. Busca relacionamentos
    List<UserRestaurantEntity> userRestaurants = 
        userRestaurantJpaRepository.findByRestaurantId(id);
    
    // 3. Busca Users completos
    Set<User> owners = userRestaurants.stream()
        .map(ur -> userJpaRepository.findById(ur.getUserId()).orElseThrow())
        .map(UserMapper::toDomainEntity)
        .collect(Collectors.toSet());
    
    // 4. Busca OpeningHours
    List<OpeningHoursEntity> ohEntities = 
        openingHoursJpaRepository.findByRestaurantId(id);
    Set<OpeningHours> openingHours = ohEntities.stream()
        .map(oh -> new OpeningHours(oh.getId(), oh.getRestaurantId(), 
            oh.getDayOfWeek(), oh.getOpenTime(), oh.getCloseTime()))
        .collect(Collectors.toSet());
    
    // 5. Monta entidade de domínio
    return Restaurant.create(entity.getId(), entity.getName(), 
        entity.getAddress(), entity.getCuisineType(), owners, openingHours);
}
```

**Por quê?**
- Queries explícitas e controláveis
- Fácil de debugar
- Múltiplas queries são OK (priorize clareza)

---

#### 4️⃣ Use Case Layer

**Valida relacionamentos:**
```java
public Restaurant execute(String name, List<Long> ownerIds, Set<OpeningHours> hours) {
    // 1. Valida se Users existem
    Set<User> owners = new HashSet<>();
    for (Long ownerId : ownerIds) {
        User user = userRepository.findById(ownerId);
        if (user == null) {
            throw new IllegalArgumentException("User not found: " + ownerId);
        }
        owners.add(user);
    }
    
    // 2. Cria e persiste
    Restaurant restaurant = Restaurant.create(name, address, cuisine, owners, hours);
    return restaurantRepository.save(restaurant);
}
```

---

## 🔍 Análise do Projeto Atual

### ✅ O que está CORRETO

#### Restaurant.java
```java
public class Restaurant {
    private Set<User> restaurantOwners;        // ✅ Objetos completos
    private Set<OpeningHours> openingHoursSet; // ✅ Objetos completos
    
    public static Restaurant create(...) {
        validateRestaurant(...);  // ✅ Validações no domínio
        // ...
    }
}
```
**Status:** ✅ Perfeito! Não precisa alterar.

---

### ❌ O que está ERRADO

#### 1. OpeningHours.java

**ATUAL:**
```java
public class OpeningHours {
    // ❌ Falta id
    // ❌ Falta restaurantId
    DayOfWeek dayOfWeek;
    LocalTime openTime;
    LocalTime closeTime;
}
```

**PROBLEMA:** Sem ID, não consegue identificar registro único. Sem restaurantId, cria referência circular.

---

#### 2. MenuItem.java

**ATUAL:**
```java
public class MenuItem {
    private Restaurant restaurant;  // ❌ Referência circular
}
```

**PROBLEMA:** Cria dependência circular Restaurant ↔ MenuItem.

---

#### 3. RestaurantEntity.java

**ATUAL:**
```java
@Entity
public class RestaurantEntity {
    @ManyToMany(mappedBy = "restaurants", fetch = FetchType.LAZY)
    private Set<UserEntity> users;  // ❌ Lazy loading
    
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
    private Set<OpeningHoursEntity> openingHours;  // ❌ Acoplamento
}
```

**PROBLEMA:** Causa LazyInitializationException e N+1 queries.

---

#### 4. OpeningHoursEntity.java

**ATUAL:**
```java
@Entity
public class OpeningHoursEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private RestaurantEntity restaurant;  // ❌ Lazy loading
}
```

**PROBLEMA:** Acoplamento com JPA, lazy loading.

---

#### 5. RestaurantMapper.java

**ATUAL:**
```java
public static RestaurantEntity toPersistenceEntity(Restaurant restaurant) {
    // ❌ Tenta converter relacionamentos completos
    Set<UserEntity> userEntities = restaurantOwners.stream()
        .map(UserMapper::toPersistenceEntity)
        .collect(Collectors.toSet());
    restaurantEntity.setUsers(userEntities);
    
    // ❌ Referência bidirecional
    for (OpeningHoursEntity opening : openingHoursEntities) {
        opening.setRestaurant(restaurantEntity);
    }
}
```

**PROBLEMA:** Mapper não deve ter lógica de relacionamento. Isso é responsabilidade do Repository.

---

### 📊 Resumo dos Problemas

| Arquivo | Problema | Impacto |
|---------|----------|---------|
| OpeningHours.java | Falta `id` e `restaurantId` | Não consegue persistir/buscar corretamente |
| MenuItem.java | Tem `Restaurant` ao invés de `restaurantId` | Referência circular |
| RestaurantEntity.java | Tem `@ManyToMany` e `@OneToMany` | LazyInitializationException |
| UserEntity.java | Tem `@ManyToMany` | LazyInitializationException |
| OpeningHoursEntity.java | Tem `@ManyToOne` | Lazy loading |
| MenuItemEntity.java | Tem `@ManyToOne` | Lazy loading |
| UserRestaurantEntity.java | ❌ Não existe | Não consegue persistir relacionamento |
| RestaurantMapper.java | Lógica de relacionamento | Responsabilidade errada |
| RestaurantRepositoryAdapter.java | Não implementa queries separadas | Não funciona corretamente |

---

## 🛠️ Plano de Correção

### Ordem de Implementação

```
FASE 1: Domain (mais fácil)
  ├─ 1. Corrigir OpeningHours
  └─ 2. Corrigir MenuItem

FASE 2: Infrastructure - Entities
  ├─ 3. Criar UserRestaurantEntity
  ├─ 4. Remover @ManyToMany de RestaurantEntity
  ├─ 5. Remover @ManyToMany de UserEntity
  ├─ 6. Remover @ManyToOne de OpeningHoursEntity
  └─ 7. Remover @ManyToOne de MenuItemEntity

FASE 3: Infrastructure - Repositories
  ├─ 8. Criar UserRestaurantJpaRepository
  └─ 9. Criar OpeningHoursJpaRepository

FASE 4: Repository Adapter
  ├─ 10. Refatorar save() (3 etapas)
  └─ 11. Implementar findById() (4 queries)

FASE 5: Mappers
  ├─ 12. Simplificar RestaurantMapper
  └─ 13. Atualizar OpeningHoursMapper

FASE 6: Application
  ├─ 14. Adicionar findById() no port
  └─ 15. Criar FindRestaurantUseCase

FASE 7: Presentation
  └─ 16. Adicionar GET /api/restaurants/{id}
```

---

### FASE 1: Corrigir Domain

#### 1.1 OpeningHours.java

**Substituir por:**
```java
package br.com.fiap.clean_arch.domain.entities;

import lombok.Getter;
import java.time.DayOfWeek;
import java.time.LocalTime;

@Getter
public class OpeningHours {
    private Long id;
    private Long restaurantId;
    private DayOfWeek dayOfWeek;
    private LocalTime openTime;
    private LocalTime closeTime;

    // Construtor completo (para buscar do banco)
    public OpeningHours(Long id, Long restaurantId, DayOfWeek dayOfWeek, 
                        LocalTime openTime, LocalTime closeTime) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.dayOfWeek = dayOfWeek;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
    
    // Construtor para criação (sem id)
    public OpeningHours(Long restaurantId, DayOfWeek dayOfWeek, 
                        LocalTime openTime, LocalTime closeTime) {
        this(null, restaurantId, dayOfWeek, openTime, closeTime);
    }
}
```

#### 1.2 MenuItem.java

**Alterar:**
```java
// ANTES
private Restaurant restaurant;

// DEPOIS
private Long restaurantId;
```

**Atualizar método create:**
```java
public static MenuItem create(Long id, String name, Long restaurantId, 
                              BigDecimal price, boolean deliveryItem, 
                              String photoUrl, ZonedDateTime lastUpdate) {
    price = price.setScale(2, RoundingMode.HALF_UP);
    validateMenuItem(name, price, photoUrl);
    
    MenuItem menuItem = new MenuItem();
    menuItem.id = id;
    menuItem.name = name;
    menuItem.restaurantId = restaurantId;  // ✅ Mudança aqui
    menuItem.price = price;
    menuItem.deliveryItem = deliveryItem;
    menuItem.photoUrl = photoUrl;
    menuItem.lastUpdate = lastUpdate;
    return menuItem;
}
```

---

### FASE 2: Corrigir JPA Entities

#### 2.1 Criar UserRestaurantEntity.java

**Criar arquivo:** `infrastructure/persistence/entity/UserRestaurantEntity.java`

```java
package br.com.fiap.clean_arch.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "user_restaurant")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "user_id", nullable = false)
    private Long userId;
    
    @Column(name = "restaurant_id", nullable = false)
    private Long restaurantId;
}
```

#### 2.2 RestaurantEntity.java

**Remover:**
```java
// ❌ REMOVER TUDO ISSO
@ManyToMany(mappedBy = "restaurants", fetch = FetchType.LAZY)
private Set<UserEntity> users = new HashSet<>();

@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
private Set<OpeningHoursEntity> openingHours = new HashSet<>();
```

**Manter apenas:**
```java
@Entity
@Table(name = "restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(name = "cuisine_type", nullable = false)
    private String cuisineType;
    
    // ✅ SEM relacionamentos JPA
}
```

#### 2.3 UserEntity.java

**Remover:**
```java
// ❌ REMOVER
@ManyToMany
@JoinTable(
    name = "user_restaurant",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "restaurant_id")
)
private Set<RestaurantEntity> restaurants = new HashSet<>();
```

#### 2.4 OpeningHoursEntity.java

**Alterar:**
```java
// ANTES
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "restaurant_id")
private RestaurantEntity restaurant;

// DEPOIS
@Column(name = "restaurant_id", nullable = false)
private Long restaurantId;
```

#### 2.5 MenuItemEntity.java

**Alterar:**
```java
// ANTES
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "restaurant_id")
private RestaurantEntity restaurant;

// DEPOIS
@Column(name = "restaurant_id", nullable = false)
private Long restaurantId;
```

---

### FASE 3: Criar JPA Repositories

#### 3.1 UserRestaurantJpaRepository.java

**Criar arquivo:** `infrastructure/persistence/repository/UserRestaurantJpaRepository.java`

```java
package br.com.fiap.clean_arch.infrastructure.persistence.repository;

import br.com.fiap.clean_arch.infrastructure.persistence.entity.UserRestaurantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRestaurantJpaRepository extends JpaRepository<UserRestaurantEntity, Long> {
    List<UserRestaurantEntity> findByRestaurantId(Long restaurantId);
    List<UserRestaurantEntity> findByUserId(Long userId);
}
```

#### 3.2 OpeningHoursJpaRepository.java

**Criar arquivo:** `infrastructure/persistence/repository/OpeningHoursJpaRepository.java`

```java
package br.com.fiap.clean_arch.infrastructure.persistence.repository;

import br.com.fiap.clean_arch.infrastructure.persistence.entity.OpeningHoursEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpeningHoursJpaRepository extends JpaRepository<OpeningHoursEntity, Long> {
    List<OpeningHoursEntity> findByRestaurantId(Long restaurantId);
}
```

---

### FASE 4: Refatorar Repository Adapter

#### 4.1 Atualizar RestaurantRepository (Port)

**Arquivo:** `application/ports/RestaurantRepository.java`

**Adicionar método:**
```java
public interface RestaurantRepository {
    Restaurant save(Restaurant restaurant);
    Restaurant findById(Long id);  // ✅ Adicionar
}
```

#### 4.2 Refatorar RestaurantRepositoryAdapter

**Arquivo:** `infrastructure/adapters/RestaurantRepositoryAdapter.java`

**Substituir por:**
```java
package br.com.fiap.clean_arch.infrastructure.adapters;

import br.com.fiap.clean_arch.application.ports.RestaurantRepository;
import br.com.fiap.clean_arch.domain.entities.OpeningHours;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.*;
import br.com.fiap.clean_arch.infrastructure.persistence.repository.*;
import br.com.fiap.clean_arch.presentation.mappers.UserMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RestaurantRepositoryAdapter implements RestaurantRepository {
    private final RestaurantJpaRepository restaurantJpaRepository;
    private final UserRestaurantJpaRepository userRestaurantJpaRepository;
    private final OpeningHoursJpaRepository openingHoursJpaRepository;
    private final UserJpaRepository userJpaRepository;

    public RestaurantRepositoryAdapter(
            RestaurantJpaRepository restaurantJpaRepository,
            UserRestaurantJpaRepository userRestaurantJpaRepository,
            OpeningHoursJpaRepository openingHoursJpaRepository,
            UserJpaRepository userJpaRepository) {
        this.restaurantJpaRepository = restaurantJpaRepository;
        this.userRestaurantJpaRepository = userRestaurantJpaRepository;
        this.openingHoursJpaRepository = openingHoursJpaRepository;
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        // 1. Salva Restaurant (apenas dados básicos)
        RestaurantEntity entity = new RestaurantEntity();
        entity.setId(restaurant.getId());
        entity.setName(restaurant.getName());
        entity.setAddress(restaurant.getAddress());
        entity.setCuisineType(restaurant.getCuisineType());
        RestaurantEntity saved = restaurantJpaRepository.save(entity);
        
        // 2. Salva relacionamentos User-Restaurant
        for (User owner : restaurant.getRestaurantOwners()) {
            UserRestaurantEntity ur = new UserRestaurantEntity();
            ur.setUserId(owner.getId());
            ur.setRestaurantId(saved.getId());
            userRestaurantJpaRepository.save(ur);
        }
        
        // 3. Salva OpeningHours
        for (OpeningHours oh : restaurant.getOpeningHoursSet()) {
            OpeningHoursEntity ohEntity = new OpeningHoursEntity();
            ohEntity.setRestaurantId(saved.getId());
            ohEntity.setDayOfWeek(oh.getDayOfWeek());
            ohEntity.setOpenTime(oh.getOpenTime());
            ohEntity.setCloseTime(oh.getCloseTime());
            openingHoursJpaRepository.save(ohEntity);
        }
        
        // 4. Busca dados completos para retornar
        return findById(saved.getId());
    }
    
    @Override
    public Restaurant findById(Long id) {
        // 1. Busca Restaurant
        RestaurantEntity entity = restaurantJpaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Restaurant not found: " + id));
        
        // 2. Busca relacionamentos User-Restaurant
        List<UserRestaurantEntity> userRestaurants = 
            userRestaurantJpaRepository.findByRestaurantId(id);
        
        // 3. Busca Users completos
        Set<User> owners = userRestaurants.stream()
            .map(ur -> userJpaRepository.findById(ur.getUserId()).orElseThrow())
            .map(UserMapper::toDomainEntity)
            .collect(Collectors.toSet());
        
        // 4. Busca OpeningHours
        List<OpeningHoursEntity> ohEntities = 
            openingHoursJpaRepository.findByRestaurantId(id);
        Set<OpeningHours> openingHours = ohEntities.stream()
            .map(oh -> new OpeningHours(oh.getId(), oh.getRestaurantId(), 
                oh.getDayOfWeek(), oh.getOpenTime(), oh.getCloseTime()))
            .collect(Collectors.toSet());
        
        // 5. Monta entidade de domínio
        return Restaurant.create(entity.getId(), entity.getName(), 
            entity.getAddress(), entity.getCuisineType(), owners, openingHours);
    }
}
```

---

### FASE 5: Atualizar Mappers

#### 5.1 Simplificar RestaurantMapper

**Arquivo:** `presentation/mappers/RestaurantMapper.java`

**Remover método `toPersistenceEntity`** (não é mais usado)

**Manter apenas:**
```java
package br.com.fiap.clean_arch.presentation.mappers;

import br.com.fiap.clean_arch.domain.entities.OpeningHours;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import br.com.fiap.clean_arch.domain.entities.User;
import br.com.fiap.clean_arch.infrastructure.persistence.entity.RestaurantEntity;
import br.com.fiap.clean_arch.presentation.dto.OpeningHoursDTO;
import br.com.fiap.clean_arch.presentation.dto.UserDTO;
import br.com.fiap.clean_arch.presentation.dto.response.RestaurantResponse;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class RestaurantMapper {

    // ✅ Manter apenas conversão Domain → DTO
    public static RestaurantResponse toResponse(Restaurant restaurant) {
        Set<OpeningHoursDTO> openingHoursDTOSet = 
            OpeningHoursMapper.toResponseDtoSet(restaurant.getOpeningHoursSet());

        Set<UserDTO> userDTOSet = restaurant.getRestaurantOwners().stream()
            .map(UserMapper::toDTO)
            .collect(Collectors.toSet());

        return new RestaurantResponse(
            restaurant.getId(),
            restaurant.getName(),
            restaurant.getAddress(),
            restaurant.getCuisineType(),
            openingHoursDTOSet,
            userDTOSet
        );
    }
}
```

#### 5.2 Atualizar OpeningHoursMapper

**Arquivo:** `presentation/mappers/OpeningHoursMapper.java`

**Atualizar método `toDomainEntity`:**
```java
public static OpeningHours toDomainEntity(OpeningHoursDTO request) {
    DayOfWeek dayOfWeek = DayOfWeek.valueOf(request.dayOfWeek().toUpperCase());
    LocalTime openTime = LocalTime.parse(request.openTime());
    LocalTime closeTime = LocalTime.parse(request.closeTime());
    
    // ✅ Passa null para restaurantId (será setado no Use Case)
    return new OpeningHours(null, dayOfWeek, openTime, closeTime);
}
```

---

### FASE 6: Atualizar Application Layer

#### 6.1 Criar FindRestaurantUseCase

**Criar arquivo:** `application/usecases/FindRestaurantUseCase.java`

```java
package br.com.fiap.clean_arch.application.usecases;

import br.com.fiap.clean_arch.application.ports.RestaurantRepository;
import br.com.fiap.clean_arch.domain.entities.Restaurant;
import org.springframework.stereotype.Service;

@Service
public class FindRestaurantUseCase {
    private final RestaurantRepository restaurantRepository;

    public FindRestaurantUseCase(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public Restaurant execute(Long id) {
        return restaurantRepository.findById(id);
    }
}
```

---

### FASE 7: Atualizar Presentation Layer

#### 7.1 Adicionar endpoint GET no Controller

**Arquivo:** `presentation/controllers/RestaurantController.java`

**Adicionar:**
```java
@RestController
@RequestMapping("/api/restaurants")
public class RestaurantController {
    private final CreateRestaurantUseCase createRestaurantUseCase;
    private final FindRestaurantUseCase findRestaurantUseCase;  // ✅ Adicionar

    public RestaurantController(
            CreateRestaurantUseCase createRestaurantUseCase,
            FindRestaurantUseCase findRestaurantUseCase) {  // ✅ Adicionar
        this.createRestaurantUseCase = createRestaurantUseCase;
        this.findRestaurantUseCase = findRestaurantUseCase;
    }

    @PostMapping
    public ResponseEntity<RestaurantResponse> create(@Valid @RequestBody CreateRestaurantRequest request) {
        // ... código existente
    }
    
    // ✅ ADICIONAR ESTE MÉTODO
    @GetMapping("/{id}")
    @Operation(summary = "Find restaurant by ID")
    public ResponseEntity<RestaurantResponse> findById(@PathVariable Long id) {
        Restaurant restaurant = findRestaurantUseCase.execute(id);
        return ResponseEntity.ok(RestaurantMapper.toResponse(restaurant));
    }
}
```

---

### Criar Restaurant

**Request:**
```json
POST /api/restaurants
{
  "name": "Pizza Place",
  "address": "Rua Augusta, 123",
  "cuisineType": "Italian",
  "userIds": [1, 2],
  "openingHours": [
    {"dayOfWeek": "MONDAY", "openTime": "10:00", "closeTime": "22:00"}
  ]
}
```

**Response:**
```json
{
  "id": 10,
  "name": "Pizza Place",
  "owners": [
    {"id": 1, "name": "João", "email": "joao@email.com"}
  ],
  "openingHours": [
    {"dayOfWeek": "MONDAY", "openTime": "10:00", "closeTime": "22:00"}
  ]
}
```

---

## 🎯 Checklist

### Domain
- [x] Restaurant tem objetos completos
- [ ] OpeningHours tem `id` e `restaurantId`
- [ ] MenuItem tem `restaurantId` (não `Restaurant`)

### Infrastructure
- [ ] RestaurantEntity sem `@ManyToMany`
- [ ] UserEntity sem `@ManyToMany`
- [ ] OpeningHoursEntity sem `@ManyToOne`
- [ ] MenuItemEntity sem `@ManyToOne`
- [ ] UserRestaurantEntity criado

### Repository
- [ ] UserRestaurantJpaRepository criado
- [ ] OpeningHoursJpaRepository criado
- [ ] RestaurantRepositoryAdapter.save() com 3 etapas
- [ ] RestaurantRepositoryAdapter.findById() com 4 queries

### Application
- [ ] RestaurantRepository (port) tem findById()
- [ ] FindRestaurantUseCase criado

### Presentation
- [ ] GET /api/restaurants/{id} implementado

---

**Última atualização:** 06/01/2026
