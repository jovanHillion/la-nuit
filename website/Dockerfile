FROM node:latest

RUN npm i -g pnpm

WORKDIR /app

EXPOSE 3001

CMD pnpm i && pnpm run build && pnpm run dev -p 3001
