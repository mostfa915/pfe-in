# Étape 1 : Build Angular
FROM node:18-alpine AS build

WORKDIR /app

# Copier tout le projet dans le conteneur
COPY . .

# Debug : afficher le contenu du fichier environment.prod.ts pour vérifier la clé apiBaseUrl
RUN echo "=== Contenu de src/environments/environment.prod.ts ===" && cat src/environments/environment.prod.ts

# Installer les dépendances
RUN npm install

# Construire l'application Angular en mode production
RUN npm run build -- --configuration production

# Étape 2 : Serveur Nginx pour héberger l'application buildée
FROM nginx:alpine AS production

# Copier les fichiers buildés depuis l'étape précédente vers le dossier de Nginx
COPY --from=build /app/dist/frontend-app-v1 /usr/share/nginx/html

# Copier la config nginx personnalisée (optionnel)
# COPY nginx.conf /etc/nginx/nginx.conf

# Exposer le port 80
EXPOSE 80

# Lancer Nginx au démarrage du conteneur
CMD ["nginx", "-g", "daemon off;"]

