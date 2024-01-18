FROM node:18-alpine AS builder
ENV NODE_ENV production

WORKDIR /app

COPY package*.json .

RUN npm ci

COPY . .

RUN npm run prod

FROM nginx:1.25.3-alpine as production
ENV NODE_ENV production

WORKDIR /usr/share/nginx/html

RUN rm -rf ./*

COPY --from=builder /app/dist .
COPY nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]
