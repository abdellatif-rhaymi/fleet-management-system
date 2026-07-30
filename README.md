# 🚚 Wassel — Fleet & Transport Management System

Application web **full-stack (Java/J2EE)** de gestion intelligente du transport de fret : gestion de flotte, des demandes de livraison et des voyages, avec **optimisation d'itinéraires** et **suivi des chauffeurs en temps réel** sur carte.

---

## 🎯 Contexte

Une entreprise de transport doit coordonner ses **véhicules**, ses **chauffeurs**, les **demandes de livraison** et les **voyages** qui les regroupent — tout en optimisant les trajets pour réduire les délais et les coûts. **Wassel** centralise cette activité dans une application web : un back-office pour l'administrateur (flotte, demandes, voyages, statistiques), un espace dédié au chauffeur (ses voyages, sa position), et un site public de suivi d'expédition.

## ✨ Fonctionnalités

- 📊 **Tableau de bord analytique** : indicateurs clés (voyages, véhicules, colis, taux de livraison), calendrier et statuts en temps réel
- 🚛 **Gestion de flotte** : véhicules (immatriculation, type, capacité, maintenance, disponibilité)
- 📦 **Gestion des demandes** de livraison (adresses, dates, statuts, recherche avancée)
- 🗺️ **Gestion des voyages** : regroupement de demandes, affectation véhicule + chauffeur
- 🧭 **Optimisation d'itinéraire** via l'API **OpenRouteService** (distance et durée du trajet optimal)
- 📍 **Suivi en temps réel** de la position du chauffeur sur la carte
- 👥 **Rôles distincts** : administrateur et chauffeur (espaces séparés)
- 🔐 **Authentification** et gestion des utilisateurs

## 🖼️ Aperçu

| Tableau de bord analytique | Optimisation d'itinéraire |
|:---:|:---:|
| ![Dashboard](docs/03_dashboard.png) | ![Route](docs/07_route_optimization.png) |

| Gestion de flotte | Suivi du chauffeur en temps réel |
|:---:|:---:|
| ![Véhicules](docs/04_vehicles.png) | ![Tracking](docs/08_driver_tracking.png) |

| Demandes de livraison | Gestion des voyages |
|:---:|:---:|
| ![Demandes](docs/05_requests.png) | ![Voyages](docs/06_trips.png) |

| Espace chauffeur | Site public / connexion |
|:---:|:---:|
| ![Chauffeur](docs/09_driver_dashboard.png) | ![Landing](docs/01_landing.png) |

## 🏗️ Architecture

Application **MVC** en Java/J2EE :

```
Navigateur (JSP + Bootstrap)
      │  HTTP
      ▼
Servlets (contrôleurs)  ──►  Modèles + DAO  ──►  MySQL
      │
      └──►  Scripts Python (OpenRouteService + Folium)  →  carte d'itinéraire
```

- **Vue** : pages **JSP** (+ Bootstrap) — back-office et site public
- **Contrôleur** : **Servlets** (`ControleurServlet`, `DemandeServlet`, `VoyageServlet`, `TrajetVoyageservlet`, `UpdateLocationServlet`…)
- **Modèle** : entités (`Vehicule`, `Voyage`, `Trajet`, `Demande`, `Utilisateur`, `Location`) + couche **DAO** (interface + implémentation par entité)
- **Base de données** : **MySQL** (accès via un `SingletonConnection` JDBC)
- **Optimisation d'itinéraire** : scripts Python (`route_optimizer.py`, `route_map_generator.py`, `route_service.py`) appelés depuis les servlets, qui interrogent **OpenRouteService** et génèrent la carte du trajet

## 🛠️ Stack technique

| Domaine | Technologies |
|---|---|
| **Backend** | Java (Servlets, JSP), architecture MVC + DAO |
| **Base de données** | MySQL (JDBC) |
| **Frontend** | JSP · Bootstrap · JavaScript |
| **Cartographie / Itinéraires** | OpenRouteService API · Leaflet · Python (Folium) |
| **Serveur** | Apache Tomcat |

## 📁 Structure du projet

```
src/main/
├── java/
│   ├── entities/          # Modèle : Vehicule, Voyage, Trajet, Demande, Utilisateur, Location
│   ├── <Entity>Dao/       # Couche DAO (interface + implémentation) par entité
│   ├── web/               # Servlets (contrôleurs) + modèles web
│   └── SingletonConnection/  # Connexion JDBC MySQL
└── webapp/
    ├── *.jsp              # Vues (back-office + espace chauffeur + public)
    ├── route_optimizer.py # Optimisation d'itinéraire (OpenRouteService)
    ├── css/ js/ img/      # Ressources statiques
    └── WEB-INF/           # web.xml
```

## 🚀 Installation

```bash
git clone git@github.com:abdellatif-rhaymi/fleet-management-system.git
```
1. Créer une base MySQL nommée `transport` et configurer l'accès dans `SingletonConnection.java`.
2. Renseigner une **clé API OpenRouteService** (placeholder `YOUR_ORS_API_KEY` dans le code) — [openrouteservice.org](https://openrouteservice.org/).
3. Déployer sur **Apache Tomcat** (projet dynamique web) et lancer.
4. Prérequis Python (pour l'optimisation) : `pip install openrouteservice folium`.

## 👤 Auteur

**Abdellatif RHAYMI** — Ingénieur d'État en Informatique (ENSIAS)
[LinkedIn](https://www.linkedin.com/in/abdellatif-rhaymi/) · [GitHub](https://github.com/abdellatif-rhaymi)
