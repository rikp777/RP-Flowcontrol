

git clone https://github.com/rikp777/RP-Flowcontrol
echo Pulled Flowcontrol deployment

cd RP-Flowcontrol


echo Building Flowcontrol application...
docker-compose build

echo Running Flowcontrol application...
docker-compose up

echo
echo Flowcontrol has started. Go to http://localhost:8080 to access

$SHELL


