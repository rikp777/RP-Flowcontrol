# Deployment 
Before running any of the scripts, 
please make sure you have installed docker & git / bash on your OS. 

- [Docker win download](https://docs.docker.com/docker-for-windows/install/),
  [Docker dist download](https://docs.docker.com/engine/install/ubuntu/)
- [Git download](https://git-scm.com/downloads)

## Install & Run
First initialization run: install_script.sh
First initialization will take around 8 min depending on the machine.

This script will create all the images and will run them. 

## Run
Run existing initialization run: run_script.sh

This script will automatically close all existing running containers before starting.

To check the services that are up check your docker containers or visit eureka service.

## Site
### Frontend client
- [Flowcontrol client | http://localhost:8080](http://localhost:8080)

### Api services
- [Article-service-api | http://localhost:7078](http://localhost:7078)
- [Farmer-service-api | http://localhost:7071](http://localhost:7071)
- [Production-service-api | http://localhost:7073](http://localhost:7073)
- [Transport-service-api | http://localhost:7072](http://localhost:7072)
- [Auth-service-api | http://localhost:7070](http://localhost:7070)

### Gateway and others 
- [Gateway-service-api | http://localhost:8762](http://localhost:8762)
- [Eureka-service-api | http://localhost:8761](http://localhost:8761)

- [Redis-service-api | http://localhost:6379](http://localhost:6379)
- [Kafka-service-api | http://localhost:2181](http://localhost:2181)
  
### Database
- [Database-service-api | http://localhost:2181](http://localhost:6073)
