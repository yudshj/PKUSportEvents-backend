软件工程四组 - PKUSportEvents

组员：

- maghsk
- shercoo
- xzy-xzy
- SpiritSoda
- lcrhPR
- Meow-starlight
- CeoxNim
- IanWalls

## 环境

```bash
$ vim docker-compose.yml
$ docker-compose up -d
```
`docker-compose.yml`

```yaml
version: '3.8'
services:
  db:
    image: mysql
    container_name: pkuse-mysql
    restart: always
    ports:
      - 13306:3306
    environment:
      MYSQL_ROOT_PASSWORD: ruangong2020sizu
      MYSQL_DATABASE: springboot_jpa
      MYSQL_USER: spring
      MYSQL_PASSWORD: ruangong2020sizu
    volumes:
      - db_data:/var/lib/mysql

  adminer:
    image: adminer
    restart: always
    links:
      - db
    ports:
      - 28080:8080

volumes:
  db_data:
```