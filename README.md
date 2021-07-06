# Flowcontrol
RP-Flowcontrol is a web application design for registration of important processes.
These processes take place at several parts within the supply chain. 
The chains involve for example farming, planning, production, transport, distribution, etc.
The application "Flowcontrol" started as a basic Access application then transformed to a sophisticated monolith and now evolving into
a microservice architecture-based web-application. 
This has been reached by continuously going through many iterations and architectures throughout the time. We try to adapt on all the latest technologies provided by the spring boot framework. Also, through
the experience over the years, we have built up best practices that we think are right. 
We strive after clear documentation of features that have been built.
All software steps are designed based on the needs/requirements of the product flow which the software, at all times, should assist. Assistance
during execution/processing, but also assistance on monitoring during execution.

## Current status backend:
The app has gone through several iterations, and now the current Rest API is written in the PHP Laravel language.
This is done because we prioritized a short-term implementation over long-term functionality.
We know that currently the framework will not be working optimal for the long term, therefore we are rewriting the current back-end in
Java with Spring-boot as framework.

In the Rest API we have chosen to implement "HATEOAS hall". Not many software developers are fond of this, there is not yet a proper implementation
that has the Rest API to the front-end. Still, we are convinced that the chosen path would fit best for the coming years.

## Front-end Architecture
Front-end v2 to v3 fitting to the new Api HateOas Hal model rest Api| front-end
Techniques: Vue, Vuex, Router, Vue CLI, Vue SSR, file structure

The Flowcontrol Api uses HateOas Hal standards via resources.
Hateoas strives to make as few API calls as possible while still keeping the application functional. 
This is also done by state management with VueX.
For our front-end we want to put an emphasis on security. 
That's why we want you to look into this and make the most of your app in terms of security.
Of course we know that every developer is not that fond of testing own application(s). 
Nevertheless we would like to ask you to test your app abundantly using Cypress.

## Modules
### Article
[![Article](https://github.com/rikp777/RP-Flowcontrol/actions/workflows/article.yml/badge.svg)](https://github.com/rikp777/RP-Flowcontrol/actions/workflows/article.yml)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=flowcontrol.article&metric=security_rating)](https://sonarcloud.io/dashboard?id=flowcontrol.article)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=flowcontrol.article&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=flowcontrol.article)

    Registration of items that a producer/farmer can supply to the distribution center.


### Farmer
    Keep records of affiliated farmers at the distribution center and which farmer is delivering product.


### Financial
    
### production
    For splitting and dividing a pallet label with production articles on it. 
    This module ensures that one can keep track of where an item comes from and where it goes. 
### transport
    Registration of transport from production to the production center based on pallet label and waybill with enumeration of pallet labels.

# Development 
This repo has been developed by [Rik Peeters](https://www.linkedin.com/in/rikpeeters-nl/)

Time spend developing this repo by dev: [Rikp777](https://github.com/rikp777)
[![wakatime](https://wakatime.com/badge/github/rikp777/RP-Flowcontrol.svg)](https://wakatime.com/badge/github/rikp777/RP-Flowcontrol)

Inters:
- null

# API Documentation 
How to set up your postman to accept OpenApi can be fount here [postman setup](https://learning.postman.com/docs/integrations/available-integrations/working-with-openAPI/)

To visit and inspect the following links you need to run the docker-compose
- [Article | REST API Endpoints](http://127.0.0.1:8762/article/api/docs.html) 
- [Farmer | REST API Endpoints](http://127.0.0.1:8762/farmer/api/docs.html)
- [Transport | REST API Endpoints](http://127.0.0.1:8762/transport/api/docs.html)
- [Production | REST API Endpoints](http://127.0.0.1:8762/production/api/docs.html)

# Install/Setup 
To run all the service run the following command
- Linux: `DOCKER_BUILDKIT=1 docker-compose up --build`
- Windows: `docker-compose up --build`

Start only database, redis, eureka and kafka:
- `docker-compose -f docker-compose-essential.yml up --build`

If your database doesn't load or seed please remove the already existing docker volumes dir in your root project directory.

## Docker 
To run the above command you need to install [docker](https://docs.docker.com/)

## Deployment
For deployment or development please download the _DEPLOYMENT_ folder.
Please read the readme in the folder.