FROM node:18-alpine AS builder
ENV NODE_ENV production

WORKDIR /app

COPY package*.json .

RUN npm install

COPY . .

RUN ./node_modules/.bin/ng build --configuration production --aot

FROM nginx:1.25.3-alpine as production
ENV NODE_ENV production

WORKDIR /usr/share/nginx/html

RUN rm -rf ./*

COPY --from=builder /app/dist/app/browser /usr/share/nginx/html
#COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
