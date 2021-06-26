# Flowcontrol

## Deployment 
For deployment or development please download the _DEPLOYMENT_ folder.
Please read the readme in the folder.

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

#API Documentation 
How to set up your postman to accept OpenApi can be fount here [postman setup](https://learning.postman.com/docs/integrations/available-integrations/working-with-openAPI/)

To visit and inspect the following links you need to run the docker-compose
- [Article | REST API Endpoints](http://127.0.0.1:8762/article/api/docs.html) 
- [Farmer | REST API Endpoints](http://127.0.0.1:8762/farmer/api/docs.html)

# Install/Setup 
To run all the service run the following command
- Linux: `DOCKER_BUILDKIT=1 docker-compose up --build`
- Windows: `docker-compose up --build`

## Docker 
To run the above command you need to install [docker](https://docs.docker.com/)
