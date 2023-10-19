# Estágio de construção (build)
FROM node:16.14 as build

# Crie um diretório de trabalho no contêiner
WORKDIR /app

# Instale o Angular CLI globalmente
RUN npm install -g @angular/cli

# Copie o arquivo package.json para o contêiner
COPY package.json .

# Instale as dependências
RUN npm install

# Copy the project files into the container
COPY . .

# Build the Angular app
RUN ng build --configuration=production

# Estágio final
FROM nginx:alpine

# Copy the built Angular app to the web server directory from the "build" stage
COPY --from=build /app/dist/wk_test /usr/share/nginx/html

# Expose port 80
EXPOSE 80

# Start Nginx
CMD ["nginx", "-g", "daemon off;"]
