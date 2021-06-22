# ____        _ _     _       _
#| __ ) _   _(_) | __| |  ___| |_ __ _  __ _  ___
#|  _ \| | | | | |/ _` | / __| __/ _` |/ _` |/ _ \
#| |_) | |_| | | | (_| | \__ \ || (_| | (_| |  __/
#|____/ \__,_|_|_|\__,_| |___/\__\__,_|\__, |\___|
#
FROM node:latest as build-stage
WORKDIR /flowcontrol/app
COPY package*.json ./
RUN npm install
COPY ./ .
RUN npm run build

# ____                _       _
#|  _ \ _ __ ___   __| |  ___| |_ __ _  __ _  ___
#| |_) | '__/ _ \ / _` | / __| __/ _` |/ _` |/ _ \
#|  __/| | | (_) | (_| | \__ \ || (_| | (_| |  __/
#|_|   |_|  \___/ \__,_| |___/\__\__,_|\__, |\___|
#
FROM nginx as production-stage
RUN mkdir -p /flowcontrol/app
COPY --from=build-stage /flowcontrol/app/dist /flowcontrol/app
COPY nginx.conf /etc/nginx/nginx.conf

EXPOSE 80

