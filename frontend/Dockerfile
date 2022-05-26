FROM node:alpine
WORKDIR /frontendapp
COPY package.json ./
COPY package-lock.json ./
COPY ./ ./
RUN npm i
ARG REACT_APP_BACKEND_IP
ENV REACT_APP_BACKEND_IP $REACT_APP_BACKEND_IP
CMD ["npm", "start"]