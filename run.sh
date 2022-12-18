#!/bin/sh

if [ $# -ne 1 ]; then
  echo "Usage: $0 <prod or local>"
  exit 1
fi
ENV=$1

APP=example
TIMEZONE='America/Chicago'
USERNAME=henninb
HOST_BASEDIR=$(pwd)
GUEST_BASEDIR=/opt/${APP}

export APP
export USERNAME
export TIMEZONE

if [ "$ENV" = "prod" ]; then
  echo prod
elif [ "$ENV" = "local" ]; then
  echo local
else
  echo "Usage: $0 <prod or local>"
  exit 2
fi

# "$OSTYPE" == "darwin"*
if [ "$OS" = "Linux Mint" ] || [ "$OS" = "Ubuntu" ]; then
  HOST_IP=$(hostname -I | awk '{print $1}')
elif [ "$OS" = "Arch Linux" ]; then
  HOST_IP=$(hostname -i | awk '{print $1}')
elif [ "$OS" = "Fedora" ]; then
  HOST_IP=192.168.100.130
elif [ "$OS" = "Darwin" ]; then
  HOST_IP=$(ipconfig getifaddr en0)
elif [ "$OS" = "Gentoo" ]; then
  HOST_IP=$(hostname -i | awk '{print $1}')
elif [ "$OS" = "FreeBSD" ]; then
  HOST_IP=$(hostname -i | awk '{print $1}')
else
  echo "$OS is not yet implemented."
  exit 1
fi

mkdir -p logs
mkdir -p ssl
mkdir -p "src/main/resources/templates"
mkdir -p "src/test/unit/groovy"
mkdir -p "src/test/integration/groovy"
mkdir -p "src/test/functional/groovy"
mkdir -p "src/test/performance/groovy"

touch env.secrets

if ! ./gradlew clean build; then
  echo "gradle build failed."
  exit 1
fi

if ! docker build -t $APP --build-arg TIMEZONE=${TIMEZONE} --build-arg APP=${APP} --build-arg USERNAME=${USERNAME} .; then
  echo "docker build failed."
  exit 1
fi

#echo docker run -it -h ${APP} --add-host hornsup:$HOST_IP --env-file env.secrets --env-file env -v $HOST_BASEDIR/logs:$GUEST_BASEDIR/logs -v $HOST_BASEDIR/ssl:$GUEST_BASEDIR/ssl -v $HOST_BASEDIR/json_in:$GUEST_BASEDIR/json_in -v $HOST_BASEDIR/config:$GUEST_BASEDIR/config -v $HOST_BASEDIR/excel_in:$GUEST_BASEDIR/excel_in --rm ${APP} bash
docker run -it -h ${APP} --add-host hornsup:$HOST_IP -p 8082:8080 --env-file env.secrets --env-file env -v $HOST_BASEDIR/logs:$GUEST_BASEDIR/logs -v $HOST_BASEDIR/ssl:$GUEST_BASEDIR/ssl -v $HOST_BASEDIR/json_in:$GUEST_BASEDIR/json_in -v $HOST_BASEDIR/config:$GUEST_BASEDIR/config -v $HOST_BASEDIR/excel_in:$GUEST_BASEDIR/excel_in --rm --name ${APP} ${APP}
if [ $? -ne 0 ]; then
  echo "docker run failed."
  exit 1
fi

exit 0
