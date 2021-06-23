@echo =================================
@echo ===========Flowcontrol===========
@echo =================================

@echo Checking docker
docker --version
docker-compose --version
docker ps
docker-compose down --rmi all

@echo Pulled Flowcontrol application...
cd RP-Flowcontrol

@echo Building Flowcontrol application...
docker-compose build

@echo Running Flowcontrol application...
docker-compose up

@echo
@echo Flowcontrol has started. Go to http://localhost:8080 to access

pause