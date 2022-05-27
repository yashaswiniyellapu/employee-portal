FROM node:alpine
WORKDIR /frontendapp
COPY package.json ./
COPY package-lock.json ./
COPY ./ ./
RUN npm i
CMD ["npm", "start"]