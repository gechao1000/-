## Gitea

> GitHub：https://github.com/go-gitea/gitea

部署 

```yaml
version: '2'
services:
  web:
    image: gitea/gitea:1.12.2
    volumes:
      - ./data:/data
    ports:
      - "3000:3000"
      - "22:22"
    depends_on:
      - db
    restart: always
  db:
    image: mysql:5.7
    restart: always
    environment:
      - MYSQL_ROOT_PASSWORD=123456
      - MYSQL_DATABASE=gitea
      - MYSQL_USER=gitea
      - MYSQL_PASSWORD=123456
    volumes:
      - ./db/:/var/lib/mysql
```

